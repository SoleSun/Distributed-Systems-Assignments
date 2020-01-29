package com.s13998159.CPEN431_2020_A3;

import ca.NetSysLab.ProtocolBuffers.KeyValueResponse;
import ca.NetSysLab.ProtocolBuffers.Message;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.zip.CRC32;

/**
 * This layer implements at-most once semantics (AMOS). The specific details
 * of AMOS are that should a client send multiple identical requests, this
 * layer should service them all the same.
 *
 * Remember that requests should be well known for at least 5 seconds
 */

public class SerializerServer {
    /**
     * Cache relevant variables
     */
    private final int MAX_TIMEOUT = 5; //s
    private final int MAX_CACHE_SIZE = 100;

    /* Caching members */
    ConcurrentHashMap<ByteString, byte[]> req_cache;
    LoadingCache<Request, byte[]> msg_id_cache = CacheBuilder.newBuilder()
    .maximumSize(MAX_CACHE_SIZE)
    .expireAfterAccess(MAX_TIMEOUT, TimeUnit.SECONDS)
    .build(
        new CacheLoader<Request, byte[]>(){
            @Override
            public byte[] load(Request req) throws Exception {
                 return req.response;
            }
        }
    );

    /**
     * Transmission relevant variables
     */
    /*
     * Note that these fields are used as a one-to-one correspondence and
     * will only work for single-threaded architectures.
     */
    InetAddress most_recent_address;
    int most_recent_port;
    Request r;

    /* TODO Determine if sockets are concurrent */
    DatagramSocket server_socket;
    DatagramSocket sender_socket;
    DatagramPacket recv_packet;
    short port;
    byte[] recv_buf = new byte[1 << 14];
    byte[] send_buf = new byte[1 << 14];


    SerializerServer(short port) {
        recv_packet = new DatagramPacket(recv_buf, recv_buf.length);
        r = new Request();
        req_cache = new ConcurrentHashMap<>();

        try {
            this.server_socket = new DatagramSocket(port);
            this.sender_socket = new DatagramSocket();
        } catch (IOException io) {
            System.out.println("Error: Could not create a server socket!");
        }
        this.port = port;
    }

    private boolean checkIfRuntimeMemAvailable() {
        /* Need at least 100kB of memory to be able to operate efficiently */
        long FREE_LIMIT = 100000; //B
        return Runtime.getRuntime().freeMemory() > FREE_LIMIT;
    }

    private boolean verifyID(Message.Msg response) {
        /*
         * Check that the response's checksum is correct and that the message
         * IDs match
         */
        CRC32 check = new CRC32();
        check.update(response.getMessageID().toByteArray());
        check.update(response.getPayload().toByteArray());

        return (check.getValue() == response.getCheckSum());
    }

    byte[] listen() {
        DatagramPacket recv_packet =
                new DatagramPacket(recv_buf, recv_buf.length);
        try {
            server_socket.receive(recv_packet);
//            System.out.println("Debugging: Received request from " +
//                    recv_packet.getAddress() + recv_packet.getPort());
        } catch (IOException io) {
            System.out.println("Error: could not receive new messages!");
            return null;
        }

        recv_buf = recv_packet.getData();
        Message.Msg request;
        try {
            request = Message.Msg.parseFrom(
                    Arrays.copyOfRange(recv_buf, 0, recv_packet.getLength()));
        } catch(OutOfMemoryError | InvalidProtocolBufferException i) {
            System.out.println("Error: Could not parse request");
            return null;
        }

        if (!checkIfRuntimeMemAvailable()) {
            reply(ErrCode.overload_message);
        }

        try {
            if (!verifyID(request)) {
                System.out.println("Error: Checksum incorrect!");
                return null;
            }

            most_recent_address = recv_packet.getAddress();
            most_recent_port = recv_packet.getPort();
            r.message_id = request.getMessageID().toByteArray();
            r.response = new byte[0]; //reset the value
        } catch(OutOfMemoryError m) {
            System.out.println("Error: Out of Memory!");
        }

        /* Check if the reply has previously been seen */
        try {
            byte[] cached_reply = msg_id_cache.get(r);
            /* Value has been found. Return the stashed reply */
            if (cached_reply.length != 0) {
                System.out.println("Debugging: Found in cache");
                reply(cached_reply);
                return null;
            }
        } catch (OutOfMemoryError| ExecutionException e) {
            System.out.println(
                "Error: Could not retrieve val from cache");
            return null;
        }

        /* Cache the client's information for now*/
//        System.out.println("Debugging: Message is good!");
        return request.getPayload().toByteArray();
    }

    boolean reply(byte[] payload) {
        DatagramPacket send_packet;
        try {
            /* Cache the reply */
            r.response = payload;
            msg_id_cache.refresh(r);

            /* Create the CRC of the message */
            CRC32 checksum = new CRC32();
            checksum.update(r.message_id);
            checksum.update(payload);

            /* Create the message */
            Message.Msg msg = Message.Msg.newBuilder()
                    .setMessageID(ByteString.copyFrom(r.message_id))
                    .setPayload(ByteString.copyFrom(payload))
                    .setCheckSum(checksum.getValue())
                    .build();

            /* Now send the data packet */
            send_buf = msg.toByteArray();
            int len = msg.toByteArray().length;
            send_packet = new DatagramPacket(
                    send_buf, len, most_recent_address, most_recent_port);
        } catch (OutOfMemoryError e) {
            System.out.println("Error: Out of Memory!");
            return false;
        }

        try {
            sender_socket.send(send_packet);
        } catch(IOException ioe) {
            System.out.println("Error: Could not send reply");
            return false;
        }
        return true;
    }
}

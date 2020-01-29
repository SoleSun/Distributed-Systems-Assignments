package com.s13998159.CPEN431_2020_A3;

import ca.NetSysLab.ProtocolBuffers.Message;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.zip.CRC32;

/**
 * TODO refactor the UDP Client so that the services relating to the student ID
 * are encapsulated from the services relating to reliable message delivery
 * This class should subsequently be responsible for the latter services.
 */
public class SerializerClient {
    private static int DEFAULT_TIMEOUT = 100;  //ms
    private static int MAXIMUM_TIMEOUT = 5000; //ms_

    byte[] msg_id = new byte[16];

    private DatagramSocket socket;

    SerializerClient() throws SocketException {
        // This throws a socket exception
        this.socket = new DatagramSocket();
    }

    private boolean verifyID(Message.Msg response, Message.Msg original) {
        /*
         * Check that the response's checksum is correct and that the message
         * IDs match
         */
        CRC32 check = new CRC32();
        check.update(response.getMessageID().toByteArray());
        check.update(response.getPayload().toByteArray());

        /* Can't compare ByteStrings alone, so convert to byte array */
        byte[] r_msg_id = response.getMessageID().toByteArray();
        byte[] o_msg_id = original.getMessageID().toByteArray();

        return (check.getValue() == response.getCheckSum()) &&
                (Arrays.equals(r_msg_id, o_msg_id));
    }

    void createMessageID() throws UnknownHostException{
        /* Create the Message ID */
        ByteBuffer msg_id_buf = ByteBuffer.wrap(msg_id);
        msg_id_buf.order(ByteOrder.LITTLE_ENDIAN);
        msg_id_buf.put(InetAddress.getLocalHost().getAddress());
        msg_id_buf.putShort((short)socket.getLocalPort());
        msg_id_buf.putShort((short)0xAABB);
        msg_id_buf.putLong(System.nanoTime());
    }

    /**
     * Send a request to the provided server address and server port with the
     * following payload
     * @param payload byte array to send
     * @param server_address IP address of recipient
     * @param server_port Port number of recipient
     * @return returns a byte array of the response. If error or no response,
     * returns an empty array
     */
    byte[] request(byte[] payload, InetAddress server_address, int server_port) throws IOException{
        byte[] recv_buf = new byte[1 << 14];
        int timeout = DEFAULT_TIMEOUT;

        DatagramPacket send_packet;
        DatagramPacket recv_packet =
            new DatagramPacket(recv_buf, recv_buf.length);

        try {
            createMessageID();
        } catch (UnknownHostException e) {
            System.out.println("Error: Could not create header");
            return new byte[0];
        }

        /* Calculate the checksum */
        CRC32 checksum = new CRC32();
        checksum.update(msg_id);
        checksum.update(payload);

        /* Create the message protocol buffer */
        Message.Msg msg = Message.Msg.newBuilder()
                .setMessageID(ByteString.copyFrom(msg_id))
                .setPayload(ByteString.copyFrom(payload))
                .setCheckSum(checksum.getValue())
                .build();

        /* With the message constructed place it in the send buffer */
        byte[] temp = msg.toByteArray();

        /* Now send the data packet */
        send_packet = new DatagramPacket(
            temp, temp.length, server_address, server_port);
//        System.out.println(
//                "Debugging: Message Length is " + temp.length);
        socket.setSoTimeout(timeout);
        socket.send(send_packet);

        int MAX_RETRIES = 3;
        for (int num_retries = 0; num_retries <= MAX_RETRIES; num_retries++) {
            try {
                socket.receive(recv_packet);

                /* Packet was received before timeout */
//                System.out.println(
//                    "Received message from " + recv_packet.getSocketAddress());
                recv_buf = recv_packet.getData();
                int response_len = recv_packet.getLength();
//                System.out.println(
//                    "Debugging: Response Length is " + response_len);
                Message.Msg response = Message.Msg.parseFrom(
                    Arrays.copyOfRange(recv_buf,0, response_len));

                // If the packet has been received, then perform a checksum
                if (!verifyID(response, msg)) {
                    System.out.println(
                        "Error: ID/checksum didn't match. Attempt " + (num_retries + 1));
                    socket.send(send_packet);
                    continue;
                }

                socket.close();
                return response.getPayload().toByteArray();
            } catch (SocketTimeoutException e) {
                // Attempt three resends upon timeouts.
                // Make sure to double the waiting period per timeout.
                if (num_retries == MAX_RETRIES) {
                    socket.close();
                    System.out.println("Error: Connection has timeout out three times!");
                    return new byte[0];
                } else {
                    timeout = Math.min(2 * timeout, MAXIMUM_TIMEOUT);
                    socket.setSoTimeout(timeout);
                    socket.send(send_packet);
                    System.out.println(
                        "Connection timed out. Resending. Attempt " + (num_retries + 1));
                }
            }
        }
        return new byte[0];
    }
}

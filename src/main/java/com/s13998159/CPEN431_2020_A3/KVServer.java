package com.s13998159.CPEN431_2020_A3;

import ca.NetSysLab.ProtocolBuffers.KeyValueRequest;
import ca.NetSysLab.ProtocolBuffers.KeyValueResponse;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.HashMap;

class KVStorageValue {
    byte[] value;
    int version;
    KVStorageValue(byte[] val, int ver) {
        value = val;
        version = ver;
    }
}

/**
 * TODO Implement out of memeory error checking
 * TODO Then learn to service multiple agnets
 * TODO When multiple can be serviced, it should be easy to integrate other commands
 */
public class KVServer {
    /* The key-value storage module */
    private HashMap<ByteString,KVStorageValue> storage;

    private final int MAX_KEY_LENGTH = 32; // bytes;
    private final int MAX_VAL_LENGTH = 10000; // bytes;

    private SerializerServer serializer;
    private short port;

    /**
     * Creates a server of the key-value server
     * @param port socket to establish itself on
     */
    KVServer(short port){
        serializer = new SerializerServer(port);
        storage =  new HashMap<ByteString, KVStorageValue>();
    }

    /**
     * Use this function to determine whether to return an overload error
     * In overload, there is sufficient memory to service a put request
     * @return true if there is sufficient memory to support runtime
     */
    private boolean checkIfRuntimeMemAvailable() {
        /* Need at least 100kB of memory to be able to operate efficiently */
        long FREE_LIMIT = 100000; //B
        return Runtime.getRuntime().freeMemory() > FREE_LIMIT;
    }

    /**
     * Use this function to determine whether to return a no space error
     * @return true if there is sufficient space to service a put request
     */
    private boolean checkIfMemAvailable(){
        long FREE_LIMIT = 1000000; //B
        long BUFFER_LIMIT = 2000000; //B
        long free = Runtime.getRuntime().freeMemory();
        long used = Runtime.getRuntime().totalMemory() - free;
        long totalFree = Runtime.getRuntime().maxMemory() - used;

        return free > FREE_LIMIT && totalFree > BUFFER_LIMIT;
    }

    private KeyValueResponse.KVResponse.Builder
    handleCommand(KeyValueRequest.KVRequest request) {
        System.out.println(
            "Debugging: Handling command " + request.getCommand());

        KeyValueResponse.KVResponse.Builder response =
                KeyValueResponse.KVResponse.newBuilder();

        KVStorageValue retrieved_val;
        ByteString key;
        switch(request.getCommand()) {
            case Commands.Put: {
                if (!checkIfMemAvailable()) {
                    response.setErrCode(ErrCode.NoSpace);
                    break;
                }

                key = request.getKey();
                if (key.size() > MAX_KEY_LENGTH) {
                    response.setErrCode(ErrCode.InvalKey);
                    break;
                }

                byte[] val = request.getValue().toByteArray();
                if (val.length > MAX_VAL_LENGTH) {
                    response.setErrCode(ErrCode.InvalVal);
                    break;
                }

                storage.put(
                    request.getKey(),
                    new KVStorageValue(val, request.getVersion())
                );
                response.setErrCode(ErrCode.Success);
                break;
            }

            case Commands.Get: {
                key = request.getKey();
                if (key.size() > MAX_KEY_LENGTH) {
                    response.setErrCode(ErrCode.InvalKey);
                    break;
                }

                retrieved_val = storage.get(key);
                if (retrieved_val == null) {
                    response.setErrCode(ErrCode.NoKey);
                } else {
                    response.setValue(ByteString.copyFrom(retrieved_val.value));
                    response.setVersion(retrieved_val.version);
                    response.setErrCode(ErrCode.Success);
                }
                break;
            }

            case Commands.Remove: {
                retrieved_val = storage.remove(request.getKey());
                if (retrieved_val == null) {
                    response.setErrCode(ErrCode.NoKey);
                } else {
                    response.setErrCode(ErrCode.Success);
                }
                break;
            }

            case Commands.Shutdown: {
                System.out.println(
                    "Debugging: Quit cmd received. System exiting");
                System.exit(0);
                break;
            }

            case Commands.Wipeout: {
                System.out.println("Debugging: Wiping out storage server");
                storage.clear();
                response.setErrCode(ErrCode.Success);
                break;
            }

            case Commands.isAlive: {
                response.setErrCode(ErrCode.Success);
                break;
            }

            case Commands.GetPID: {
                String pid = ManagementFactory.getRuntimeMXBean().getName();
                response.setPid(Integer.parseInt(pid.split("@")[0]));
                response.setErrCode(ErrCode.Success);
                break;
            }

            case Commands.GetMembershipCount: {
                response.setMembershipCount(1);
                response.setErrCode(ErrCode.Success);
                break;
            }

            default:
                response.setErrCode(ErrCode.NoCmd);
                System.out.println("Error: Command not supported!");

        }

//        System.out.println("Debugging: Return Code is " + err_code);
        return response;
    }

    void sendOverloadFail() {
        serializer.reply(ErrCode.overload_message);
    }

    void sendGeneralFail() {
        serializer.reply(ErrCode.general_fail_message);
    }

    /**
     * Constantly listen on the passed in port for key-value requests and
     * service them accordingly
     */
    void receive() {
        System.out.println("Debugging: Listening!");
        /* Constantly listen on the provided port */
        while (true) {
            byte[] payload = serializer.listen();
            if (payload == null)
                continue;

            if (!checkIfRuntimeMemAvailable()) {
                sendOverloadFail();
                continue;
            }

            KeyValueRequest.KVRequest request;
            KeyValueResponse.KVResponse.Builder response;
            try {
                request = KeyValueRequest.KVRequest.parseFrom(payload);
            } catch (InvalidProtocolBufferException ipbe) {
                sendGeneralFail();
                System.out.println("Error: Could not parse client message");
                continue;
            }

            try {
                /* Handle the command and form the reply payload */
                response = handleCommand(request);
            } catch(OutOfMemoryError e) {
                System.out.println("Error: Out of memory");
                continue;
            }

            /* Reply to the client */
            if (!serializer.reply(response.build().toByteArray())) {
                System.out.println("Error: Could not send reply!");
            }
        }
    }
}

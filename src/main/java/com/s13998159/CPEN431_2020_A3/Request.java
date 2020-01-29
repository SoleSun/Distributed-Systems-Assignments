package com.s13998159.CPEN431_2020_A3;

import java.util.Arrays;

public class Request {
    byte[] message_id;
    byte[] response;

    Request() {
        this.response = null;
    }

    Request(byte[] msg_id, byte[] p) {
        this.message_id = msg_id;
        this.response = new byte[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Arrays.equals(message_id, request.message_id);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(message_id);
    }
}

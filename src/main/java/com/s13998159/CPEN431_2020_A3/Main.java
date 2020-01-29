package com.s13998159.CPEN431_2020_A3;

public class Main {
    public static void main(String[] args) {
        KVServer server = new KVServer((short) 18159);
        server.receive();
    }
}

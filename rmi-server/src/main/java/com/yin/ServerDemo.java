package com.yin;

public class ServerDemo {
    public static void main(String[] args) {
        IHello hello = new IHelloImpl();
        RPCServer server = new RPCServer();
        server.publisher(hello, 8888);
    }
}

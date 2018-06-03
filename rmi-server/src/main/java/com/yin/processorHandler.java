package com.yin;

public class processorHandler implements Runnable {
    private Object service;
    private int port;
    public processorHandler(Object service, int port) {
        this.service = service;
        this.port = port;
    }

    @Override
    public void run() {

    }
}

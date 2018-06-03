package com.yin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCServer {
    private ExecutorService executorService = Executors.newCachedThreadPool();
    public void publisher(Object hello, int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            //循环监听客户端的请求
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new processorHandler(hello, port));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

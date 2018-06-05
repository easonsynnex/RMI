package com.yin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCServer {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(Object service, int port) {
        System.out.println("服务器服务打开");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            //循环监听客户端的请求
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("监听到一个请求");
                executorService.execute(new processorHandler(socket, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

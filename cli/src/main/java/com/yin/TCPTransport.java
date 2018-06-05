package com.yin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by easony on 06/05/18.
 */
public class TCPTransport {
    private String host;
    private int port;

    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socket newSocket() {
        Socket socket = null;
        try {
            socket = new Socket(this.host,this.port);
            System.out.println("创建一个Socket连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public Object send(RpcRequest rpcRequest) {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        Socket socket = null;
        try {
            socket = newSocket();
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(rpcRequest);

            objectOutputStream.flush();

            //获取输入流信息
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

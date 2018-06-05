package com.yin;

import com.sun.corba.se.pept.encoding.OutputObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class processorHandler implements Runnable {
    private Object service;
    private Socket socket;
    public processorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        //获取socket
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            //反序列化
            RpcRequest rpcRequest = (RpcRequest)inputStream.readObject();

            Object object = invoke(rpcRequest);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(object);
            outputStream.flush();
            inputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public Object invoke (RpcRequest rpcRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object [] args = rpcRequest.getParameter();
        Class<?>[] types = new Class[args.length];
        for (int i = 0;i < args.length; i++){
            types[i] = args[i].getClass();
        }
        Method method = service.getClass().getDeclaredMethod(rpcRequest.getMethodName(), types);
        return method.invoke(service, args);
    }
}

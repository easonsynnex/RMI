package com.yin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by easony on 06/05/18.
 */
public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameter(args);
        //通过TCP请求传送数据
        TCPTransport tcpTransport = new TCPTransport(host, port);
        return tcpTransport.send(rpcRequest);
    }
}

package com.yin;

import java.lang.reflect.Proxy;

/**
 * Created by easony on 06/05/18.
 */
public class RpcClientProxy {
    //创建客户端的远程代理
    public <T> T clientProxy(Class<T> clazz, String host, int port){

        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RemoteInvocationHandler(host,port));
    }
}

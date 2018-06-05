package com.yin;

/**
 * Created by easony on 06/05/18.
 */
public class ClientDemo {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy=new RpcClientProxy();

        IHello hello=rpcClientProxy.clientProxy
                (IHello.class,"localhost",8888);
        System.out.println(hello.say("eason"));

    }
}

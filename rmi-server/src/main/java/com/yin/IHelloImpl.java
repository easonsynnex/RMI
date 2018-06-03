package com.yin;

public class IHelloImpl implements IHello{
    @Override
    public String say(String msg) {
        return "Hello " + msg;
    }
}

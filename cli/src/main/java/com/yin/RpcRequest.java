package com.yin;

import java.io.Serializable;

/**
 * Created by easony on 06/04/18.
 */

public class RpcRequest implements Serializable{
    private static final long serialVersionUID = -9100893052391757993L;
    private String className;
    private String methodName;
    private Object [] parameter;

    public RpcRequest() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameter() {
        return parameter;
    }

    public void setParameter(Object[] parameter) {
        this.parameter = parameter;
    }

    public void setParameter(String[] parameter) {
        this.parameter = parameter;
    }
}

package rest;

import common.utils.AllureUtils;

import java.io.ByteArrayOutputStream;

public class LogEntity {
    private ByteArrayOutputStream request;
    private ByteArrayOutputStream response;
    private String name;

    public LogEntity(String name) {
        this.name = name;
        this.request = new ByteArrayOutputStream();
        this.response = new ByteArrayOutputStream();
    }

    public ByteArrayOutputStream getRequest() {
        return request;
    }

    public ByteArrayOutputStream getResponse() {
        return response;
    }

    public String getName() {
        return name;
    }

    public void attachToAllure() {
        AllureUtils.attachFromStream(name != null ? "request \"" + name + "\"" : "requests", request);
        AllureUtils.attachFromStream(name != null ? "response \"" + name + "\"" : "responses", response);
    }
}

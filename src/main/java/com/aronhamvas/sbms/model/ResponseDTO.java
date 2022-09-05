package com.aronhamvas.sbms.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ResponseDTO {
    private static AtomicInteger instanceCounter = new AtomicInteger(0);
    private final String controllerName;
    private final Integer instanceNumber;

    public ResponseDTO(String controllerName) {
        this.instanceNumber = instanceCounter.getAndIncrement();
        this.controllerName = controllerName;
    }

    public String getControllerName() {
        return this.controllerName;
    }

    public Integer getInstanceNumber() {
        return instanceNumber;
    }
}

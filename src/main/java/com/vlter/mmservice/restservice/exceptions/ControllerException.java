package com.vlter.mmservice.restservice.exceptions;

import java.io.Serializable;

/**
 * Created by Tereshchenko on 04.11.2020.
 */
public class ControllerException implements Serializable{
    private Integer status;
    private String reason;

    public ControllerException() {
    }

    public ControllerException(Integer status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

package com.vlter.mmservice.restservice.models;

import java.io.Serializable;

/**
 * Created by Tereshchenko on 21.10.2020.
 */

public class StatusMessage implements Serializable{
    private Integer status;
    private String reason;

    public StatusMessage() {
    }

    public StatusMessage(Integer status, String reason) {
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

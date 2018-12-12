package com.bill.materiel.utils.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String status;
    private String error;
    private Object data;

    public Message(String status){
        this.status = status;
        this.error = "";
        this.data = null;
    }

    public Message(String status, Object data){
        this.status = status;
        this.error = "";
        this.data = data;
    }

    public Message(String status, String error){
        this.status = status;
        this.error = error;
        this.data = null;
    }
}
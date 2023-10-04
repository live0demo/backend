package com.example.backend.common.content;

import com.example.backend.common.content.BaseContent;

import java.io.Serializable;

public class WrapContent<T> extends BaseContent implements Serializable {
    protected T data;

    public WrapContent(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public WrapContent() {
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("WrapContent{");
        sb.append("data=").append(this.data);
        sb.append(", code='").append(this.code).append('\'');
        sb.append(", message='").append(this.message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public T getData() {
        return this.data;
    }

    protected static class Dummy implements Serializable {
        protected Dummy() {
        }
    }
}

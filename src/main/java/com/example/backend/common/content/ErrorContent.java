package com.example.backend.common.content;

import com.example.backend.common.content.BaseContent;

import java.util.Collection;
import java.util.Collections;

public class ErrorContent extends BaseContent {
    protected Collection<Error> errors;

    public ErrorContent(int code, String message, Collection<Error> errors) {
        super(code, message);
        this.errors = (Collection)(errors == null ? Collections.emptyList() : errors);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("ErrorContent{");
        sb.append("errors=").append(this.errors);
        sb.append(", code='").append(this.code).append('\'');
        sb.append(", message='").append(this.message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Collection<Error> getErrors() {
        return this.errors;
    }

    public void setErrors(Collection<Error> errors) {
        this.errors = errors;
    }
}

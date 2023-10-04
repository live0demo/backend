package com.example.backend.common.response;

import com.example.backend.common.ResultCode;
import com.example.backend.common.content.WrapContent;
import com.example.backend.common.content.BaseContent;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ResponseBuilder {
    public ResponseBuilder() {
    }

    public static <E extends BaseContent> Response status(E content, HttpStatus httpStatus) {
        return new Response(content, httpStatus);
    }

    protected static <E> Response buildPagingReponse(Collection<E> data, ResultCode resultCode, String message) {
        PaginationRS<E> paginationRS = new PaginationRS();
        paginationRS.setItems(data);
        paginationRS.setTotal((long)data.size());
        WrapContent<PaginationRS<E>> content = new WrapContent(resultCode.getCode(), message, paginationRS);
        return status(content, resultCode.getHttpStatus());
    }

    protected static <E> Response buildPagingReponse(Collection<E> data, long total, ResultCode resultCode, String message) {
        PaginationRS<E> paginationRS = new PaginationRS();
        paginationRS.setItems(data);
        paginationRS.setTotal(total);
        WrapContent<PaginationRS<E>> content = new WrapContent(resultCode.getCode(), message, paginationRS);
        return status(content, resultCode.getHttpStatus());
    }
}

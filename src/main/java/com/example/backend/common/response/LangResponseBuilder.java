package com.example.backend.common.response;

import com.example.backend.common.*;
import com.example.backend.common.content.BaseContent;
import com.example.backend.common.content.ErrorContent;
import com.example.backend.common.content.WrapContent;
import com.example.backend.common.service.TranslateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class LangResponseBuilder extends ResponseBuilder {
    private static final Logger log = LoggerFactory.getLogger(LangResponseBuilder.class);
    protected static TranslateService translateService;

    public LangResponseBuilder() {
    }

    public static Response success() {
        return status(ResultCodes.SUCCESS);
    }

    public static Response successWithLang(String lang) {
        return status(lang, ResultCodes.SUCCESS);
    }

    public static <E> Response success(E data) {
        return status(ResultCodes.SUCCESS, data);
    }

    public static <E> Response success(String lang, E data) {
        return status(lang, ResultCodes.SUCCESS, data);
    }

    public static <E> Response success(Collection<E> data) {
        return status(ResultCodes.SUCCESS, data);
    }

    public static <E> Response success(Collection<E> data, long total) {
        return status(ResultCodes.SUCCESS, data, total);
    }

    public static <E> Response status(ResultCode resultCode, E data) {
        String message = translateService.translate(resultCode.getMessage());
        WrapContent<E> content = new WrapContent(resultCode.getCode(), message, data);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static <E> Response status(String lang, ResultCode resultCode, E data) {
        String message = translateService.translateWithLang(lang, resultCode.getMessage());
        WrapContent<E> content = new WrapContent(resultCode.getCode(), message, data);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static Response status(String lang, ResultCode resultCode) {
        String message = translateService.translateWithLang(lang, resultCode.getMessage());
        BaseContent content = new BaseContent(resultCode.getCode(), message);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static Response status(ResultCode resultCode) {
        String message = translateService.translate(resultCode.getMessage());
        BaseContent content = new BaseContent(resultCode.getCode(), message);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static Response statusWithLangArgs(String lang, ResultCode resultCode, Object... translateArgs) {
        String message = translateService.translateWithLangAndArgs(lang, resultCode.getMessage(), translateArgs);
        BaseContent content = new BaseContent(resultCode.getCode(), message);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static Response statusWithLangArgs(ResultCode resultCode, Object... translateArgs) {
        String message = translateService.translateWithArgs(resultCode.getMessage(), translateArgs);
        BaseContent content = new BaseContent(resultCode.getCode(), message);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static <E> Response statusWithLangArgs(E data, ResultCode resultCode, Object... translateArgs) {
        String message = translateService.translateWithArgs(resultCode.getMessage(), translateArgs);
        WrapContent<E> content = new WrapContent(resultCode.getCode(), message, data);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static <E> Response statusWithLangArgs(E data, String lang, ResultCode resultCode, Object... translateArgs) {
        String message = translateService.translateWithLangAndArgs(lang, resultCode.getMessage(), translateArgs);
        WrapContent<E> content = new WrapContent(resultCode.getCode(), message, data);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static <E> Response status(ResultCode resultCode, Collection<E> data) {
        String message = translateService.translate(resultCode.getMessage());
        return buildPagingReponse(data, resultCode, message);
    }

    public static <E> Response status(ResultCode resultCode, Collection<E> data, long total) {
        String message = translateService.translate(resultCode.getMessage());
        return buildPagingReponse(data, total, resultCode, message);
    }

    public static <E> Response statusWithLangArgs(String lang, ResultCode resultCode, Collection<E> data, Object... translateArgs) {
        return statusWithLangArgs(lang, resultCode, data, (long)data.size(), translateArgs);
    }

    public static <E> Response statusWithLangArgs(String lang, ResultCode resultCode, Collection<E> data, long total, Object... translateArgs) {
        String message = translateService.translateWithLangAndArgs(lang, resultCode.getMessage(), translateArgs);
        return buildPagingReponse(data, total, resultCode, message);
    }

    public static Response errors(ResultCode resultCode, Collection<Error> errors) {
        String message = translateService.translate(resultCode.getMessage());
        ErrorContent content = new ErrorContent(resultCode.getCode(), message, errors);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static Response errors(String lang, ResultCode resultCode, Collection<Error> errors, String... translateArgs) {
        String message = translateService.translateWithLangAndArgs(lang, resultCode.getMessage(), translateArgs);
        ErrorContent content = new ErrorContent(resultCode.getCode(), message, errors);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    public static Response errors(ResultCode resultCode, Collection<Error> errors, String... translateArgs) {
        String message = translateService.translateWithArgs(resultCode.getMessage(), translateArgs);
        ErrorContent content = new ErrorContent(resultCode.getCode(), message, errors);
        return status((BaseContent)content, (HttpStatus)resultCode.getHttpStatus());
    }

    @Autowired
    public void setTranslateService(TranslateService translateService) {
        LangResponseBuilder.translateService = translateService;
    }
}

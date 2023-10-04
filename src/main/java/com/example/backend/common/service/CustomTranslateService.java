package com.example.backend.common.service;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomTranslateService extends DefaultTranslateService {
    protected MessageSource messageSource;

    public CustomTranslateService(MessageSource messageSource) {
        super(messageSource);
        this.messageSource = messageSource;
    }

    public String translateWithDefaultLang(String defaultLang, String content) {
        try {
            return this.messageSource.getMessage(content, (Object[])null, LocaleContextHolder.getLocale());
        } catch (Exception var3) {
            return this.translateWithLang(defaultLang, content);
        }
    }

    public String translateWithDefaultLang(String content) {
        return translateWithDefaultLang("en", content);
    }
}

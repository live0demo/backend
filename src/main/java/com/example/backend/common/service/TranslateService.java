package com.example.backend.common.service;

public interface TranslateService {
    String translate(String var1);

    String translateWithArgs(String var1, Object... var2);

    String translateWithLang(String var1, String var2);

    String translateWithLangAndArgs(String var1, String var2, Object... var3);
}

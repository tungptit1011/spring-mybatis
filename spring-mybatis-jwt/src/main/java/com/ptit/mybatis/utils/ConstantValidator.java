/**
 * Copyright 2023 NguyenDacTung
 */
package com.ptit.mybatis.utils;

public class ConstantValidator {
    public static final int MAX_LENGTH_EMAIL = 100;
    public static final String REGX_EMAIL = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+";

    public static final String REGX_TEL = "[0-9]{1,4}-[0-9]{1,4}-[0-9]{1,4}";

    public static final int MIN_LENGTH_PASSWORD = 5;
    public static final int MAX_LENGTH_PASSWORD = 15;

    public static final String REGX_PASSWORD = "[a-zA-Z0-9]*";

    public static final String REGX_FULL_NAME_KANA = "[\\u30a1-\\u30ff\\uff65-\\uffa1]*";

    public static final int MAX_LENGTH_FULL_NAME_KANA = 255;

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
}

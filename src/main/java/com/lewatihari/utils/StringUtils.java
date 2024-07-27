package com.lewatihari.utils;

public class StringUtils {
    public static String generateSlug(String text) {
        return text.toLowerCase().replaceAll("\\s+", "-");
    }
}

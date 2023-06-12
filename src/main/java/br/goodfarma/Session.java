package br.goodfarma;

import java.util.HashMap;

public class Session {
    private static final HashMap<String, String> sessionMap = new HashMap<>();

    public static String get(String key) {
        return sessionMap.get(key);
    }

    public static String put(String key, String value) {
        return sessionMap.put(key, value);
    }

    public static String forget(String key) {
        return sessionMap.remove(key);
    }
}

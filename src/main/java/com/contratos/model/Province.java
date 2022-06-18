package com.contratos.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Province {
    ALMERIA,
    GRANADA,
    CORDOBA,
    SEVILLA,
    CADIZ,
    HUELVA;

    private static final Map<String, Province> nameToValueMap =
            new HashMap<String, Province>();

    static {
        for (Province value : EnumSet.allOf(Province.class)) {
            nameToValueMap.put(value.name(), value);
        }
    }

    public static Set<String> getList() {
        return nameToValueMap.keySet();
    }

    public static Boolean isValid(String name) {
        return nameToValueMap.containsKey(name);
    }

}

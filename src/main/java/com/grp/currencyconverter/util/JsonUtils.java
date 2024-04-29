package com.grp.currencyconverter.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parseJson(String json, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(json, typeReference);
    }

    public static <T> T parseJson(String json, Class<T> valueType) throws IOException {
        return objectMapper.readValue(json, valueType);
    }
}

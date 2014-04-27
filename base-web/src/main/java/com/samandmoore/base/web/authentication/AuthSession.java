package com.samandmoore.base.web.authentication;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

/**
 * @author Sam Moore
 * @since 4/27/14 12:38 AM
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthSession {

    private final Map<String, Object> values = Maps.newHashMap();

    public void put(final String key, final Object value) {

        values.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final String key) {

        return (T) values.get(key);
    }

    String serialize() {

        final StringBuilder serializer = new StringBuilder();

        for (Map.Entry<String, Object> entry : this.values.entrySet()) {
            serializer.append(String.format("%s=%s&", entry.getKey(), entry.getValue()));
        }

        return serializer.toString();
    }

    void deserialize(final String serialized) {


    }
}

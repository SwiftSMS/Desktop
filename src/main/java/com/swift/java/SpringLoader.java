package com.swift.java;

import javafx.util.Callback;
import org.springframework.context.ApplicationContext;

public class SpringLoader implements Callback<Class<?>, Object> {

    private final ApplicationContext context;

    public SpringLoader(final ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object call(final Class<?> param) {
        if (context.getBeanNamesForType(param).length != 0) {
            return context.getBean(param);
        }
        return newInstance(param);
    }

    private Object newInstance(final Class<?> param) {
        Object instance = null;
        try {
            instance = param.newInstance();
        } catch (final InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }
}

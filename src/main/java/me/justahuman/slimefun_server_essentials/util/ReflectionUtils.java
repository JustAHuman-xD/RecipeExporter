package me.justahuman.slimefun_server_essentials.util;

import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.entities.ExpCollector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static <T> T getField(Object object, String fieldName, T defaultValue) {
        return object != null
                ? getField(object.getClass(), object, fieldName, defaultValue)
                : defaultValue;
    }

    public static <T> T getField(Class<?> clazz, Object object, String fieldName, T defaultValue) {
        try {
            final Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static <T> T getStaticField(Class<?> clazz, String fieldName, T defaultValue) {
        try {
            final Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static <R> R callMethod(Object object, String methodName, R defaultValue, Class<?>[] paramTypes, Object[] args) {
        return callMethod(object.getClass(), object, methodName, defaultValue, paramTypes, args);
    }

    public static <R> R callMethod(Class<?> clazz, Object object, String methodName, R defaultValue, Class<?>[] paramTypes, Object[] args) {
        try {
            final Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return (R) method.invoke(object, args);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
}

package com.leanplum.annotations;

import android.util.Log;
import com.leanplum.Var;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Parser {
    private static <T> void m12738a(Object obj, String str, T t, String str2, Field field) {
        Var define = Var.define(str, t, str2);
        define.addValueChangedHandler(new C0626a(new WeakReference(obj), obj != null, field, define));
    }

    public static void parseVariables(Object... objArr) {
        try {
            for (Object obj : objArr) {
                m12737a(obj, obj.getClass());
            }
        } catch (Throwable e) {
            Log.e("Leanplum", "Error parsing variables", e);
        }
    }

    public static void parseVariablesForClasses(Class<?>... clsArr) {
        try {
            for (Class a : clsArr) {
                m12737a(null, a);
            }
        } catch (Throwable e) {
            Log.e("Leanplum", "Error parsing variables", e);
        }
    }

    private static void m12737a(Object obj, Class<?> cls) {
        for (Field field : cls.getFields()) {
            String group;
            String name;
            if (field.isAnnotationPresent(Variable.class)) {
                Variable variable = (Variable) field.getAnnotation(Variable.class);
                group = variable.group();
                name = variable.name();
                Object obj2 = null;
            } else if (field.isAnnotationPresent(File.class)) {
                File file = (File) field.getAnnotation(File.class);
                group = file.group();
                name = file.name();
                int i = 1;
            } else {
            }
            if (name == null || name.length() == 0) {
                name = field.getName();
            }
            if (group.length() > 0) {
                name = new StringBuilder(String.valueOf(group)).append(".").append(name).toString();
            }
            Class type = field.getType();
            String cls2 = type.toString();
            if (cls2.equals("int")) {
                m12738a(obj, name, Integer.valueOf(field.getInt(obj)), "integer", field);
            } else if (cls2.equals("byte")) {
                m12738a(obj, name, Byte.valueOf(field.getByte(obj)), "integer", field);
            } else if (cls2.equals("short")) {
                m12738a(obj, name, Short.valueOf(field.getShort(obj)), "integer", field);
            } else if (cls2.equals("long")) {
                m12738a(obj, name, Long.valueOf(field.getLong(obj)), "integer", field);
            } else if (cls2.equals("char")) {
                m12738a(obj, name, Character.valueOf(field.getChar(obj)), "integer", field);
            } else if (cls2.equals("float")) {
                m12738a(obj, name, Float.valueOf(field.getFloat(obj)), "float", field);
            } else if (cls2.equals("double")) {
                m12738a(obj, name, Double.valueOf(field.getDouble(obj)), "float", field);
            } else if (cls2.equals("boolean")) {
                m12738a(obj, name, Boolean.valueOf(field.getBoolean(obj)), "bool", field);
            } else if (type.isPrimitive()) {
                Log.e("Leanplum", "Variable " + name + " is an unsupported primitive type");
            } else if (type.isArray()) {
                Log.e("Leanplum", "Variable " + name + " should be a List instead of an Array");
            } else if (type.isAssignableFrom(List.class)) {
                m12738a(obj, name, field.get(obj), "list", field);
            } else if (type.isAssignableFrom(Map.class)) {
                m12738a(obj, name, field.get(obj), "group", field);
            } else {
                Object obj3 = field.get(obj);
                group = obj3 == null ? null : obj3.toString();
                if (obj2 != null) {
                    boolean z;
                    Var defineFile = Var.defineFile(name, group);
                    if (obj != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    defineFile.addFileReadyHandler(new C0627b(new WeakReference(obj), z, field, defineFile));
                } else {
                    m12738a(obj, name, group, "string", field);
                }
            }
        }
    }
}

package com.example.todolist.utils;

import com.example.todolist.task.TaskModel;
import org.springframework.beans.*;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Utils {

    // passa o que esta dentro do source para o target
    public static void copyNonNullProprities(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // pega todas as propriedades nulas
    public static String[] getNullPropertyNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);

         PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd: pds){
           Object srcValue = src.getPropertyValue(pd.getName());
           if (srcValue == null){
               emptyNames.add(pd.getName());
           }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

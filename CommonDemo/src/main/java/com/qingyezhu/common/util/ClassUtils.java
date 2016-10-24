package com.qingyezhu.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassUtils {
	
	/**
	 * 获取泛型参数数组<br/>
	 * @param clazz 泛型实现类的类对象
	 * @return
	 */
	public static Type[] getGenericType(Class<?> clazz){
		//获取实现类对应的泛型父类
		Type type = clazz.getGenericSuperclass();
		if(type instanceof ParameterizedType){
			//当是泛型时，获取其实际传入的泛型数组
			return ((ParameterizedType) type).getActualTypeArguments();
		}
		return null;
	}
	
	public static Set<Field> getAllField(Class<?> clazz){
		Set<Field> fieldSet = new HashSet<Field>();
		//获取所有的public公有属性，包括继承而来的
		fieldSet.addAll(Arrays.asList(clazz.getFields()));
		//获取自己类定义的属性，包括private私有的
		fieldSet.addAll(Arrays.asList(clazz.getDeclaredFields()));
		Class<?> superClazz = clazz.getSuperclass();
		if(!superClazz.equals(Object.class)){			
			//当父类不是Object时，继续递归获取类中的属性
			Set<Field> superFieldSet = getAllField(superClazz);
			fieldSet.addAll(superFieldSet);
		}
		return fieldSet;
	}
	
	public static Set<Method> getAllMethod(Class<?> clazz){
		Set<Method> methodSet = new HashSet<>();
		//获取所有的public公有方法，包括继承而来的
		methodSet.addAll(Arrays.asList(clazz.getMethods()));
		//获取自己类定义的方法，包括private私有的
		methodSet.addAll(Arrays.asList(clazz.getDeclaredMethods()));
		Class<?> superClazz = clazz.getSuperclass();
		if(!superClazz.equals(Object.class)){
			//当父类不是Object时，继续递归获取类中的方法
			Set<Method> superMethodSet = getAllMethod(superClazz);
			methodSet.addAll(superMethodSet);
		}
		return methodSet;
	}
	
}

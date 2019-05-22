package com.ecxls.integral.util.convert;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

public class ObjectUtil {
	/**
	 * Map转化为对象
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
		Object obj = null;
		try {
			if (map == null) {
				return null;
			}
			obj = beanClass.newInstance();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				if(!StringUtils.isEmpty(map.get(field.getName()))){
					if(field.getType().toString().equals("class java.math.BigDecimal")){
						field.set(obj,new BigDecimal((Double) map.get(field.getName())));
					}else if(field.getType().toString().equals("class java.util.Date")){
						field.set(obj,new Date((Long) map.get(field.getName())));
					}else if(field.getType().toString().equals("class java.lang.Long")){
						field.set(obj,new Long((String) map.get(field.getName())));
					}else{
						field.set(obj, map.get(field.getName()));
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * Map转化为对象
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> beanClass) {      
		try {
			if (map == null)     
	            return null;      
	    
	        Object obj = beanClass.newInstance();    
	    
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());      
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();      
	        for (PropertyDescriptor property : propertyDescriptors) {    
	            Method setter = property.getWriteMethod();      
	            if (setter != null) {    
	                setter.invoke(obj, map.get(property.getName()));     
	            }    
	        }   
	        return obj; 
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;   
	}
	
	/**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
	
	/**
	 * List去重
	 * @param list
	 * @return
	 */
	public static List<String> removeDuplicate(List<String> list)  {
	    Set<String> set = new HashSet<String>(list);
	    list.clear();
	    list.addAll(set);
		return list;
	}
	
	/**
	 * List去重(Long)
	 * @param list
	 * @return
	 */
	public static List<Long> removeDuplicateLong(List<Long> list)  {
	    Set<Long> set = new HashSet<Long>(list);
	    list.clear();
	    list.addAll(set);
		return list;
	}
	
	/**
	 * List判断是否重复
	 * @param list
	 * @return
	 */
	public static boolean isRepeat(List<String> list)  {
		if(list.size() != removeDuplicate(list).size()){
			return true; 
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Serializable>T deepClone(T obj){
        T clone = null;
        try {
            ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
            ObjectOutputStream objectOS = new ObjectOutputStream(byteOS);
            objectOS.writeObject(obj);
            objectOS.close();
            
            ByteArrayInputStream byteIS = new ByteArrayInputStream(byteOS.toByteArray());
            ObjectInputStream objectIS = new ObjectInputStream(byteIS);
            clone = (T) objectIS.readObject();
            
            objectIS.close();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return clone;
    }

	public static String newLines(String str, int length) {
		int strLength = str.length();
		int lineNum = strLength % length == 0 ? strLength / length : strLength / length + 1;
		List<String> strList = new ArrayList<String>();
		String subStr = "";
		for(int i = 1; i <= lineNum; i++){
			if(i < lineNum){
			subStr = str.substring((i-1)*length, i*length);
			}else{
			subStr = str.substring((i-1)*length, strLength);
			}
			//添加分割字符串
			strList.add(subStr);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strList.size(); i++) {
			sb.append(strList.get(i));
			if((i+1) != strList.size()){
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
}

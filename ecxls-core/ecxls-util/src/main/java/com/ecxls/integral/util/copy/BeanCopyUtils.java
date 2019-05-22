package com.ecxls.integral.util.copy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.beans.BeanCopier;

/**
 * <p>ClassName: BeanCopyUtils<p>
 * <p>Description: 对象复制<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年10月26日 下午1:56:00
 */
public class BeanCopyUtils {
	public static Map<String,BeanCopier> beanCopierMap = new HashMap<String,BeanCopier>();
    
    public static void copyProperties(Object source, Object target){
        String beanKey 		=  generateKey(source.getClass(), target.getClass());
        BeanCopier copier 	=  null;
        if(!beanCopierMap.containsKey(beanKey)){
             copier = BeanCopier.create(source.getClass(), target.getClass(), false);
             beanCopierMap.put(beanKey, copier);
        }else{
             copier = beanCopierMap.get(beanKey);
        }
        
        copier.copy(source, target, null);
    }   
    
    private static String generateKey(Class<?> class1,Class<?>class2){
        return class1.toString() + class2.toString();
    }
}

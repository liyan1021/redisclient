/**
 * @FileName: Util.java
 * @Package com.sfbest.cache.redis.impl
 * 
 * @author zhangshaobin
 * @created 2015年4月17日 下午3:48:57
 * 
 * Copyright 2011-2015 顺丰优选 版权所有
 */
package com.liyan.redis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

/**
 * <p>TODO</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author liyan
 * @since 1.0
 * @version 1.0
 */
public class SerializeUtil {
	
	protected static Logger logger = Logger.getLogger(SerializeUtil.class.getName());
	
	/**
	 * 序列化对象
	 *
	 * @author liyan
	 * @created 2015年4月17日 下午3:50:00
	 *
	 * @param value
	 * @return
	 */
	public static byte[] serialize(Object value) {  
        if (value == null) {  
            throw new NullPointerException("Can't serialize null");  
        }  
        byte[] rv=null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            os.writeObject(value);  
            os.close();  
            bos.close();  
            rv = bos.toByteArray();  
        } catch (IOException e) {  
            throw new IllegalArgumentException("Non-serializable object", e);  
        } finally {  
            close(os);  
            close(bos);  
        }  
        return rv;  
    }  

    
	/**
	 * 反序列化对象
	 *
	 * @author liyan
	 * @created 2015年4月17日 下午3:50:12
	 *
	 * @param in
	 * @return
	 */
	public static Object deserialize(byte[] in) {  
        Object rv=null;  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                rv=is.readObject();  
                is.close();  
                bis.close();  
            }  
        } catch (IOException e) {  
        	e.printStackTrace();
            logger.debug("Caught IOException decoding %d bytes of data");  
        } catch (ClassNotFoundException e) {  
        	e.printStackTrace();
            logger.debug("Caught ClassNotFoundException decoding %d bytes of data");  
        } finally {  
            close(is);  
            close(bis);  
        }  
        return rv;  
    }  
	public static void close(Closeable closeable) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
            } catch (Exception e) {  
            	e.printStackTrace();
                logger.debug("Unable to close %s");  
            }  
        }  
    }  
}

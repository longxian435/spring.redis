package com.my.biz.sm.commons.redis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import redis.clients.jedis.JedisPool;


/**
 * redis操作客户端工具类
 * 
 * @author: hualong
 * @date 2014年2月17日 上午9:58:35
 */
public class JedisPoolFactory
{
    private static Logger logger = LoggerFactory.getLogger(JedisPoolFactory.class);


    private static String configFile = "jedisconf";

    private static JedisPool jedisPool = null;

    private static Map<String, JedisPool> JEDISPOOL_MAP = new HashMap<String, JedisPool>();

    static
    {
        loadXmlConfig();
    }

    /**
     * @author: smartlv
     * @date: 2014年2月17日下午3:36:30
     */
    private static void loadXmlConfig()
    {
        DefaultListableBeanFactory context = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:jedisconf.xml");
        initJedisPool(context);
        System.out.println("----------连接redis------------->");
    }

    private static void initJedisPool(DefaultListableBeanFactory context)
    {
        JEDISPOOL_MAP = (Map) context.getBean("jedisPoolMap");

        if (CollectionUtils.isEmpty(JEDISPOOL_MAP))
        {
        	logger.warn("no any jedis instance");
        }
    }

    public static JedisPool getJedisPool(String name)
    {
        return JEDISPOOL_MAP.get(name);
    }

    
}

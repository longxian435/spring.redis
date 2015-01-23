package com.my.biz.sm.commons.util;

import java.util.Collection;
import java.util.Map;

/**
 * 简单逻辑判断
 * 
 * @author: Hualong
 * @date 2013-3-7 下午05:30:13
 */
public class LogicUtil
{

    private static int ZERO = 0;
    private static String EMPTY_STRING = "";

    public static boolean isNullOrEmpty(String subject)
    {
        if (null == subject || EMPTY_STRING.equals(subject))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(String subject)
    {
        return !isNullOrEmpty(subject);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Map map)
    {
        if (null == map || ZERO == map.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotNullAndEmpty(Map map)
    {
        return !isNullOrEmpty(map);
    }

    public static boolean isNullOrEmpty(Collection<?> collection)
    {
        if (null == collection || ZERO == collection.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotNullAndEmpty(Collection collection)
    {
        return !isNullOrEmpty(collection);
    }

    public static boolean isNull(Object object)
    {
        if (object == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNull(Object Object)
    {
        return !isNull(Object);
    }

    public static boolean isNullOrEmpty(Object[] objects)
    {
        if (null == objects || ZERO == objects.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(Object[] objects)
    {
        return !isNullOrEmpty(objects);
    }

    public static boolean isNullOrEmpty(byte[] array)
    {
        if (null == array || ZERO == array.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(byte[] array)
    {
        return !isNullOrEmpty(array);
    }

    public static boolean isNullOrEmpty(short[] array)
    {
        if (null == array || ZERO == array.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(short[] array)
    {
        return !isNullOrEmpty(array);
    }

    public static boolean isNullOrEmpty(int[] array)
    {
        if (null == array || ZERO == array.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(int[] array)
    {
        return !isNullOrEmpty(array);
    }

    public static boolean isNotNullAndEmpty(long[] array)
    {
        return !isNullOrEmpty(array);
    }

    public static boolean isNullOrEmpty(long[] array)
    {
        if (null == array || ZERO == array.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNullOrEmpty(char[] array)
    {
        if (null == array || ZERO == array.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(char[] array)
    {
        return !isNullOrEmpty(array);
    }

    public static boolean isNullOrEmpty(float[] array)
    {
        if (null == array || ZERO == array.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(float[] array)
    {
        return !isNullOrEmpty(array);
    }

    public static boolean isNullOrEmpty(double[] array)
    {
        if (null == array || ZERO == array.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean isNotNullAndEmpty(double[] array)
    {
        return !isNullOrEmpty(array);
    }

}

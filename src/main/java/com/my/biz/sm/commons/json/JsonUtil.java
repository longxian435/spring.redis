package com.my.biz.sm.commons.json;

import org.codehaus.jackson.JsonNode;

import com.my.biz.sm.commons.string.StringUtil;

/**
 * 功能:主要获取请求json串中参数值
 * 
 * @author smartlv
 */
public class JsonUtil
{

    public static boolean getBooleanParameter(JsonNode body, String name, boolean defaults)
    {
        JsonNode jsonNode = body.get(name);
        if (jsonNode != null)
        {
            return jsonNode.asBoolean();
        }
        return defaults;
    }

    public static String getEncodeWMLStringParameter(JsonNode body, String name, String defaults)
    {
        return StringUtil.encodeWML(JsonUtil.getStringParameter(body, name, defaults));
    }

    public static int getIntParameter(JsonNode body, String name, int defaults)
    {
        JsonNode jsonNode = body.get(name);
        if (jsonNode != null)
        {
            try
            {
                return jsonNode.asInt();
            }
            catch (Exception e)
            {
            }
        }
        return defaults;
    }

    public static int getIntParameterCompatiable(JsonNode body, String[] paramNames, int discardvalue)
    {
        for (String paramName : paramNames)
        {
            int paramvalue = JsonUtil.getIntParameter(body, paramName, discardvalue);
            if (paramvalue != discardvalue)
            {
                return paramvalue;
            }
        }
        return discardvalue;
    }

    public static long getLongParameter(JsonNode body, String name, long defaults)
    {
        try
        {
            JsonNode jsonNode = body.get(name);
            if (jsonNode != null)
            {
                return jsonNode.asLong();
            }
        }
        catch (Throwable e)
        {
            return defaults;
        }
        return defaults;
    }

    public static String getSafeStringParameter(JsonNode body, String name, String defaults)
    {
        return StringUtil.removeInvalidWML(JsonUtil.getStringParameter(body, name, defaults));
    }

    public static String getStringParameter(JsonNode body, String name, String defaults)
    {
        JsonNode jsonNode = body.get(name);
        if (jsonNode != null)
        {
            return jsonNode.asText();
        }
        return defaults;
    }
}

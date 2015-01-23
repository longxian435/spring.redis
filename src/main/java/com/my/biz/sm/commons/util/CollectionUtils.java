package com.my.biz.sm.commons.util;

import static com.google.common.base.Preconditions.*;

import java.lang.reflect.Field;
import java.util.*;

import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.my.biz.sm.commons.string.StringUtil;

public class CollectionUtils
{
    /**
     * 检查当前集合是否有且仅有一个元素, 并且返回该元素.
     * 
     * @return 集合的唯一元素, 如果集合为空, 则返回null
     * @throws IllegalStateException
     *         如果集合元素大于1时抛出异常
     */
    public static <T> T checkAndReturnOnlyElement(Collection<T> collection)
    {
        if (collection == null || collection.size() == 0)
        {
            return null;
        }
        else if (collection.size() != 1)
        {
            throw new IllegalStateException("collection size is " + collection.size());
        }
        return collection.iterator().next();
    }

    /**
     * 对一个集合中的元素进行随机打乱, 然后取出指定数量的元素. 常用场景: 轮播一个,互联网产品爱好随机曝光
     * 
     * @param list
     *        原始集合
     * @param subSize
     *        最终要返回的个数
     * @param <V>
     *        元素类型
     * @return 进行打乱后的指定个数的元素
     */
    public static <V> List<V> shuffleAndSubList(List<V> list, int subSize)
    {
        if (list == null || list.size() == 0)
        {
            return null;
        }
        Collections.shuffle(list);
        list = list.size() > subSize ? list.subList(0, subSize) : list;
        return list;
    }

    public static Set<Long> convertStrings2Longs(Set<String> ids)
    {
        return Sets.newLinkedHashSet(Collections2.transform(ids, new Function<String, Long>()
        {
            @Override
            public Long apply(String idstr)
            {
                return StringUtil.convertLong(idstr, 0L);
            }
        }));
    }

    public static <T> List<T> convertList(List<String> ids, final Class<T> type, final T defaults)
    {
        if (!ClassUtils.isPrimitiveWrapper(type))
        {
            throw new RuntimeException("not support output T");
        }

        return Lists.newArrayList(Collections2.transform(ids, new Function<String, T>()
        {
            @SuppressWarnings("unchecked")
            @Override
            public T apply(String input)
            {
                if (Integer.class.isAssignableFrom(type))
                {
                    return (T) Integer.valueOf(StringUtil.convertInt(input, (Integer) defaults));
                }
                else if (Long.class.isAssignableFrom(type))
                {
                    return (T) Long.valueOf(StringUtil.convertLong(input, (Long) defaults));
                }
                else if (Boolean.class.isAssignableFrom(type))
                {
                    return (T) Boolean.valueOf(StringUtil.convertBoolean(input, (Boolean) defaults));
                }
                else if (Double.class.isAssignableFrom(type))
                {
                    return (T) Double.valueOf(StringUtil.convertDouble(input, (Double) defaults));
                }
                return null;
            }
        }));
    }

    /**
     * 计算集合1和集合2之间的交集, 最终结果中的元素既要在集合1中, 也要在集合2中.
     * <p/>
     * 举例: list1: [1, 3, 4], list2: [1, 5, 3], 则返回: [1, 3]
     * 
     * @param list1
     *        集合1
     * @param list2
     *        集合2
     * @param fieldNames
     *        比较的元素字段属性, 可以有0到多个. 如果不传入任何字段, 则用集合元素的equals方法比较
     * @param <E>
     *        元素类型
     * @return 两个集合的交集
     */
    public static <E> List<E> intersection(List<E> list1, List<E> list2, String... fieldNames)
    {
        checkArgument(list1 != null && list2 != null);
        List<E> result = new ArrayList<>();
        // 如果集合为空, 直接返回空集合
        if(list1.isEmpty() || list2.isEmpty()){
            return result;
        }
        // 如果不指定的字段属性, 则直接通过元素的equals方法比较
        if(fieldNames.length == 0)
        {
            for(E e : list1){
                if(list2.contains(e)){
                    result.add(e);
                }
            }
        }else{ // 比较指定的字段属性值
            List<Field> fields = new ArrayList<>(fieldNames.length);
            Class classz = (list1.get(0) != null) ? list1.get(0).getClass() : list2.get(0).getClass();
            for (String fieldName : fieldNames){
                Field field = ReflectionUtils.findField(classz, fieldName);
                field.setAccessible(true);
                fields.add(field);
            }
            for(E e1 : list1){
                for(E e2 : list2){
                    boolean equals = false;
                    for (Field field : fields){
                        equals = ReflectionUtils.getField(field, e1).equals(ReflectionUtils.getField(field, e2));
                        if(!equals){
                            break;
                        }
                    }
                    if(equals){
                        result.add(e1);
                        continue;
                    }
                }
            }
        }
        return  result;
    }

    /**
     * 计算集合1和集合2中不同的元素, 并返回这些元素. 最终返回的集合中元素要出现在集合1中, 但是不能出现在集合2中.
     * <p/>
     * 举例: list1: [1, 3, 4], list2: [1, 5, 3], 则返回: [4]
     * 
     * @param list1
     *        集合1
     * @param list2
     *        集合2
     * @param fieldNames
     *        比较的元素字段属性, 可以有0到多个. 如果不传入任何字段, 则用集合元素的equals方法比较
     * @param <E>
     *        元素类型
     * @return 两个集合不同的元素集合
     */
    public static <E> List<E> difference(List<E> list1, List<E> list2, String... fieldNames)
    {
        List<E> intersection = intersection(list1, list2, fieldNames);
        List<E> result = new ArrayList<>(list1.size() - intersection.size());
        for(E e1 : list1){
            boolean sameFlag = false;
            for(E e2 : intersection){
                if(e1 == e2){
                    sameFlag = true;
                    break;
                }
            }
            if(!sameFlag){
                result.add(e1);
            }
        }
        return result;
    }
}

package com.starter.medicalcommon.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

import static com.starter.medicalcommon.constant.Constant.DATE_PATTERN_FORMAT_FULL;
import static com.starter.medicalcommon.constant.Constant.DATE_PATTERN_FULL;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.beans.BeanUtils.getPropertyDescriptor;
import static org.springframework.beans.BeanUtils.getPropertyDescriptors;

/**
 * 此类的描述是：数据格式转换
 *
 * @author Starter
 * @date 2019-03-16 16:32
 **/
@Slf4j
public class BeanUtil {
    /**
     * 使用getter，setter进行属性拷贝所有的属性
     * {@link org.springframework.beans.BeanUtils#copyProperties} 只复制 public 的属性
     *
     * @param source 源对象
     * @param target 目标对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyAllPropertiesByGetterAndSetter(Object source, Object target) throws InvocationTargetException, IllegalAccessException {
        Map<String, PropertyDescriptor> sourcePdMap = Arrays.stream(getPropertyDescriptors(source.getClass())).collect(toMap(FeatureDescriptor::getName, pd -> pd));
        Map<String, PropertyDescriptor> targetPdMap = Arrays.stream(getPropertyDescriptors(target.getClass())).collect(toMap(FeatureDescriptor::getName, pd -> pd));

        for (String key : targetPdMap.keySet()) {
            if (sourcePdMap.containsKey(key)) {
                Object sourceValue = sourcePdMap.get(key).getReadMethod().invoke(source);

                if (null != sourceValue) {
                    Method writer = targetPdMap.get(key).getWriteMethod();

                    if (null != writer) {
                        writer.invoke(target, sourceValue);
                    }
                }
            }
        }
    }

    /**
     * 获取对象的属性
     *
     * @param object   对象
     * @param property 属性名
     * @param <T>      类型为泛型
     * @return 属性值
     */
    public static <T> T getProperty(Object object, String property) {
        if (object == null || StringUtils.isEmpty(property)) {
            return null;
        }

        try {
            PropertyDescriptor prop = getPropertyDescriptor(object.getClass(), property);
            Method getter = prop.getReadMethod();
            return (T) getter.invoke(object);
        } catch (Exception e) {
            log.error(JSON.toJSONString(e));
            return null;
        }
    }

    /**
     * 设置对象的属性
     *
     * @param object   对象
     * @param property 属性名
     * @param value    属性值
     * @param <T>      类型为泛型
     * @return true 设置成功，false 设置失败
     */
    public static <T> boolean setProperty(Object object, String property, T value) {
        if (object == null || StringUtils.isEmpty(property)) {
            return false;
        }

        try {
            PropertyDescriptor prop = getPropertyDescriptor(object.getClass(), property);
            Method setter = prop.getWriteMethod();
            setter.invoke(object, value);
            return true;
        } catch (Exception e) {
            log.error(JSON.toJSONString(e));
            return false;
        }
    }

    /**
     * 获取属性的类型
     *
     * @param object   对象
     * @param property 属性
     * @return 属性类型，判断属性类型可以使用 {@link Class#isAssignableFrom}
     */
    public static Class getPropertyType(Object object, String property) {
        try {
            PropertyDescriptor prop = getPropertyDescriptor(object.getClass(), property);
            Method getter = prop.getReadMethod();
            return getter.getReturnType();
        } catch (Exception e) {
            log.error(JSON.toJSONString(e));
            return null;
        }
    }

    /**
     * 将 Map 转换成对象
     *
     * @param properties       属性键值对
     * @param bean             对象
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static void populate(Map<String, ? extends Object> properties, Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        populate(properties, bean, Collections.EMPTY_MAP, false);
    }

    /**
     * 将 Map 转换成对象
     *
     * @param properties       属性键值对
     * @param bean             对象
     * @param keyReflectMap    Map中的键和对象的属性名映射表
     * @param useReflectKey    是否使用映射表的键值
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static void populate(Map<String, ? extends Object> properties, Object bean, Map<String, String> keyReflectMap, boolean useReflectKey) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //如果要求使用映射的键值，而且字段映射表为空，则返回
        if (useReflectKey && MapUtils.isEmpty(keyReflectMap)) {
            return;
        }

        if (bean != null && properties != null) {
            Iterator iterator = properties.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, ? extends Object> entry = (Map.Entry) iterator.next();
                String key = entry.getKey();

                if (!StringUtils.isEmpty(key)) {
                    //如果配置了映射字段，则从 map 中获取字段名
                    if (!MapUtils.isEmpty(keyReflectMap)) {
                        String reflectKey = keyReflectMap.get(key);

                        //如果要求使用映射键值，但是映射表中不存在，则 continue
                        if (useReflectKey && StringUtils.isEmpty(reflectKey)) {
                            continue;
                        }

                        if (!StringUtils.isEmpty(reflectKey)) {
                            key = reflectKey;
                        }
                    }

                    Class distType = getPropertyType(bean, key);
                    Object value = entry.getValue();

                    if (value != null && distType != null) {
                        if (Enum.class.isAssignableFrom(distType)) {
                            value = ((Object[]) distType.getMethod("values").invoke(distType))[value instanceof String
                                    ? Integer.valueOf((String) value)
                                    : (Integer) value];
                        } else if (Integer.class.isAssignableFrom(distType) && value instanceof String) {
                            value = Integer.valueOf((String) value);
                        } else if (Date.class.isAssignableFrom(distType) && value instanceof String) {
                            String time = (String) value;

                            if (time.length() > DATE_PATTERN_FULL.length()) {
                                time = time.substring(0, DATE_PATTERN_FULL.length());
                            }

                            value = DateUtil.convertLDTToDate(LocalDateTime.parse(time, DATE_PATTERN_FORMAT_FULL));
                        }
                    }

                    setProperty(bean, key, value);
                }
            }
        }
    }

    /**
     * 将对象转为 Map
     *
     * @param object 对象
     * @param map    键值对
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void describe(Object object, Map<String, Object> map) throws InvocationTargetException, IllegalAccessException {
        if (null == map) {
            return;
        }

        if (object != null) {
            PropertyDescriptor[] descriptors = org.springframework.beans.BeanUtils.getPropertyDescriptors(object.getClass());

            for (PropertyDescriptor descriptor : descriptors) {
                Method readMethod = descriptor.getReadMethod();

                if (null != readMethod) {
                    if ("class".equals(descriptor.getName())) {
                        continue;
                    }

                    map.put(descriptor.getName(), readMethod.invoke(object));
                }
            }
        }
    }

    /**
     * 如果必要，则将 Map 数据转换数据类型
     *
     * @param inputList 输入列表
     * @param clazz     结果数据的类型
     * @return 结果列表
     */
    public static List convertMapToEntityListIfNeeded(List<Object> inputList, Class<?> clazz) {
        if (CollectionUtils.isEmpty(inputList)) {
            return Lists.newArrayList();
        }

        Object object = inputList.get(0);

        if (clazz.isAssignableFrom(object.getClass())) {
            return inputList;
        } else if (object instanceof Map) {
            return inputList.stream()
                    .map(item -> JSON.parseObject(JSON.toJSONString(item), clazz))
                    .filter(Objects::nonNull)
                    .collect(toList());
        }

        return Lists.newArrayList();
    }
}
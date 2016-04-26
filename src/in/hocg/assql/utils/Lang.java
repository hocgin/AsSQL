package in.hocg.assql.utils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by hocgin on 16-4-26.
 */
public class Lang {

    public static RuntimeException makeThrow(String format, Object... args) {
        return new RuntimeException(String.format(format, args));
    }

    /**
     * 将多个数组，合并成一个数组。如果这些数组为空，则返回 null
     *
     * @param arrays
     *            数组对象
     * @return 合并后的数组对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] merge(T[]... arrays) {
        Queue<T> list = new LinkedList<T>();
        for (T[] ary : arrays)
            if (null != ary)
                for (T e : ary)
                    if (null != e)
                        list.add(e);
        if (list.isEmpty())
            return null;
        Class<T> type = (Class<T>) list.peek().getClass();
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }

    /**
     * 将一个对象添加成为一个数组的最后一个元素，从而生成一个新的数组
     *
     * @param e
     *            对象
     * @param eles
     *            数组
     * @return 新数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] arrayLast(T[] eles, T e) {
        try {
            if (null == eles || eles.length == 0) {
                T[] arr = (T[]) Array.newInstance(e.getClass(), 1);
                arr[0] = e;
                return arr;
            }
            T[] arr = (T[]) Array.newInstance(eles.getClass().getComponentType(), eles.length + 1);
            for (int i = 0; i < eles.length; i++) {
                arr[i] = eles[i];
            }
            arr[eles.length] = e;
            return arr;
        }
        catch (NegativeArraySizeException e1) {
            throw Lang.makeThrow("array length is exp", e1);
        }
    }


    /**
     * 获得一个对象的长度。它可以接受:
     * <ul>
     * <li>null : 0
     * <li>数组
     * <li>集合
     * <li>Map
     * <li>一般 Java 对象。 返回 1
     * </ul>
     * 如果你想让你的 Java 对象返回不是 1 ， 请在对象中声明 length() 函数
     *
     * @param obj
     * @return 对象长度
     */
    public static int length(Object obj) {
        if (null == obj)
            return 0;
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        } else if (obj instanceof Collection<?>) {
            return ((Collection<?>) obj).size();
        } else if (obj instanceof Map<?, ?>) {
            return ((Map<?, ?>) obj).size();
        }
        return 1;
    }

    /**
     * list 拼接 数组
     * @param ls
     * @param arr
     * @return
     */
    public static List listAddArr(List ls, Object... arr) {
        for (Object a : arr) {
            ls.add(a);
        }
        return ls;
    }

    public static Object[] removeChild(Object[] arr, Object... childs) {
        List<Object> ls = new ArrayList<Object>();
        boolean flag;
        for (Object a : arr) {
            flag = false;
            for (Object child : childs) {
                if (equals(a, child)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ls.add(a);
            }
        }
        return ls.toArray();
    }

    /**
     * 判断两个对象是否相等。 这个函数用处是:
     * <ul>
     * <li>可以容忍 null
     * <li>可以容忍不同类型的 Number
     * <li>对数组，集合， Map 会深层比较
     * </ul>
     * 当然，如果你重写的 equals 方法会优先
     *
     * @param a0
     *            比较对象1
     * @param a1
     *            比较对象2
     * @return 是否相等
     */
    public static boolean equals(Object a0, Object a1) {
        if (a0 == a1)
            return true;

        if (a0 == null || a1 == null)
            return false;

        // 简单的判断是否等于
        if (a0.equals(a1))
            return true;

        // 如果类型就不能互相转换，那么一定是错的
        if (!a0.getClass().isAssignableFrom(a1.getClass())
                && !a1.getClass().isAssignableFrom(a0.getClass()))
            return false;

        // Map
        if (a0 instanceof Map && a1 instanceof Map) {
            Map<?, ?> m1 = (Map<?, ?>) a0;
            Map<?, ?> m2 = (Map<?, ?>) a1;
            if (m1.size() != m2.size())
                return false;
            for (Map.Entry<?, ?> e : m1.entrySet()) {
                Object key = e.getKey();
                if (!m2.containsKey(key) || !equals(m1.get(key), m2.get(key)))
                    return false;
            }
            return true;
        }
        // 数组
        else if (a0.getClass().isArray() && a1.getClass().isArray()) {
            int len = Array.getLength(a0);
            if (len != Array.getLength(a1))
                return false;
            for (int i = 0; i < len; i++) {
                if (!equals(Array.get(a0, i), Array.get(a1, i)))
                    return false;
            }
            return true;
        }
        // 集合
        else if (a0 instanceof Collection && a1 instanceof Collection) {
            Collection<?> c0 = (Collection<?>) a0;
            Collection<?> c1 = (Collection<?>) a1;
            if (c0.size() != c1.size())
                return false;

            Iterator<?> it0 = c0.iterator();
            Iterator<?> it1 = c1.iterator();

            while (it0.hasNext()) {
                Object o0 = it0.next();
                Object o1 = it1.next();
                if (!equals(o0, o1))
                    return false;
            }

            return true;
        }

        // 一定不相等
        return false;
    }
}

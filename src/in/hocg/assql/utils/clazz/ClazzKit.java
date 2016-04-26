package in.hocg.assql.utils.clazz;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by hocgin on 16-4-26.
 */
public class ClazzKit {

    private Class clazz;

    private ClazzKit(Class clazz) {
        this.clazz = clazz;
    }

    public static ClazzKit me(Class clazz) {
        return new ClazzKit(clazz);
    }

    /**
     * 判断当前对象是否为一个类型。精确匹配，即使是父类和接口，也不相等
     *
     * @param type
     *            类型
     * @return 是否相等
     */
    public boolean is(Class<?> type) {
        return null != type && clazz == type;
    }

    /**
     * @param type
     *            类型或接口名
     * @return 当前对象是否为一个类型的子类，或者一个接口的实现类
     */
    public boolean isOf(Class<?> type) {
        return type.isAssignableFrom(clazz);
    }

    /**
     * @return 当前对象是否为字符串
     */
    public boolean isString() {
        return is(String.class);
    }

    /**
     * @return 当前对象是否为CharSequence的子类
     */
    public boolean isStringLike() {
        return CharSequence.class.isAssignableFrom(clazz);
    }

    /**
     * @return 当前对象是否简单的数值，比如字符串，布尔，字符，数字，日期时间等
     */
    public boolean isSimple() {
        return isStringLike() || isBoolean() || isChar() || isNumber() || isDateTimeLike();
    }

    /**
     * @return 当前对象是否为字符
     */
    public boolean isChar() {
        return is(char.class) || is(Character.class);
    }

    /**
     * @return 当前对象是否为枚举
     */
    public boolean isEnum() {
        return clazz.isEnum();
    }

    /**
     * @return 当前对象是否为布尔
     */
    public boolean isBoolean() {
        return is(boolean.class) || is(Boolean.class);
    }

    /**
     * @return 当前对象是否为浮点
     */
    public boolean isFloat() {
        return is(float.class) || is(Float.class);
    }

    /**
     * @return 当前对象是否为双精度浮点
     */
    public boolean isDouble() {
        return is(double.class) || is(Double.class);
    }

    /**
     * @return 当前对象是否为整型
     */
    public boolean isInt() {
        return is(int.class) || is(Integer.class);
    }

    /**
     * @return 当前对象是否为整数（包括 int, long, short, byte）
     */
    public boolean isIntLike() {
        return isInt() || isLong() || isShort() || isByte() || is(BigDecimal.class);
    }

    /**
     * @return 当前类型是不是接口
     */
    public boolean isInterface() {
        return clazz.isInterface();
    }

    /**
     * @return 当前对象是否为小数 (float, dobule)
     */
    public boolean isDecimal() {
        return isFloat() || isDouble();
    }

    /**
     * @return 当前对象是否为长整型
     */
    public boolean isLong() {
        return is(long.class) || is(Long.class);
    }

    /**
     * @return 当前对象是否为短整型
     */
    public boolean isShort() {
        return is(short.class) || is(Short.class);
    }

    /**
     * @return 当前对象是否为字节型
     */
    public boolean isByte() {
        return is(byte.class) || is(Byte.class);
    }

    /**
     * @return 当前对象是否为数字
     */
    public boolean isNumber() {
        return Number.class.isAssignableFrom(clazz)
                || clazz.isPrimitive()
                && !is(boolean.class)
                && !is(char.class);
    }

    /**
     * @return 当前对象是否在表示日期或时间
     */
    public boolean isDateTimeLike() {
        return Calendar.class.isAssignableFrom(clazz)
                || java.util.Date.class.isAssignableFrom(clazz)
                || java.sql.Date.class.isAssignableFrom(clazz)
                || java.sql.Time.class.isAssignableFrom(clazz);
    }
}

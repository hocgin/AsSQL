package in.hocg.assql.cri;

import in.hocg.assql.utils.clazz.ClazzKit;
import in.hocg.assql.utils.Lang;
import in.hocg.assql.utils.StrKit;

/**
 * Created by hocgin on 16-4-26.
 */
public class Exps {

    public static IsNull isNull(String name) {
        return new IsNull(name);
    }

    public static InLong inLong(String name, long... ids) {
        return new InLong(name, ids);
    }
    public static InString inStr(String name, String... ids) {
        return new InString(name, ids);
    }

    public static Like like(String name, String value) {
        return Like.create(name, value, true);
    }

    public static Between between(String name, Object min, Object max) {
        return new Between(name, min, max);
    }

    public static Eq eq(String name, Object val) {
        return new Eq(name, val);
    }

    public static Gt gt(String name, Object val) {
        return new Gt(name, val);
    }

    public static Lt lt(String name, Object val) {
        return new Lt(name, val);
    }

    public static Like like(String name, String value, boolean ignoreCase) {
        return Like.create(name, value, ignoreCase);
    }

    public static Sql sql(String sql, Object... params) {
        return new Sql(sql, params);
    }

    public static SqlExpression create(String name, String op, Object value) {
        op = StrKit.trim(op.toUpperCase());
        SqlExpression re;
        // NULL
        if(value == null) { // ("xx", "", null)
            // IS NULL
            if ("=".equals(op) || "IS".equals(op) || "NOT IS".equals(op) || "IS NOT".equals(op)) {
                re = isNull(name);
            }
            // !!!
            else {
                throw Lang.makeThrow("null can only use 'IS' or 'NOT IS'");
            }
            return re.setNot(op.startsWith("NOT") || op.endsWith("NOT"));
        }
        // IN
        else if ("IN".equals(op) || "NOT IN".equals(op)) {
            Class<?> type = value.getClass();
            // 数组
            if (type.isArray()) {
                re = _evalRange(ClazzKit.me(type), name, value);
            } else {
                throw Lang.makeThrow("param should is Array or Collection type");
            }
            return re.setNot(op.startsWith("NOT"));
        }
        // LIKE || IS
        else if ("LIKE".equals(op) || "NOT LIKE".equals(op)) {
            String v = value.toString();
            Like lk;
            if (v.length() == 1) {
                lk = like(name, v);
            } else {
                lk = like(name, v.substring(1, v.length() - 1));
                lk.left(v.substring(0, 1));
                lk.right(v.substring(v.length() - 1, v.length()));
            }
            return lk.ignoreCase(false).setNot(op.startsWith("NOT"));
        }
        // =  !=
        else if ("=".equals(op) || "!=".equals(op) || "<>".equals(op)) {
            re = eq(name, value).setNot(!op.startsWith("="));
            return re;
        }
        // between and 参数为数组
        else if ("BETWEEN".equals(op)) {
            Object[] values = (Object[])value;
            return between(name, values[0], values[1]);
        }
        // > <=
        else if (">".equals(op) || "<=".equals(op)) {
            re = gt(name, value).setNot(op.endsWith("="));
            return re;
        }
        // >=
        else if ("<".equals(op) || ">=".equals(op)) {
            re = lt(name, value).setNot(op.endsWith("="));
            return re;
        }
        // other
        else {
            throw Lang.makeThrow("SQL is error");
        }
    }

    private static SqlExpression _evalRange(ClazzKit clazzKit, String name, Object value) {
        if (clazzKit.isInt() || clazzKit.isLong()) {
            return inLong(name, (long[]) value);
        }
        return inStr(name, (String[]) value);
    }
}

package in.hocg.assql.utils;


/**
 * Created by hocgin on 16-4-26.
 */
public class StrKit {

    /**
     * 如果此字符串为 null 或者全为空白字符，则返回 true
     *
     * @param cs
     *            字符串
     * @return 如果此字符串为 null 或者全为空白字符，则返回 true
     */
    public static boolean isBlank(CharSequence cs) {
        if (null == cs)
            return true;
        int length = cs.length();
        for (int i = 0; i < length; i++) {
            if (!(Character.isWhitespace(cs.charAt(i))))
                return false;
        }
        return true;
    }
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 将给定字符串，变成 "xxx...xxx" 形式的字符串
     *
     * @param str
     *            字符串
     * @param len
     *            最大长度
     * @return 紧凑的字符串
     */
    public static String brief(String str, int len) {
        if (StrKit.isBlank(str) || (str.length() + 3) <= len)
            return str;
        int w = len / 2;
        int l = str.length();
        return str.substring(0, len - w) + " ... " + str.substring(l - w);
    }

    /**
     * 去前后空格
     * @param cs 字符串
     * @return
     */
    public static String trim(CharSequence cs) {
        if(cs == null) {
            return null;
        } else {
            int length = cs.length();
            if(length == 0) {
                return cs.toString();
            } else {
                int l = 0;
                int last = length - 1;

                int r;
                for(r = last; l < length && Character.isWhitespace(cs.charAt(l)); ++l) {
                    ;
                }

                while(r > l && Character.isWhitespace(cs.charAt(r))) {
                    --r;
                }

                return l > r?"":(l == 0 && r == last?cs.toString():cs.subSequence(l, r + 1).toString());
            }
        }
    }
}

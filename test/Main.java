import in.hocg.assql.Condition;
import in.hocg.assql.cri.Cnd;
import in.hocg.assql.cri.Wrap;
import in.hocg.jlog.JLog;

import java.util.Arrays;

/**
 * Created by hocgin on 16-4-26.
 */
public class Main {
    public static void main(String[] args) {
        JLog.init();

        Condition cnd = Cnd.where("name", ">", "xiaoming")
                .andIn("age", 12, 56, 89)
                .and(new Wrap("weight", ">", "60").orIn("height", 160, 170, 180))
                .groupBy("id")
                .having(Cnd.where("12", ">", "12"))
                .desc("age");

        String sql = String.format("%s from %s where %s", "select *", "tableName", cnd.toSql());
        JLog.v(sql);
        JLog.e(Arrays.asList(cnd.getParams()).toString());
    }
}

package in.hocg.assql.cri;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public interface MarkPojo {
//    public void joinSql(Model model, StringBuilder sb);
    // 不检查表字段
    void joinSql(StringBuilder sb);

    void joinParam(ArrayList<Object> params);
}

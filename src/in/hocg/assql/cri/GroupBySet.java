package in.hocg.assql.cri;

import in.hocg.assql.utils.Lang;
import in.hocg.assql.Condition;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public class GroupBySet implements MarkPojo {
    private String[] names;

    private Condition having;

    public GroupBySet(String... names) {
        if (Lang.length(names) == 0)
            throw Lang.makeThrow("NULL for GroupBy");
        this.names = names;
    }

    public void having(Condition cnd) {
        having = cnd;
    }

    @Override
    public void joinSql(StringBuilder sb) {
        sb.append(" GROUP BY ");
        for (String name : names) {
            sb.append(name);
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        if (having != null) {
            sb.append(" HAVING ");
            String sql = having.toSql().trim();
            if (sql.length() > 5 && "WHERE".equalsIgnoreCase(sql.substring(0, 5)))
                sql = sql.substring(5).trim();
            sb.append(sql);
        }
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
        params.addAll(having.getParams());
    }
}

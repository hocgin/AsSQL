package in.hocg.assql.cri;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public class InLong extends AbstractSqlExpression {

    protected long[] ids;

    protected InLong(String name, long... ids) {
        super(name);
        this.ids = ids;
        this.not = false;
    }

    @Override
    public void joinSql(StringBuilder sb) {
        if (ids.length > 0) {
            sb.append(getName());
            if (not)
                sb.append(" NOT");
            sb.append(" IN (");
            for (int i = 0; i < ids.length; i++)
                sb.append("?,");
            sb.setCharAt(sb.length() - 1, ')');
        } else
            ;//OK,无需添加.
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
        for (long i : ids) {
            params.add(i);
        }
    }
}

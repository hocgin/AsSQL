package in.hocg.assql.cri;

import in.hocg.assql.utils.Lang;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public class Sql extends AbstractSqlExpression {

    private Object[] params;

    protected Sql(String sql, Object... params) {
        super(sql);
        this.params = params;
    }

    @Override
    public void joinSql(StringBuilder sb) {
        sb.append(" ").append(getName());
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
        params = (ArrayList<Object>) Lang.listAddArr(params, this.params);
    }
}

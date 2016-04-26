package in.hocg.assql.cri;

import in.hocg.assql.Condition;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public abstract class AbstractSqlExpression implements SqlExpression, Condition {
    protected boolean not;
    private String name;

    protected AbstractSqlExpression(String name) {
        this.name = name;
    }

    AbstractSqlExpression not() {
        this.not = true;
        return this;
    }

    protected String getName() {
        return name;
    }

    @Override
    public SqlExpression setNot(boolean not) {
        this.not = not;
        return this;
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
    }

    @Override
    public String toSql() {
        return null;
    }

    @Override
    public ArrayList<Object> getParams() {
        return null;
    }

    // 校验name参数
//    protected String _fmtcol(Model model) {
//        if (Arrays.asList(model.getAttrNames()).contains(getName())) {
//            return getName();
//        } else {
//            throw Lang.makeThrow("SQL filed is not found");
//        }
//    }
}

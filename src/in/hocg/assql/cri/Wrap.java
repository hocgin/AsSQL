package in.hocg.assql.cri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocgin on 16-4-26.
 */
public class Wrap implements SqlExpression {

    private List<SqlExpression> wrap;

    @Override
    public SqlExpression setNot(boolean not) {
        return this;
    }

    public Wrap(String name, String op, String value) {
        wrap = new ArrayList<SqlExpression>();
        wrap.add(Exps.create(name, op, value));
    }

    // ** and
    public Wrap and(String name, String op, Object value) {
        _and().add(Exps.create(name, op, value));
        return this;
    }

    public Wrap and(String sql, Object... params) {
        _and().add(Exps.sql(sql, params));
        return this;
    }

    public Wrap and(Wrap wrap) {
        _and().add(wrap);
        return this;
    }

    public Wrap andIn(String name, long... params) {
        _and().add(Exps.inLong(name, params));
        return this;
    }

    public Wrap andIn(String name, String... params) {
        _and().add(Exps.inStr(name, params));
        return this;
    }

    public Wrap or(String name, String op, Object value) {
        _or().add(Exps.create(name, op, value));
        return this;
    }

    public Wrap or(String sql, Object... params) {
        _or().add(Exps.sql(sql, params));
        return this;
    }


    public Wrap or(Wrap wrap) {
        _or().add(wrap);
        return this;
    }

    public Wrap orIn(String name, long... params) {
        _or().add(Exps.inLong(name, params));
        return this;
    }

    public Wrap orIn(String name, String... params) {
        _or().add(Exps.inStr(name, params));
        return this;
    }

    @Override
    public void joinSql(StringBuilder sb) {
        sb.append("( ");
        for (MarkPojo m : wrap) {
            m.joinSql(sb);
        }
        sb.append(" )");
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
        for (MarkPojo m : wrap) {
            m.joinParam(params);
        }
    }

    private List<SqlExpression> _and() {
        wrap.add(new Static("AND"));
        return wrap;
    }

    private List<SqlExpression> _or() {
        wrap.add(new Static("OR"));
        return wrap;
    }
}

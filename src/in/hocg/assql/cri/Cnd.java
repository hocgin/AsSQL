package in.hocg.assql.cri;

import in.hocg.assql.Condition;
import in.hocg.assql.GroupBy;
import in.hocg.assql.OrderBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocgin on 16-4-26.
 */
public class Cnd implements OrderBy, GroupBy {

    private List<SqlExpression> wheres = new ArrayList<SqlExpression>();
    private OrderBySet orderBySet = new OrderBySet();
    private GroupBySet groupBy;
    private ArrayList<Object> params;
    private StringBuilder sql;

    private Cnd(String sql, Object... params) {
        wheres.add(Exps.sql(sql, params));
    }

    private Cnd(String name, String op, Object value) {
        wheres.add(Exps.create(name, op, value));
    }

    public static Cnd where(String name, String op, Object value) {
        return new Cnd(name, op, value);
    }
    public static Cnd where(String sql, Object... params) {
        return new Cnd(sql, params);
    }

    // ** and
    public Cnd and(String name, String op, Object value) {
        _and().add(Exps.create(name, op, value));
        return this;
    }

    public Cnd and(String sql, Object... params) {
        _and().add(Exps.sql(sql, params));
        return this;
    }

    public Cnd and(Wrap wrap) {
        _and().add(wrap);
        return this;
    }

    public Cnd andIn(String name, long... params) {
        _and().add(Exps.inLong(name, params));
        return this;
    }

    public Cnd andIn(String name, String... params) {
        _and().add(Exps.inStr(name, params));
        return this;
    }

    public Cnd or(String name, String op, Object value) {
        _or().add(Exps.create(name, op, value));
        return this;
    }

    public Cnd or(String sql, Object... params) {
        _or().add(Exps.sql(sql, params));
        return this;
    }


    public Cnd or(Wrap wrap) {
        _or().add(wrap);
        return this;
    }

    public Cnd orIn(String name, long... params) {
        _or().add(Exps.inLong(name, params));
        return this;
    }

    public Cnd orIn(String name, String... params) {
        _or().add(Exps.inStr(name, params));
        return this;
    }

    @Override
    public OrderBy desc(String name) {
        orderBySet.desc(name);
        return this;
    }

    @Override
    public OrderBy asc(String name) {
        orderBySet.asc(name);
        return this;
    }

    public OrderBy orderBy(String name, String dir) {
        if("asc".equalsIgnoreCase(dir)) {
            this.asc(name);
        } else {
            this.desc(name);
        }

        return this;
    }

    @Override
    public String toSql() {
        // where
        sql = new StringBuilder();
        if (!wheres.isEmpty()) {
//            sql.append(" WHERE ");
            for (SqlExpression sqlExpression : wheres) {
                sqlExpression.joinSql(sql);
            }
        }
        // group by
        if (groupBy != null) {
            groupBy.joinSql(sql);
        }
        // order by
        orderBySet.joinSql(sql);
        return sql.toString();
    }

    @Override
    public ArrayList<Object> getParams() {
        params = new ArrayList<Object>();
        if (!wheres.isEmpty()) {
            for (SqlExpression sqlExpression : wheres) {
                sqlExpression.joinParam(params);
            }
        }
        // group by -> having
        if (groupBy != null) {
            groupBy.joinParam(params);
        }
        return this.params;
    }

    @Override
    public GroupBy groupBy(String... names) {
        groupBy = new GroupBySet(names);
        return this;
    }

    @Override
    public GroupBy having(Condition cnd) {
        groupBy.having(cnd);
        return this;
    }

    private List<SqlExpression> _and() {
        wheres.add(new Static("AND"));
        return wheres;
    }

    private List<SqlExpression> _or() {
        wheres.add(new Static("OR"));
        return wheres;
    }
}

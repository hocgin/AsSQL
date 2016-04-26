package in.hocg.assql.cri;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public class Like extends AbstractSqlExpression {
    protected static Like create(String name, String value, boolean ignoreCase) {
        Like like = new Like(name);
        like.value = value;
        like.ignoreCase = ignoreCase;
        like.left = "%";
        like.right = "%";
        return like;
    }

    private String value;

    private boolean ignoreCase;

    private String left;

    private String right;

    private Like(String name) {
        super(name);
    }

    @Override
    public void joinSql(StringBuilder sb) {
        String colName = getName();
        if (not)
            sb.append(" NOT ");
        if (ignoreCase)
            sb.append("LOWER(").append(colName).append(") LIKE LOWER(?)");
        else
            sb.append(colName).append(" LIKE ?");
    }

    public Like left(String left) {
        this.left = left;
        return this;
    }

    public Like right(String right) {
        this.right = right;
        return this;
    }

    public Like ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
        params.add((null == left ? "" : left) + value + (null == right ? "" : right));
    }
}
package in.hocg.assql.cri;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public class Between extends AbstractSqlExpression {
    private Object min;
    private Object max;

    protected Between(String name, Object min, Object max) {
        super(name);
        this.max = max;
        this.min = min;
    }

    @Override
    public void joinSql(StringBuilder sb) {
        if (not)
            sb.append(" NOT ");
        sb.append(getName()).append(' ').append("BETWEEN").append(' ').append('?').append(" AND ").append('?');
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
        params.add(min);
        params.add(max);
    }
}

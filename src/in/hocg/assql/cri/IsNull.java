package in.hocg.assql.cri;

/**
 * Created by hocgin on 16-4-26.
 */
public class IsNull extends AbstractSqlExpression {

    protected IsNull(String name) {
        super(name);
        this.not = false;
    }

    @Override
    public void joinSql(StringBuilder sb) {
        sb.append(getName());
        sb.append(" IS ");
        if (not)
            sb.append("NOT ");
        sb.append("NULL ");
    }
}

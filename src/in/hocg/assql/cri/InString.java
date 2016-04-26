package in.hocg.assql.cri;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hocgin on 16-4-26.
 */
public class InString extends AbstractSqlExpression {

    protected String[] names;

    protected InString(String name, String... names) {
        super(name);
        this.names = names;
        this.not = false;
    }

    public void joinSql(StringBuilder sb) {
        if (names.length > 0) {
            sb.append(getName());
            if (not)
                sb.append(" NOT");
            sb.append(" IN (");
            for (int i = 0; i < names.length; i++)
                sb.append("?,");
            sb.setCharAt(sb.length() - 1, ')');
        } else
            ;//OK,无需添加.
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
        params.addAll(Arrays.asList(names));
    }
}

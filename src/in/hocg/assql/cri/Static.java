package in.hocg.assql.cri;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public class Static implements SqlExpression {

    private String str;

    public Static(String str) {
        this.str = str;
    }

    @Override
    public SqlExpression setNot(boolean not) {
        return this;
    }

    public String toString() {
        return ' ' + str + ' ';
    }

    @Override
    public void joinSql(StringBuilder sb) {
        sb.append(' ').append(str).append(' ');
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
    }
}

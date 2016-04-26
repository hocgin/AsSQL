package in.hocg.assql.cri;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public class OrderByItem implements MarkPojo {

    private String name;

    private String by;

    public OrderByItem(String name, String by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void joinSql(StringBuilder sb) {
        sb.append(name).append(' ').append(by);
    }

    @Override
    public void joinParam(ArrayList<Object> params) {

    }
}

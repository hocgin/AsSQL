package in.hocg.assql.cri;

import in.hocg.assql.OrderBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocgin on 16-4-26.
 */
public class OrderBySet implements OrderBy, MarkPojo{
    private List<OrderByItem> list;
    OrderBySet() {
        list = new ArrayList<OrderByItem>(3);
    }

    @Override
    public OrderBy asc(String name) {
        OrderByItem asc = new OrderByItem(name, "ASC");
        list.add(asc);
        return this;
    }

    @Override
    public OrderBy desc(String name) {
        OrderByItem desc = new OrderByItem(name, "DESC");
        list.add(desc);
        return this;
    }

    @Override
    public String toSql() {
        StringBuilder sb = new StringBuilder();
        joinSql(sb);
        return sb.toString();
    }

    @Override
    public ArrayList<Object> getParams() {
        return null;
    }

    @Override
    public void joinSql(StringBuilder sb) {
        if (!list.isEmpty()) {
            sb.append(" ORDER BY ");
            for (OrderByItem obi : list) {
                obi.joinSql(sb);
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
        } else
            ;
    }

    @Override
    public void joinParam(ArrayList<Object> params) {
    }

    public List<OrderByItem> getItems() {
        return list;
    }
}

package in.hocg.assql;

import java.util.ArrayList;

/**
 * Created by hocgin on 16-4-26.
 */
public interface Condition {
    String toSql();
    ArrayList<Object> getParams();
}

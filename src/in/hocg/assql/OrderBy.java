package in.hocg.assql;

/**
 * Created by hocgin on 16-4-26.
 */
public interface OrderBy extends Condition {
    OrderBy asc(String name);
    OrderBy desc(String name);
}

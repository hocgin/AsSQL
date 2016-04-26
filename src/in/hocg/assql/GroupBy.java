package in.hocg.assql;

/**
 * Created by hocgin on 16-4-26.
 */
public interface GroupBy extends OrderBy {
    GroupBy groupBy(String... names);
    GroupBy having(Condition condition);
}

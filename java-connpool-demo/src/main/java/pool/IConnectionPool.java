package pool;

/**
 * Created by cc on 2017/7/29.
 */
public interface IConnectionPool<T> {

    void init(int maxTotal, int maxWait);

    T getConnection();

    T createConnection();

    void release(T conn) throws Exception;

    void close();

    Integer getTotalConnSize();

    Integer getFreeConnSize();

}

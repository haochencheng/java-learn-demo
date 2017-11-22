package pers.cc.transfer.connpool;

/**
 * 连接池接口
 * 
 * @author cc
 *
 * @param <T>
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

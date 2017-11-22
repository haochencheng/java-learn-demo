package pool.factory;

import pool.ConnectionType;
import pool.IConnectionPool;

/**
 * Created by cc on 2017/7/30.
 */
public interface IConnectionPoolFactory {

    IConnectionPool getConnPool(Enum<ConnectionType> type);

}

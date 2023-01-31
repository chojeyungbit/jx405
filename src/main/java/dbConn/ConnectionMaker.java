package dbConn;

import java.sql.Connection;

public interface ConnectionMaker {
    public Connection makeConnection();
}

package connector;

import java.sql.Connection;

public interface ConnectionMaker {
    public Connection makeConnection();
}

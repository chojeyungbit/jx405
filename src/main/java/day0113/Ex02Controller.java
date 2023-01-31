package day0113;

import dbConn.ConnectionMaker;
import dbConn.MySqlConnectionMaker;
import dbConn.OracleConnectionMaker;

import java.sql.Connection;

public class Ex02Controller {
    public static void main(String[] args) {
        ConnectionMaker maker = new OracleConnectionMaker();
        Connection connection = maker.makeConnection();


    }
}

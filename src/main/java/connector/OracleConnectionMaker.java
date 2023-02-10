package connector;

import java.sql.Connection;

public class OracleConnectionMaker implements ConnectionMaker{
    private final String ADDRESS = "192.168.0.2";
    private final String USERNAME = "ADMIN";
    private final String PASSWORD = "1234";
    @Override
    public Connection makeConnection() {
        System.out.println("오라클 데이터베이스 연결 시도");
        System.out.println("오라클 주소: " + ADDRESS);
        System.out.println("오라클 아이디: " + USERNAME);
        System.out.println("오라클 비밀번호: " + PASSWORD);
        System.out.println("연결 성공");
        return null;
    }
}

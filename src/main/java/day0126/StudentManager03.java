package day0126;

import dbConn.ConnectionMaker;
import dbConn.MySqlConnectionMaker;
import viewer.StudentViewer;

public class StudentManager03 {
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        StudentViewer studentViewer = new StudentViewer(connectionMaker);
        studentViewer.showMenu();
    }
}

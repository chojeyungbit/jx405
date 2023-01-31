package day0126;

import java.sql.*;

public class Ex01Connection {
    public static void main(String[] args) {
        String address = "jdbc:mysql://localhost/basic";
        String username = "root";
        String password = "1111";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(address, username, password);

            System.out.println("MySql 연결 성공!!!");

            // 1. insert
            String query = "INSERT INTO `student`(`name`, `korean`, `english`, `math`) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, "최준서");
            pstmt.setInt(2, 91);
            pstmt.setInt(3, 91);
            pstmt.setInt(4, 92);

            pstmt.executeUpdate();
            System.out.println("INSERT 성공!!!");

            // 2. read
            query = "SELECT * FROM `student`";
            ResultSet resultSet = pstmt.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int korean = resultSet.getInt("korean");
                int english = resultSet.getInt("english");
                int math = resultSet.getInt("math");

                System.out.printf("번호: %d번 이름: %s 국어: %d점 영어: %d점 수학: %d점\n",
                        id, name, korean, english, math);
            }

            query = "SELECT * FROM `student` WHERE `id` = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, 6);

            resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int korean = resultSet.getInt("korean");
                int english = resultSet.getInt("english");
                int math = resultSet.getInt("math");

                System.out.printf("번호: %d번 이름: %s 국어: %d점 영어: %d점 수학: %d점\n",
                        id, name, korean, english, math);
            }

            System.out.println("READ 종료!!!");

            query = "UPDATE `student` SET `name` = ? WHERE `id` = ?";
            pstmt = connection.prepareStatement(query);

            pstmt.setString(1, "정의경");
            pstmt.setInt(2, 14);

            pstmt.executeUpdate();

            System.out.println("UPDATE 종료!!!");

            query = "DELETE FROM `student` WHERE `name` = ?";
            pstmt = connection.prepareStatement(query);

            pstmt.setString(1, "최준서");

            pstmt.executeUpdate();

            System.out.println("DELETE 종료!!!");

            resultSet.close();
            pstmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}













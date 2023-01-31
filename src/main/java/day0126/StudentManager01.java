package day0126;

import util.ScannerUtil;

import java.sql.*;
import java.util.Scanner;

// 학생관리 프로그램 ver 1.0
// 무한루프를 사용하고
// 메소드 분리가 안된 원시적인 형태의 관리 프로그램
public class StudentManager01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String address = "jdbc:mysql://localhost/basic";
        String username = "root";
        String password = "1111";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(address, username, password);

            while(true){
                String message = "1. 입력 2. 출력 3. 종료";
                int userChoice = ScannerUtil.nextInt(scanner, message);

                if(userChoice == 1){
                    message = "이름을 입력해주세요.";
                    String name = ScannerUtil.nextLine(scanner, message);

                    message = "국어 점수를 입력해주세요.";
                    int korean = ScannerUtil.nextInt(scanner, message);

                    message = "영어 점수를 입력해주세요.";
                    int english = ScannerUtil.nextInt(scanner, message);

                    message = "수학 점수를 입력해주세요.";
                    int math = ScannerUtil.nextInt(scanner, message);

                    String query = "INSERT INTO `student`(`name`, `korean`, `english`, `math`) VALUES(?, ?, ?, ?)";

                    PreparedStatement pstmt = connection.prepareStatement(query);

                    pstmt.setString(1, name);
                    pstmt.setInt(2, korean);
                    pstmt.setInt(3, english);
                    pstmt.setInt(4, math);

                    pstmt.executeUpdate();

                    pstmt.close();

                } else if(userChoice == 2){
                    String query = "SELECT * FROM `student`";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    ResultSet resultSet = pstmt.executeQuery();

                    while(resultSet.next()){
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int korean = resultSet.getInt("korean");
                        int english = resultSet.getInt("english");
                        int math = resultSet.getInt("math");

                        int sum = korean + english + math;
                        double average = sum / 3.0;

                        System.out.printf("번호: %d번 이름: %s 국어: %d점 영어: %d점 수학: %d점\n",
                                id, name, korean, english, math);
                        System.out.printf("총점: %d점 평균: %.2f점\n", sum, average);
                    }


                } else if(userChoice == 3){
                    System.out.println("사용해주셔서 감사합니다.");
                    break;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

















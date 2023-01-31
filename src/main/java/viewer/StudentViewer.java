package viewer;

import controller.StudentController;
import dbConn.ConnectionMaker;
import model.StudentDTO;
import util.ScannerUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentViewer {
    private Connection connection;
    private Scanner scanner;

    public StudentViewer(ConnectionMaker connectionMaker) {
        scanner = new Scanner(System.in);
        connection = connectionMaker.makeConnection();
    }

    public void showMenu() {
        String message = "1. 입력 2. 출력 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                add();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("VIEWER CONNECTION ERROR");
            e.printStackTrace();
        }
    }

    private void add() {
        String message;
        StudentDTO s = new StudentDTO();
        message = "학생의 이름을 입력해주세요.";
        s.setName(ScannerUtil.nextLine(scanner, message));

        message = "학생의 국어 점수를 입력해주세요.";
        s.setKorean(ScannerUtil.nextInt(scanner, message, 0, 100));

        message = "학생의 영어 점수를 입력해주세요.";
        s.setEnglish(ScannerUtil.nextInt(scanner, message, 0, 100));

        message = "학생의 수학 점수를 입력해주세요.";
        s.setMath(ScannerUtil.nextInt(scanner, message, 0, 100));

        StudentController controller = new StudentController(connection);
        controller.insert(s);
    }

    private void printList() {
        StudentController controller = new StudentController(connection);
        ArrayList<StudentDTO> list = controller.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 학생이 존재하지 않습니다.");
        } else {
            for (StudentDTO s : list) {
                System.out.printf("%d. %s\n", s.getId(), s.getName());
            }

            String message = "상세보기할 학생의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);

            while (userChoice != 0 && controller.selectOne(userChoice) == null) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }

        }
    }

    private void printOne(int id) {
        StudentController controller = new StudentController(connection);
        StudentDTO s = controller.selectOne(id);

        if (s == null) {
            System.out.println("해당 번호는 유효하지 않습니다.");
            printList();
        } else {
            System.out.printf("번호: %d번 이름: %s 국어: %d점 영어: %d점 수학: %d점\n",
                    s.getId(), s.getName(), s.getKorean(), s.getEnglish(), s.getMath());

            String message = "1. 수정 2. 삭제 3. 목록으로";
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                //update(id);
            } else if (userChoice == 2) {
                //delete(id);
            } else if (userChoice == 3) {
                printList();
            }
        }

    }
}















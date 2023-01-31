package day0110;
// 전역 변수(Global Variable)

import util.ScannerUtil;

import java.util.Scanner;

public class Ex05GlobalVar {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int SUBJECT_SIZE = 3;
    public static final int SCORE_MIN = 0;
    public static final int SCORE_MAX = 100;
    public static Student student = null;

    public static void main(String[] args) {
        while (true) {
            String message = "1. 입력 2. 출력 3. 종료";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                insertInfo();
            } else if (userChoice == 2) {
                if (student != null) {
                    printInfo();
                } else {
                    System.out.println("아직 입력된 학생의 정보가 존재하지 않습니다.");
                }
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
        SCANNER.close();

    }

    public static void insertInfo() {
        student = new Student();
        String message;

        message = "학생의 번호를 입력해주세요.";
        student.id = ScannerUtil.nextInt(SCANNER, message);

        message = "학생의 이름을 입력해주세요.";
        student.name = ScannerUtil.nextLine(SCANNER, message);

        message = "학생의 국어 점수를 입력해주세요.";
        student.korean = ScannerUtil.nextInt(SCANNER, message, SCORE_MIN, SCORE_MAX);

        message = "학생의 영어 점수를 입력해주세요.";
        student.english = ScannerUtil.nextInt(SCANNER, message, SCORE_MIN, SCORE_MAX);

        message = "학생의 수학 점수를 입력해주세요.";
        student.math = ScannerUtil.nextInt(SCANNER, message, SCORE_MIN, SCORE_MAX);
    }

    public static void printInfo() {
        System.out.printf("번호: %d번 이름: %s\n", student.id, student.name);
        System.out.printf("국어: %d점 영어: %d점 수학: %d점\n", student.korean, student.english, student.math);
        System.out.printf("총점: %d점 평균: %.2f점\n", calculateSum(), calculateAverage());
    }

    public static int calculateSum() {
        return student.korean + student.english + student.math;
    }

    public static double calculateAverage() {
        return (double) calculateSum() / SUBJECT_SIZE;
    }
}














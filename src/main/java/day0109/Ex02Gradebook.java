package day0109;
// 사용자로부터 번호, 이름, 국어, 영어, 수학 점수를 입력 받아서
// 각각의 정보를 다음과 같이 출력되는 프로그램을 작성하시오.
// 단, 입력에 관한 메소드, 출력에 관한 메소드, 총점 및 평균을 계산 하는 메소드를 따로 분리하시오.

// 출력 방법:
// 번호: ###번 이름: ###
// 국어: ##점 영어: ##점 수학: ##점
// 총점: ##점 평균: ##.######점

import util.ScannerUtil;

import java.util.Scanner;

public class Ex02Gradebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 입력
        // 1-1. 번호 입력
        int id = getId(scanner);

        // 1-2. 이름 입력
        String name = getName(scanner);

        // 1-3. 국어 점수 입력
        int korean = getKorean(scanner);

        // 1-4. 영어 점수 입력
        int english = getEnglish(scanner);

        // 1-5. 수학 점수 입력
        int math = getMath(scanner);


        // 2. 출력
        printInfo(id, name, korean, english, math);


        scanner.close();
    }

    public static int getId(Scanner scanner) {
        String message = "학생의 번호를 입력해주세요.";

        return ScannerUtil.nextInt(scanner, message);
    }

    public static String getName(Scanner scanner) {
        String temp;
        String message = "학생의 이름을 입력해주세요.";

        temp = ScannerUtil.nextLine(scanner, message);

        return temp;
    }

    public static int getKorean(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 국어 점수를 입력해주세요.";
        System.out.println(message);
        System.out.print("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.print("> ");
            temp = scanner.nextInt();
        }

        return temp;
    }

    public static int getEnglish(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 영어 점수를 입력해주세요.";
        System.out.println(message);
        System.out.print("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.print("> ");
            temp = scanner.nextInt();
        }

        return temp;
    }

    public static int getMath(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 수학 점수를 입력해주세요.";
        System.out.println(message);
        System.out.print("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.print("> ");
            temp = scanner.nextInt();
        }

        return temp;
    }

    public static void printInfo(int id, String name, int korean, int english, int math) {
        System.out.println("번호: " + id + "번 이름: " + name);
        System.out.printf("국어: %d점 영어: %d점 수학:%d점\n", korean, english, math);
        System.out.printf("총점: %d점 평균: %f점\n", calculateSum(korean, english, math), calculateAverage(korean, english, math));
    }

    public static int calculateSum(int korean, int english, int math) {
        return korean + english + math;
    }

    public static double calculateAverage(int korean, int english, int math) {
        final int SUBJECT_SIZE = 3;
        return calculateSum(korean, english, math) / (double) SUBJECT_SIZE;
    }
}

















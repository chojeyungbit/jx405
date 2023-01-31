package day0112;

import day0111.Board;
import util.ArrayUtil;
import util.ScannerUtil;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Ex01Board {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static Board[] array = new Board[0];
    private static int nextId = 1;

    public static void main(String[] args) {
        showMenu();

        SCANNER.close();
    }

    private static void showMenu() {
        while (true) {
            String message = "1. 입력 2. 목록 보기 3. 종료";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                writeBoard();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private static void writeBoard() {
        Board b = new Board();

        b.setId(nextId++);

        String message;

        message = "글의 작성자를 입력해주세요.";
        b.setWriter(ScannerUtil.nextLine(SCANNER, message));

        message = "글의 제목을 입력해주세요.";
        b.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "글의 내용을 입력해주세요.";
        b.setContent(ScannerUtil.nextLine(SCANNER, message));

        array = ArrayUtil.add(array, b);
    }

    private static void printList() {
        if (ArrayUtil.isEmpty(array)) {
            System.out.println("아직 등록된 글이 존재하지 않습니다.");
        } else {
            for (Board b : array) {
                System.out.printf("%d. %s\n", b.getId(), b.getTitle());
            }

            String message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            Board b = new Board();
            b.setId(userChoice);

            while (userChoice != 0 && !ArrayUtil.contains(array, b)) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
                b.setId(userChoice);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private static void printOne(int id) {
        Board temp = new Board();
        temp.setId(id);

        Board b = ArrayUtil.get(array, ArrayUtil.indexOf(array, temp));

        b.printBoard();

        String message = "1. 수정 2. 삭제 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);

        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        } else {
            printList();
        }
    }

    private static void update(int id) {
        Board temp = new Board();
        temp.setId(id);

        Board b = ArrayUtil.get(array, ArrayUtil.indexOf(array, temp));

        String message = "새로운 제목을 입력해주세요.";
        b.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 내용을 입력해주세요.";
        b.setContent(ScannerUtil.nextLine(SCANNER, message));

        printOne(id);
    }

    private static void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            Board b = new Board();
            b.setId(id);
            array = ArrayUtil.remove(array, b);
            printList();
        } else {
            printOne(id);
        }
    }
}

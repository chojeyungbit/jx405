package day0110;
// 로또 시뮬레이텨
// ver4.0
// 사용자로부터 총 몇게임할지 입력 받은 후에
// 해당 게임마다 각각 자동/수동 입력을 받아서
// 알맞게 처리하는 프로그램

import util.ScannerUtil;

import java.util.Scanner;

public class Ex11Lotto04 {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int ARRAY_LENGTH = 6;
    public static final int NUMBER_MIN = 1;
    public static final int NUMBER_MAX = 45;

    public static void main(String[] args) {
        String message = "총 몇게임을 하시겠습니까?";
        int gameSize = ScannerUtil.nextInt(SCANNER, message);

        int[][] userNumbers = new int[gameSize][ARRAY_LENGTH];

        setNumbers(userNumbers);

        int[] computerNumbers = new int[ARRAY_LENGTH];
        Ex10Lotto03.setAutoNumbers(computerNumbers);
        Ex10Lotto03.sort(computerNumbers);

        printResult(userNumbers, computerNumbers);
    }

    public static void setNumbers(int[][] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            System.out.println((i + 1) + "번째 게임");
            String message = "1. 자동 2. 수동";
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 2);

            if (userChoice == 1) {
                Ex10Lotto03.setAutoNumbers(arrays[i]);
            } else {
                Ex10Lotto03.setManualNumbers(arrays[i]);
            }

            Ex10Lotto03.sort(arrays[i]);
        }
    }

    public static void printResult(int[][] userNumbers, int[] computerNumbers) {
        System.out.print("컴퓨터 숫자: ");
        printArray(computerNumbers);
        System.out.println();
        System.out.println("사용자 숫자");
        for (int i = 0; i < userNumbers.length; i++) {
            System.out.printf("%d번 게임: ", i + 1);
            printArray(userNumbers[i]);
            System.out.printf(" - %d개\n", countSame(computerNumbers, userNumbers[i]));
        }
    }

    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%2d", array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public static int countSame(int[] computerNumbers, int[] userNumbers) {
        int count = 0;
        for (int i = 0; i < computerNumbers.length; i++) {
            if (Ex10Lotto03.contains(userNumbers, computerNumbers[i])) {
                count++;
            }
        }

        return count;
    }
}

















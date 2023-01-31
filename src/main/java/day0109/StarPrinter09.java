package day0109;

import java.util.Scanner;

public class StarPrinter09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("별 찍기 9번");
        System.out.println("출력할 줄 수를 입력해주세요.");
        System.out.print("> ");
        int lineNumber = scanner.nextInt();

        int totalHeight = 2 * lineNumber - 1;

        for (int i = 1; i <= totalHeight; i++) {
            String stars = "";
            int spaceWidth = 0;
            int starWidth = 0;

            if (i < lineNumber) {
                // 윗부분
                spaceWidth = lineNumber - i;
                starWidth = 2 * i - 1;
            } else {
                int lowerI = i - lineNumber + 1;

                // 아랫부분
                spaceWidth = lowerI - 1;
                starWidth = 2 *(lineNumber - lowerI) + 1;
            }

            for (int j = 1; j <= spaceWidth; j++) {
                stars += " ";
            }

            for (int j = 1; j <= starWidth; j++) {
                stars += "*";
            }

            System.out.println(stars);
        }

        scanner.close();
    }
}

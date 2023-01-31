package day0109;

import java.util.Scanner;

public class StarPrinter06Hard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in );
        System.out.println("별 찍기 6번 어려운 버젼");
        System.out.println("출력할 줄 수를 입력해주세요.");
        System.out.print("> ");
        int lineNumber = scanner.nextInt();

        for(int i = 1; i <= lineNumber; i++){
            String stars = "";

            for(int j = 1; j <= i - 1; j++){
                stars += " ";
            }

            for(int j = 1; j <= 2 * (lineNumber - i) + 1; j++){
                stars += "*";
            }

            System.out.println(stars);
        }

        scanner.close();
    }
}

package day0109;

import java.util.Scanner;

public class StarPrinter06Easy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in );
        System.out.println("별 찍기 6번");
        System.out.println("출력할 줄 수를 입력해주세요.");
        System.out.print("> ");
        int lineNumber = scanner.nextInt();

        for(int i = lineNumber; i >= 1; i--){
            String stars = "";

            for(int j = 1; j <= lineNumber - i; j++){
                stars += " ";
            }

            for(int j = 1; j <= 2 * i - 1; j++){
                stars += "*";
            }

            System.out.println(stars);
        }

        scanner.close();
    }
}

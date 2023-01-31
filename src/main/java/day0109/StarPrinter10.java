package day0109;

import java.util.Scanner;

public class StarPrinter10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("별찍기 10번");
        System.out.println("출력할 줄 수를 입력해주세요.");
        System.out.print("> ");
        int lineNumber = scanner.nextInt();

        int totalHeight = 2 * lineNumber - 1;

        for(int i = 1; i <= totalHeight; i++){
            String stars = "";
            if(i == 1 || i == totalHeight){
                for(int j = 1; j <= totalHeight; j++){
                    stars += "*";
                }
            } else {
                int starWidth = 0;

                if(i < lineNumber){
                    starWidth = lineNumber - i + 1;
                } else {
                    int lowerI = i - lineNumber + 1;
                    starWidth = lowerI;
                }

                int spaceWidth = totalHeight - 2 * starWidth;

                for(int j = 1; j <= starWidth; j++){
                    stars += "*";
                }

                for(int j = 1; j <= spaceWidth; j++){
                    stars += " ";
                }

                for(int j = 1; j <= starWidth; j++){
                    stars += "*";
                }
            }


            System.out.println(stars);
        }

        scanner.close();
    }
}
















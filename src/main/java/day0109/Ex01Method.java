package day0109;

public class Ex01Method {
    public static void main(String[] args) {
        printIntro();
        printIntro();
        printIntro();
        printIntro();

        int a = 3;
        int b = 4;
        printBigger(a, b);
        a = 4;
        b = 3;
        printBigger(a, b);
        printBigger(4, 4);

        a = 5;
        b = 3;
        int result = calculatePower(a, b);
        System.out.println("a의 b승: " + result);

        a = 4;
        b = 4;
        result = calculatePower(a, b);
        System.out.println("a의 b승: " + result);

        System.out.println("a의 b승: " + calculatePower(1, 7));
    }

    public static void printIntro() {
        System.out.println("Ex01Method");
        System.out.println("작성자: 조재영");
        System.out.println("작성일: 2023년 01월 09일");
        System.out.println("내용: 메소드에 대한 설명 및 예제");
    }

    public static void printBigger(int number1, int number2) {
        if (number1 > number2) {
            System.out.println("a가 b보다 더 큽니다.");
        } else {
            System.out.println("b가 a보다 더 크거나 같습니다.");
        }
    }

    public static int calculatePower(int a, int b){
        int result = 1;

        for(int i = 1; i <= b; i++){
            result *= a;
        }

        return result;
    }
}

















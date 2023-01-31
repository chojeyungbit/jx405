package day0110;
// Random 클래스
// Random 클래스는 우리가 랜덤숫자(=난수)가 필요할 때 사용하는 클래스이다.
import java.util.Random;
public class Ex07Random {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());
        System.out.println(random.nextDouble());

        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());

        System.out.println(random.nextInt(100) + 40);
        System.out.println(random.nextInt(100) + 40);
        System.out.println(random.nextInt(100) + 40);
        System.out.println(random.nextInt(100) + 40);
    }
}

package day0113;

public class Rabbit implements IAnimal{
    public void makeSound(){
        System.out.println("토끼 토끼");
    }

    public void move(){
        System.out.println("깡총 깡총");
    }

    public void eat() {
        System.out.println("풀을 뜯어먹습니다.");
    }

    public void swim() {
        System.out.println("용궁에 갑니다.");
    }
}

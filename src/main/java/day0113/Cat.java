package day0113;

public class Cat implements IAnimal{

    @Override
    public void makeSound() {
        System.out.println("냐옹");
    }

    @Override
    public void move() {
        System.out.println("슬금슬금");
    }

    @Override
    public void eat() {
        System.out.println("생선을 먹습니다.");
    }
}

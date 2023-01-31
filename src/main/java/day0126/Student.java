package day0126;

public class Student {
    private int id;
    private String name;
    private int korean;
    private int english;
    private int math;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKorean() {
        return korean;
    }

    public void setKorean(int korean) {
        this.korean = korean;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void printInfo(){
        int sum = korean + english + math;
        double average = sum / 3.0;

        System.out.printf("번호: %d번 이름: %s 국어: %d점 영어: %d점 수학: %d점\n",
                id, name, korean, english, math);
        System.out.printf("총점: %d점 평균: %.2f점\n", sum, average);
    }
}

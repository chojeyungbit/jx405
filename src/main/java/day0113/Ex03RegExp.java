package day0113;
// 정규표현식(Regular Expression)

public class Ex03RegExp {
    public static void main(String[] args) {
        // 1. 숫자 체크
        System.out.println("1. 숫자 체크");
        String pattern = "\\d";
        System.out.println("\"1\": "+"1".matches(pattern));
        System.out.println("\"a\": "+"a".matches(pattern));
        System.out.println("--------------------------------------------------\n");
        // 2. 알파벳 체크
        System.out.println("2. 알파벳 체크");
        pattern = "[A-Za-z]";
        System.out.println("\"1\": "+"1".matches(pattern));
        System.out.println("\"a\": "+"a".matches(pattern));
        System.out.println("--------------------------------------------------\n");
        // 3. 여러개의 숫자로만 이루어졌는지 체크
        System.out.println("3. 여러개의 숫자 체크");
        pattern = "\\d+";
        System.out.println("\"1\": " + "1".matches(pattern));
        System.out.println("\"123\": " + "123".matches(pattern));
        System.out.println("--------------------------------------------------\n");
        // 4. 복합적으로 섞어서 사용해보기
        System.out.println("4. 복합적인 사용");
        pattern = "010-\\d{4}-\\d{4}";
        System.out.println("\"010-1234-1234\": " + "010-1234-1234".matches(pattern));
        System.out.println("\"010-9999-8888\": " + "010-9999-8888".matches(pattern));
        System.out.println("\"02-111-2222\": " + "02-111-2222".matches(pattern));
        System.out.println("\"서울시 강남구 강남동 강남로\": " + "서울시 강남구 강남동 강남로".matches(pattern));
        System.out.println("\"admin@gmail.com\": " + "admin@gmail.com".matches(pattern));
        System.out.println("--------------------------------------------------\n");
        pattern = "\\d{3,6}";
        System.out.println("\"1\": " + "1".matches(pattern));
        System.out.println("\"135\": " + "135".matches(pattern));
        System.out.println("\"13579\": " + "13579".matches(pattern));
        System.out.println("\"123456\": " + "123456".matches(pattern));
        System.out.println("\"1234567\": " + "1234567".matches(pattern));

    }
}

















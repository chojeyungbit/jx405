package day0113;

import model.UserDTO;

public class Ex05ToString {
    public static void main(String[] args) {
        int number = 4;
        System.out.println(number);

        String str = "abcd";
        System.out.println(str);

        UserDTO u = new UserDTO();
        u.setId(1);
        u.setUsername("a");
        u.setPassword("a");
        u.setNickname("사용자1");

        System.out.println(u);


    }
}

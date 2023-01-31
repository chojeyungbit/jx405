package day0131;

import model.SampleDTO;

public class Ex01Lombok {
    public static void main(String[] args) {
        SampleDTO s = new SampleDTO();
        s.setId(1);
        s.setSomething("Something");

        SampleDTO s2 = new SampleDTO();
        s2.setId(1);
        s2.setSomething("Something");

        System.out.println(s.equals(s2));

    }
}

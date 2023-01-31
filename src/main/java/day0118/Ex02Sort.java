package day0118;

import model.BoardDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Ex02Sort {
    public static void main(String[] args) {
        ArrayList<BoardDTO> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            BoardDTO b = new BoardDTO();
            b.setId(random.nextInt(500) + 1);
            b.setTitle("제목 " + b.getId());
            list.add(b);
        }

        Collections.sort(list);
        Collections.reverse(list);

        for (BoardDTO b : list) {
            System.out.printf("%d. %s\n", b.getId(), b.getTitle());
        }
    }
}














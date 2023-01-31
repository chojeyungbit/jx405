package controller;

import model.BoardDTO;

import java.util.ArrayList;
import java.util.Date;

public class BoardController {
    private ArrayList<BoardDTO> list;
    private int nextId;

    public BoardController() {
        list = new ArrayList<>();
        nextId = 1;

        for (int i = 1; i <= 4; i++) {
            BoardDTO b = new BoardDTO();
            b.setTitle("제목 " + i);
            b.setWriterId(1);
            b.setWriterNickname("일반회원1");
            b.setContent(i + "번째 게시글의 내용입니다.");

            add(b);
        }
    }

    public void add(BoardDTO boardDTO) {
        boardDTO.setId(nextId++);
        boardDTO.setEntryDate(new Date());
        boardDTO.setModifyDate(new Date());
        list.add(boardDTO);
    }

    public BoardDTO selectOne(int id) {
        BoardDTO temp = new BoardDTO(id);
        if (list.contains(temp)) {
            return new BoardDTO(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<BoardDTO> selectAll() {
        ArrayList<BoardDTO> temp = new ArrayList<>();
        for (BoardDTO b : list) {
            temp.add(new BoardDTO(b));
        }

        return temp;
    }

    public void update(BoardDTO boardDTO) {
        boardDTO.setModifyDate(new Date());
        list.set(list.indexOf(boardDTO), boardDTO);
    }

    public void delete(int id) {
        list.remove(new BoardDTO(id));
    }
}

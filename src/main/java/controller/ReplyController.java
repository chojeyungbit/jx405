package controller;

import model.ReplyDTO;

import java.util.ArrayList;
import java.util.Date;

public class ReplyController {
    private ArrayList<ReplyDTO> list;
    private int nextId;

    public ReplyController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void add(ReplyDTO replyDTO) {
        replyDTO.setId(nextId++);
        replyDTO.setEntryDate(new Date());
        list.add(replyDTO);
    }

    public ArrayList<ReplyDTO> selectAll(int boardId) {
        ArrayList<ReplyDTO> temp = new ArrayList<>();
        for (ReplyDTO r : list) {
            if (r.getBoardId() == boardId) {
                temp.add(new ReplyDTO(r));
            }
        }

        return temp;
    }

    public ReplyDTO selectOne(int id) {
        ReplyDTO r = new ReplyDTO();
        r.setId(id);
        if (list.contains(r)) {
            return new ReplyDTO(list.get(list.indexOf(r)));

        } else {
            return null;
        }
    }

    public void update(ReplyDTO replyDTO) {
        replyDTO.setModifyDate(new Date());
        list.set(list.indexOf(replyDTO), replyDTO);
    }

    public void delete(int id) {
        ReplyDTO r = new ReplyDTO();
        r.setId(id);
        list.remove(r);
    }
}

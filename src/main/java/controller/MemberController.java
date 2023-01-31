package controller;

import model.MemberDTO;

import java.util.ArrayList;

public class MemberController {
    private ArrayList<MemberDTO> list;
    private int nextId;

    private final int LEVEL_GENERAL = 1;
    private final int LEVEL_CRITIC = 2;
    private final int LEVEL_ADMIN = 3;

    public MemberController() {
        list = new ArrayList<>();
        nextId = 1;

        MemberDTO m = new MemberDTO();
        m.setId(nextId++);
        m.setUsername("a");
        m.setPassword("a");
        m.setNickname("관리자 1");
        m.setLevel(LEVEL_ADMIN);

        list.add(m);

        addSampleData();
    }

    private void addSampleData() {
        // 평론가 아이디 추가
        for (int i = 1; i <= 3; i++) {
            MemberDTO m = new MemberDTO();
            m.setId(nextId++);
            m.setUsername("C" + i);
            m.setPassword("1");
            m.setNickname("평론가 " + i);
            m.setLevel(LEVEL_CRITIC);
            list.add(m);
        }

        // 일반사용자 아이디 추가
        for (int i = 1; i <= 3; i++) {
            MemberDTO m = new MemberDTO();
            m.setUsername("G" + i);
            m.setPassword("1");
            m.setNickname("일반 사용자 " + i);
            add(m);
        }
    }

    public void add(MemberDTO memberDTO) {
        memberDTO.setId(nextId++);
        memberDTO.setLevel(LEVEL_GENERAL);
        list.add(memberDTO);
    }

    public boolean validateUsername(String username) {
        if (username.equalsIgnoreCase("X")) {
            return false;
        }

        for (MemberDTO m : list) {
            if (username.equalsIgnoreCase(m.getUsername())) {
                return false;
            }
        }

        return true;
    }

    public MemberDTO auth(String username, String password) {
        for (MemberDTO m : list) {
            if (username.equalsIgnoreCase(m.getUsername()) && password.equals(m.getPassword())) {
                return new MemberDTO(m);
            }
        }
        return null;
    }

    public void update(MemberDTO memberDTO) {
        list.set(list.indexOf(memberDTO), memberDTO);
    }

    public void delete(int id) {
        list.remove(new MemberDTO(id));
    }

    public void rankUp(int id, int level) {
        MemberDTO m = new MemberDTO(id);
        list.get(list.indexOf(m)).setLevel(level);
    }

    public String selectNickname(int id) {
        MemberDTO m = new MemberDTO(id);
        int index = list.indexOf(m);
        return list.get(index).getNickname();
    }
}

















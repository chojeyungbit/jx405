package controller;

import model.FilmDTO;

import java.util.ArrayList;

public class FilmController {
    private ArrayList<FilmDTO> list;
    private int nextId;

    public FilmController() {
        list = new ArrayList<>();
        nextId = 1;

        for (int i = 1; i <= 4; i++) {
            FilmDTO f = new FilmDTO();
            f.setTitle("제목 " + i);
            f.setSummary("줄거리 " + i);
            f.setRating(i);

            add(f);
        }
    }

    public void add(FilmDTO filmDTO) {
        filmDTO.setId(nextId++);

        list.add(filmDTO);
    }

    public ArrayList<FilmDTO> selectAll() {
        ArrayList<FilmDTO> temp = new ArrayList<>();
        for (FilmDTO f : list) {
            temp.add(new FilmDTO(f));
        }

        return temp;
    }

    public FilmDTO selectOne(int id) {
        FilmDTO temp = new FilmDTO(id);
        if (list.contains(temp)) {
            int index = list.indexOf(temp);
            return new FilmDTO(list.get(index));
        }
        return null;
    }

    public void update(FilmDTO filmDTO) {
        int index = list.indexOf(filmDTO);
        list.set(index, filmDTO);
    }

    public void delete(int id) {
        FilmDTO f = new FilmDTO(id);
        list.remove(f);
    }
}

















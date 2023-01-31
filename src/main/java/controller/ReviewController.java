package controller;

import model.ReviewDTO;

import java.util.ArrayList;

public class ReviewController {
    private ArrayList<ReviewDTO> list;
    private int nextId;

    public ReviewController() {
        list = new ArrayList<>();

        nextId = 1;
    }

    public void add(ReviewDTO reviewDTO) {
        reviewDTO.setId(nextId++);

        list.add(reviewDTO);
    }

    public ArrayList<ReviewDTO> selectAll(int filmId) {
        ArrayList<ReviewDTO> temp = new ArrayList<>();
        for (ReviewDTO r : list) {
            if (r.getFilmId() == filmId) {
                temp.add(new ReviewDTO(r));
            }
        }

        return temp;
    }

    public ArrayList<ReviewDTO> selectGeneralList(int filmId) {
        ArrayList<ReviewDTO> temp = new ArrayList<>();

        for (ReviewDTO r : selectAll(filmId)) {
            if (r.getReview() == null) {
                temp.add(new ReviewDTO(r));
            }
        }

        return temp;
    }

    public ArrayList<ReviewDTO> selectCriticList(int filmId) {
        ArrayList<ReviewDTO> temp = new ArrayList<>();
        for (ReviewDTO r : selectAll(filmId)) {
            if (r.getReview() != null) {
                temp.add(new ReviewDTO(r));
            }
        }

        return temp;
    }

    public ReviewDTO selectOne(int id) {
        ReviewDTO reviewDTO = new ReviewDTO(id);
        if (list.contains(reviewDTO)) {
            int index = list.indexOf(reviewDTO);
            return new ReviewDTO(list.get(index));
        }
        return null;
    }

    public void update(ReviewDTO reviewDTO) {
        int index = list.indexOf(reviewDTO);
        list.set(index, reviewDTO);
    }

    public void delete(int id) {
        list.remove(new ReviewDTO(id));
    }

    public void delete(int filmId, int writerId) {
        list.remove(selectOne(filmId, writerId));
    }

    public double calculateAverage(ArrayList<ReviewDTO> list) {

        int sum = 0;
        for (ReviewDTO r : list) {
            sum += r.getScore();
        }

        return (double) sum / list.size();
    }

    public boolean validateReview(int filmId, int writerId) {
        return selectOne(filmId, writerId) == null;
    }

    public ReviewDTO selectOne(int filmId, int writerId) {
        for (ReviewDTO r : selectAll(filmId)) {
            if (r.getWriterId() == writerId) {
                return new ReviewDTO(r);
            }
        }
        return null;
    }

    public void deleteByWriterId(int writerId) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWriterId() == writerId) {
                list.remove(i);
                i--;
            }
        }
    }
}

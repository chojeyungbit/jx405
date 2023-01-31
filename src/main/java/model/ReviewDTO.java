package model;

public class ReviewDTO {
    private int id;
    private int writerId;
    private int filmId;
    private int score;
    private String review;

    public ReviewDTO() {

    }

    public ReviewDTO(int id) {
        this.id = id;
    }

    public ReviewDTO(ReviewDTO origin) {
        id = origin.id;
        writerId = origin.writerId;
        filmId = origin.filmId;
        score = origin.score;
        review = origin.review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean equals(Object o) {
        if (o instanceof ReviewDTO) {
            ReviewDTO r = (ReviewDTO) o;
            return id == r.id;
        }

        return false;
    }
}

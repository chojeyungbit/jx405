package model;

public class FilmDTO {
    private int id;
    private String title;
    private String summary;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean equals(Object o) {
        if (o instanceof FilmDTO) {
            FilmDTO f = (FilmDTO) o;
            return id == f.id;
        }
        return false;
    }

    public FilmDTO() {

    }

    public FilmDTO(int id) {
        this.id = id;
    }

    public FilmDTO(FilmDTO origin) {
        id = origin.id;
        title = origin.title;
        summary = origin.summary;
        rating = origin.rating;
    }
}

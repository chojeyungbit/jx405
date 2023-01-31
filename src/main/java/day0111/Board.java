package day0111;

public class Board {
    private int id;
    private String title;
    private String writer;
    private String content;

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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void printBoard() {
        System.out.println("-----------------------------------");
        System.out.println(title);
        System.out.println("-----------------------------------");
        System.out.println("번호: " + id);
        System.out.println("작성자: " + writer);
        System.out.println("-----------------------------------");
        System.out.println(content);
    }

    public boolean equals(Object o) {
        if (o instanceof Board) {
            Board b = (Board) o;
            return id == b.id;
        }

        return false;
    }
}

















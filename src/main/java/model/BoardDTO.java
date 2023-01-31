package model;

import java.util.Date;

public class BoardDTO implements Comparable<BoardDTO> {
    private int id;
    private String title;
    private int writerId;
    private String writerNickname;
    private String content;
    private Date entryDate;
    private Date modifyDate;

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

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

    public String getWriterNickname() {
        return writerNickname;
    }

    public void setWriterNickname(String writerNickname) {
        this.writerNickname = writerNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(BoardDTO b) {

        return this.id - b.id;
    }

    public boolean equals(Object o) {
        if (o instanceof BoardDTO b) {
            return id == b.id;
        }
        return false;
    }

    public BoardDTO(BoardDTO origin) {
        id = origin.id;
        title = origin.title;
        writerId = origin.writerId;
        writerNickname = origin.writerNickname;
        content = origin.content;
        entryDate = origin.entryDate;
        modifyDate = origin.modifyDate;
    }

    public BoardDTO() {

    }

    public BoardDTO(int id) {
        this.id = id;
    }
}

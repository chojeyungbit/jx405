package model;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int id;
    private String title;
    private int writerId;
    private String writerNickname;
    private String content;
    private Date entryDate;
    private Date modifyDate;

}

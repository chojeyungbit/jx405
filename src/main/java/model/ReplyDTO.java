package model;

import lombok.Data;

import java.util.Date;
@Data
public class ReplyDTO {
    private int id;
    private String content;
    private int boardId;
    private int writerId;
    private String writerNickname;

    private Date entryDate;
    private Date modifyDate;

}

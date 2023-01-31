package day0112;

import viewer.BoardViewer;
import viewer.ReplyViewer;
import viewer.UserViewer;

import java.util.Scanner;

public class Ex03Board02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserViewer userViewer = new UserViewer(scanner);
        BoardViewer boardViewer = new BoardViewer(scanner);
        ReplyViewer replyViewer = new ReplyViewer(scanner);

        userViewer.setBoardViewer(boardViewer);
        userViewer.setReplyViewer(replyViewer);

        boardViewer.setUserViewer(userViewer);
        boardViewer.setReplyViewer(replyViewer);

        userViewer.showIndex();
    }
}

package viewer;

import controller.ReplyController;
import model.ReplyDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ReplyViewer {
    private Scanner SCANNER;
    private ReplyController replyController;
    private UserDTO logIn;

    public ReplyViewer(Scanner scanner) {
        this.SCANNER = scanner;
        replyController = new ReplyController();
    }

    public void setLogIn(UserDTO logIn) {
        this.logIn = logIn;
    }

    public void printAll(int boardId) {
        ArrayList<ReplyDTO> list = replyController.selectAll(boardId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMd H:m:s");
        for (ReplyDTO r : list) {
            String date = sdf.format(r.getEntryDate());
            if (r.getModifyDate() != null) {
                date = sdf.format(r.getModifyDate());
            }
            System.out.printf("%d. %s(%s): %s\n", r.getId(), r.getWriterNickname(), date, r.getContent());
        }
    }

    public void showMenu(int boardId) {
        String message = "1. 댓글 등록 2. 댓글 수정 3. 댓글 삭제 4. 뒤로 가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            writeReply(boardId);
        } else if (userChoice != 4) {
            message = "수정/삭제할 댓글 번호나 뒤로 가실려면 0을 입력해주세요.";
            int targetId = ScannerUtil.nextInt(SCANNER, message);
            while (userChoice != 0 && replyController.selectOne(targetId) == null) {
                System.out.println("잘못 입력하셨습니다.");
                targetId = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice == 2) {
                updateReply(targetId, boardId);
            } else if (userChoice == 3) {
                deleteReply(targetId, boardId);
            }
        }
    }

    private void writeReply(int boardId) {
        ReplyDTO r = new ReplyDTO();
        r.setBoardId(boardId);
        r.setWriterId(logIn.getId());
        r.setWriterNickname(logIn.getNickname());

        String message = "댓글 내용을 입력해주세요.";
        r.setContent(ScannerUtil.nextLine(SCANNER, message));

        replyController.add(r);
    }

    private void deleteReply(int id, int boardId) {
        ReplyDTO r = replyController.selectOne(id);
        if (r != null && r.getBoardId() == boardId && r.getWriterId() == logIn.getId()) {
            String message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("Y")) {
                replyController.delete(id);
            }
        }
    }

    private void updateReply(int id, int boardId) {
        ReplyDTO r = replyController.selectOne(id);
        if (r != null && r.getBoardId() == boardId && r.getWriterId() == logIn.getId()) {
            String message = "새로운 내용을 입력해주세요.";
            r.setContent(ScannerUtil.nextLine(SCANNER, message));

            replyController.update(r);
        }
    }
}


















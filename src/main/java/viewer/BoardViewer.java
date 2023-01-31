package viewer;

import controller.BoardController;
import model.BoardDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardViewer {
    private BoardController boardController;
    private UserViewer userViewer;


    private ReplyViewer replyViewer;
    private final Scanner SCANNER;
    private final String DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private UserDTO logIn;

    public BoardViewer(Scanner scanner) {
        boardController = new BoardController();
        SCANNER = scanner;
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

    public void setReplyViewer(ReplyViewer replyViewer) {
        this.replyViewer = replyViewer;
    }

    public void setLogIn(UserDTO logIn) {
        this.logIn = logIn;
    }

    public void showMenu() {
        String message = "1. 새 글 작성하기 2. 글 목록 보기 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                writeBoard();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void writeBoard() {
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setWriterId(logIn.getId());
        boardDTO.setWriterNickname(logIn.getNickname());

        String message = "글의 제목을 입력해주세요.";
        boardDTO.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "글의 내용을 입력해주세요.";
        boardDTO.setContent(ScannerUtil.nextLine(SCANNER, message));

        boardController.add(boardDTO);
    }

    private void printList() {
        ArrayList<BoardDTO> list = boardController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 글이 없습니다.");
        } else {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            for (BoardDTO b : list) {
                System.out.printf("%d. %s - %s\n", b.getId(), b.getTitle(), df.format(b.getEntryDate()));
            }

            String message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (userChoice != 0 && !list.contains(new BoardDTO(userChoice))) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        BoardDTO boardDTO = boardController.selectOne(id);
        System.out.println("=================================================");
        System.out.println(boardDTO.getTitle());
        System.out.println("-------------------------------------------------");
        System.out.println("글 번호: " + boardDTO.getId());
        System.out.println("글 작성자: " + boardDTO.getWriterNickname());
        System.out.println("작성일: " + df.format(boardDTO.getEntryDate()));
        System.out.println("수정일: " + df.format(boardDTO.getModifyDate()));
        System.out.println("-------------------------------------------------");
        System.out.println(boardDTO.getContent());
        System.out.println("-------------------------------------------------");
        System.out.println("댓글");
        System.out.println("-------------------------------------------------");
        replyViewer.printAll(id);
        System.out.println("=================================================");
        String message;
        int userChoice;

        if (boardDTO.getWriterId() == logIn.getId()) {
            message = "1. 수정 2. 삭제 3. 댓글 메뉴 4. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 4);
        } else {
            message = "3. 댓글 메뉴 4. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 3, 4);
        }

        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        } else if (userChoice == 3) {
            replyViewer.showMenu(id);
            printOne(id);
        } else if (userChoice == 4) {
            printList();
        }
    }

    private void update(int id) {
        BoardDTO b = boardController.selectOne(id);

        String message = "새로운 제목을 입력해주세요.";
        b.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 내용을 입력해주세요.";
        b.setContent(ScannerUtil.nextLine(SCANNER, message));

        boardController.update(b);
    }

    private void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            boardController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }
}


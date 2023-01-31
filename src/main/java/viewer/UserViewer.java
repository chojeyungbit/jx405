package viewer;

import controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;

public class UserViewer {
    private final Scanner SCANNER;
    private UserController userController;
    private BoardViewer boardViewer;
    private ReplyViewer replyViewer;
    private UserDTO logIn = null;

    public UserViewer(Scanner scanner) {
        SCANNER = scanner;
        userController = new UserController();
    }

    public void setBoardViewer(BoardViewer boardViewer) {
        this.boardViewer = boardViewer;
    }

    public void setReplyViewer(ReplyViewer replyViewer) {
        this.replyViewer = replyViewer;
    }

    public void showIndex() {
        String message = "1. 로그인 2. 회원가입 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    boardViewer.setLogIn(logIn);
                    replyViewer.setLogIn(logIn);
                    showMenu();
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void register() {
        String message;
        message = "사용하실 아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        while (!userController.validateUsername(username)) {
            System.out.println("해당 아이디는 사용하실 수 없습니다.");
            message = "사용하실 아이디나 뒤로 가실려면 \"X\"를 입력해주세요.";
            username = ScannerUtil.nextLine(SCANNER, message);

            if (username.equalsIgnoreCase("X")) {
                break;
            }
        }

        if (!username.equalsIgnoreCase("X")) {
            UserDTO u = new UserDTO();
            u.setUsername(username);

            message = "사용하실 비밀번호를 입력해주세요.";
            u.setPassword(ScannerUtil.nextLine(SCANNER, message));

            message = "사용하실 닉네임을 입력해주세요.";
            u.setNickname(ScannerUtil.nextLine(SCANNER, message));

            userController.insert(u);
        }
    }

    private void auth() {
        String message;
        message = "아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀번호를 입력해주세요.";
        String password = ScannerUtil.nextLine(SCANNER, message);

        logIn = userController.auth(username, password);

        if (logIn == null) {
            System.out.println("로그인 정보가 정확하지 않습니다.");
        }
    }

    private void showMenu() {
        String message = "1. 게시판으로 2. 회원 정보 관리 3. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                boardViewer.showMenu();
            } else if (userChoice == 2) {
                printOne();
            } else if (userChoice == 3) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
            }
        }
    }

    private void printOne() {
        System.out.println("회원 번호: " + logIn.getId());
        System.out.println("회원 닉네임: " + logIn.getNickname());
        System.out.println("---------------------------------------------");
        String message = "1. 수정 2. 탈퇴 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            delete();
        }
    }

    private void update() {

        String message = "새로운 비밀번호를 입력해주세요.";
        String newPassword = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 닉네임을 입력해주세요.";
        String newNickname = ScannerUtil.nextLine(SCANNER, message);

        message = "기존 비밀번호를 입력해주세요.";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);

        if (logIn.getPassword().equals(oldPassword)) {
            logIn.setNickname(newNickname);
            logIn.setPassword(newPassword);

            userController.update(logIn);
        } else {
            System.out.println("회원 정보 변경에 실패하였습니다.");
        }
    }

    private void delete() {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            message = "비밀번호를 입력해주세요.";
            String password = ScannerUtil.nextLine(SCANNER, message);

            if (password.equals(logIn.getPassword())) {
                userController.delete(logIn.getId());
                logIn = null;
            }
        }
    }
}

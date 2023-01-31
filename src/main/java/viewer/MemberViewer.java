package viewer;

import controller.MemberController;
import model.MemberDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberViewer {
    private final Scanner SCANNER;
    private MemberController memberController;
    private MemberDTO logIn;
    private ArrayList<MemberDTO> criticList;
    private ArrayList<MemberDTO> adminList;
    private final int LEVEL_GENERAL = 1;
    private final int LEVEL_CRITIC = 2;
    private final int LEVEL_ADMIN = 3;
    private FilmViewer filmViewer;
    private ReviewViewer reviewViewer;

    public MemberViewer(Scanner scanner) {
        SCANNER = scanner;
        memberController = new MemberController();
        criticList = new ArrayList<>();
        adminList = new ArrayList<>();
    }

    public void setFilmViewer(FilmViewer filmViewer) {
        this.filmViewer = filmViewer;
    }

    public void setReviewViewer(ReviewViewer reviewViewer) {
        this.reviewViewer = reviewViewer;
    }

    public void showIndex() {
        String message = "1. 로그인 2. 회원 가입 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                logIn();
                if (logIn != null) {
                    filmViewer.setLogIn(logIn);
                    reviewViewer.setLogIn(logIn);
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

    private void logIn() {
        String message1 = "아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message1);

        String message2 = "비밀번호를 입력해주세요.";
        String password = ScannerUtil.nextLine(SCANNER, message2);

        logIn = memberController.auth(username, password);
        if (logIn == null) {
            System.out.println("로그인 정보를 다시 확인해주세요.");
        }
    }


    private void register() {
        String message1 = "사용하실 아이디를 입력하시거나 뒤로 가실려면 X를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message1);

        while (!username.equalsIgnoreCase("X") && !memberController.validateUsername(username)) {
            System.out.println("잘못 입력하셨습니다.");
            username = ScannerUtil.nextLine(SCANNER, message1);
        }

        if (!username.equalsIgnoreCase("X")) {
            MemberDTO m = new MemberDTO();
            m.setUsername(username);

            String message2 = "사용하실 비밀번호를 입력해주세요.";
            m.setPassword(ScannerUtil.nextLine(SCANNER, message2));

            String message3 = "사용하실 닉네임을 입력해주세요.";
            m.setNickname(ScannerUtil.nextLine(SCANNER, message3));

            memberController.add(m);
        }
    }


    private void showMenu() {
        String message = "1. 영화 정보 2. 극장 정보 3. 상영 정보 4. 회원 정보 5. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                filmViewer.showMenu();
            } else if (userChoice == 2) {

            } else if (userChoice == 3) {

            } else if (userChoice == 4) {
                printOne();
            } else if (userChoice == 5) {
                System.out.println("정상적으로 로그아웃되셨습니다.");
                logIn = null;
            }
        }
    }

    private void printOne() {
        System.out.println("회원 번호: " + logIn.getId());
        String level = "일반 회원";
        if (logIn.getLevel() == LEVEL_CRITIC) {
            level = "평론가";
        } else if (logIn.getLevel() == LEVEL_ADMIN) {
            level = "관리자";
        }
        System.out.println("회원 등급: " + level);
        System.out.println("회원 닉네임: " + logIn.getNickname());
        String message = "1. 회원 정보 수정 2. 회원 탈퇴 ";
        if (logIn.getLevel() == LEVEL_ADMIN) {
            message += "3. 등업 신청 현황 ";
        } else {
            message += "3. 등업 신청하기 ";
        }
        message += "4. 뒤로 가기";

        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            delete();
        } else if (userChoice == 3) {
            if (logIn.getLevel() == LEVEL_ADMIN) {
                // 등업 신청 현황 보기 메소드 실행
                approveRequest();
            } else {
                // 등업 신청하기 메소드 실행
                promote();
            }
        }
    }

    private void approveRequest() {
        String message = "1. 평론가 신청 목록 2. 관리자 신청 목록 3. 뒤로 가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            printRequest(criticList, "평론가");
        } else if (userChoice == 2) {
            printRequest(adminList, "관리자");
        } else {

        }
    }

    private void printRequest(ArrayList<MemberDTO> list, String level) {
        System.out.println(level + "등급 신청 목록");
        for (MemberDTO m : list) {
            System.out.printf("%d. %s\n", m.getId(), m.getUsername());
        }
        String message = "등업을 승인할 회원의 번호나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && !list.contains(new MemberDTO(userChoice))) {
            System.out.println("해당 번호는 유효하지 않은 회원 번호입니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            if (level.equalsIgnoreCase("평론가")) {
                memberController.rankUp(userChoice, LEVEL_CRITIC);
                criticList.remove(new MemberDTO(userChoice));
            } else {
                memberController.rankUp(userChoice, LEVEL_ADMIN);
                adminList.remove(new MemberDTO(userChoice));
            }
            printRequest(list, level);
        } else {
            approveRequest();
        }

    }


    private void promote() {
        String message = "등업하실 등급을 입력해주세요. 2. 평론가 3. 관리자 0. 뒤로";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == logIn.getLevel()) {
            System.out.println("현재 등급으로는 변경하실 수 없습니다.");
        } else if (userChoice == LEVEL_CRITIC) {
            MemberDTO m = new MemberDTO(logIn.getId());
            m.setUsername(logIn.getUsername());
            criticList.add(m);
        } else if (userChoice == LEVEL_ADMIN) {
            MemberDTO m = new MemberDTO(logIn.getId());
            m.setUsername(logIn.getUsername());
            adminList.add(m);
        }
    }

    private void update() {
        String message = "새로운 비밀번호를 입력해주세요.";
        String newPassword = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 닉네임을 입력해주세요.";
        String newNickname = ScannerUtil.nextLine(SCANNER, message);

        message = "기존 비밀번호를 입력해주세요.";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);

        if (oldPassword.equals(logIn.getPassword())) {
            logIn.setPassword(newPassword);
            logIn.setNickname(newNickname);

            memberController.update(logIn);
        }

        printOne();
    }

    private void delete() {
        if (logIn.getId() == 1) {
            System.out.println("기본 관리자는 탈퇴하실 수 없습니다.");
        } else {
            String message = "정말로 탈퇴하시겠습니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);

            if (yesNo.equalsIgnoreCase("Y")) {
                criticList.remove(logIn);
                adminList.remove(logIn);

                // 리뷰 뷰어에서 해당 회원 번호를 작성자 번호로 가진
                // reviewDTO들을 삭제하도록 지시
                reviewViewer.deleteByWriterId(logIn.getId());

                memberController.delete(logIn.getId());

                logIn = null;
            }
        }
    }

    public void printNickname(int id) {
        System.out.print(memberController.selectNickname(id));
    }
}

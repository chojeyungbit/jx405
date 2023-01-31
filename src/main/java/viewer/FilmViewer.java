package viewer;

import controller.FilmController;
import model.FilmDTO;
import model.MemberDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class FilmViewer {
    private final Scanner SCANNER;
    private FilmController filmController;
    private MemberDTO logIn;
    private final int LEVEL_GENERAL = 1;
    private final int LEVEL_CRITIC = 2;
    private final int LEVEL_ADMIN = 3;
    private final int OPTION_ALL = 1;

    private ReviewViewer reviewViewer;

    public FilmViewer(Scanner scanner) {
        SCANNER = scanner;
        filmController = new FilmController();
    }

    public void setLogIn(MemberDTO logIn) {
        this.logIn = logIn;
    }

    public void setReviewViewer(ReviewViewer reviewViewer) {
        this.reviewViewer = reviewViewer;
    }

    public void showMenu() {
        if (logIn.getLevel() == LEVEL_ADMIN) {
            showAdminMenu();
        } else {
            showGeneralMenu();
        }
    }

    private void showAdminMenu() {
        String message = "1. 영화 목록 보기 2. 영화 등록 하기 3. 뒤로 가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                printList();
            } else if (userChoice == 2) {
                addFilm();
            } else if (userChoice == 3) {
                System.out.println("메인 화면으로 돌아갑니다.");
                break;
            }
        }
    }

    private void showGeneralMenu() {
        String message = "1. 영화 목록 보기 2. 뒤로 가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                printList();
            } else if (userChoice == 2) {
                System.out.println("메인 화면으로 돌아갑니다.");
                break;
            }
        }
    }

    private void addFilm() {
        FilmDTO f = new FilmDTO();

        String message = "영화의 제목을 입력해주세요.";
        f.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "영화의 줄거리를 입력해주세요.";
        f.setSummary(ScannerUtil.nextLine(SCANNER, message));

        message = "영화의 등급을 입력해주세요.\n 1. 전연령 2. 12세 3. 15세 4. 19세";
        f.setRating(ScannerUtil.nextInt(SCANNER, message, 1, 4));

        filmController.add(f);
    }

    private void printList() {
        ArrayList<FilmDTO> list = filmController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 영화가 존재하지 않습니다.");
        } else {
            for (FilmDTO f : list) {
                System.out.printf("%d. %s \n", f.getId(), f.getTitle());
            }

            String message = "상세보기할 영화의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (userChoice != 0 && filmController.selectOne(userChoice) == null) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        FilmDTO f = filmController.selectOne(id);
        System.out.println("====================================================");
        System.out.println("제목: " + f.getTitle());
        String rating = "전연령 이용가";
        if (f.getRating() == 2) {
            rating = "12세 이용가";
        } else if (f.getRating() == 3) {
            rating = "15세 이용가";
        } else if (f.getRating() == 4) {
            rating = "19세 이용가";
        }
        System.out.println("등급: " + rating);
        System.out.println("----------------------------------------------------");
        System.out.println("줄거리");
        System.out.println("----------------------------------------------------");
        System.out.println(f.getSummary());
        System.out.println("====================================================");

        if (logIn.getLevel() == LEVEL_ADMIN) {
            showAdminOptions(id);
        } else {
            showGeneralOptions(id);
        }
    }

    private void showAdminOptions(int id) {
        reviewViewer.showMenu(id, OPTION_ALL);
        String message = "1. 수정 2. 삭제 3. 뒤로 가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        } else if (userChoice == 3) {
            printList();
        }
    }

    private void update(int id) {
        FilmDTO f = filmController.selectOne(id);
        String message = "새로운 제목을 입력해주세요.";
        f.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 내용을 입력해주세요.";
        f.setSummary(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 등급을 입려겨해주세요.\n 1. 전연령 2. 12세 이용가 3. 15세 이용가 4. 19세 이용가";
        f.setRating(ScannerUtil.nextInt(SCANNER, message, 1, 4));

        filmController.update(f);

        printOne(id);
    }

    private void delete(int id) {
        String message = "해당 영화를 정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            filmController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }

    private void showGeneralOptions(int id) {
        reviewViewer.showMenu(id, OPTION_ALL);
        printList();
    }
}

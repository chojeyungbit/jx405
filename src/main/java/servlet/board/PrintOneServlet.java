package servlet.board;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.BoardController;
import controller.UserController;
import model.BoardDTO;
import model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet(name = "PrintOneServlet", value = "/board/printOne")
public class PrintOneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        JsonObject object = new JsonObject();

        HttpSession session = request.getSession();

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        BoardController boardController = new BoardController(connectionMaker);
        UserController userController = new UserController(connectionMaker);
        String message = "";
        String nextPath = "";
        try {
            UserDTO logIn = (UserDTO) session.getAttribute("logIn");

            if (logIn == null) {
                message = "로그인한 유저만 볼 수 있습니다.";
                nextPath = "/index.jsp";

                throw new NullPointerException();
            }

            int id = Integer.parseInt(request.getParameter("id"));

            BoardDTO boardDTO = boardController.selectOne(id);
            if (boardDTO == null) {
                message = "유효하지 않은 글 번호입니다.";
                nextPath = "/board/printList.jsp?pageNo=1";
                throw new NullPointerException();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");

            JsonObject boardJson = new JsonObject();
            boardJson.addProperty("id", boardDTO.getId());
            boardJson.addProperty("title", boardDTO.getTitle());
            boardJson.addProperty("writerNickname", userController.selectOne(boardDTO.getWriterId()).getNickname());
            boardJson.addProperty("entryDate", sdf.format(boardDTO.getEntryDate()));
            boardJson.addProperty("modifyDate", sdf.format(boardDTO.getModifyDate()));
            boardJson.addProperty("content", boardDTO.getContent());
            boardJson.addProperty("isOwned", boardDTO.getWriterId() == logIn.getId());

            object.addProperty("status", "success");
            object.addProperty("data", boardJson.toString());

        } catch (NullPointerException e) {
            object.addProperty("status", "fail");
            object.addProperty("message", message);
            object.addProperty("nextPath", nextPath);
        } catch (Exception e) {
            message = "오류가 발생하였습니다.";
            nextPath = "/board/printList.jsp?pageNo=1";
            object.addProperty("status", "fail");
            object.addProperty("message", message);
            object.addProperty("nextPath", nextPath);
        }

        writer.print(object);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

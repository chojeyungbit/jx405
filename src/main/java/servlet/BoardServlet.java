package servlet;

import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.BoardController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BoardServlet", value = "/board/printList")
public class BoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        BoardController boardController = new BoardController(connectionMaker);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Hello, WORLD!!!</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>HELLO, WORLD!!!</h1>");
        writer.println("</body>");
        writer.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

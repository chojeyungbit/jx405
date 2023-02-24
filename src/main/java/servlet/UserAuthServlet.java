package servlet;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.UserController;
import model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(name = "AuthServlet", value = "/user/auth")
public class UserAuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        UserController userController = new UserController(connectionMaker);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDTO userDTO = userController.auth(username, password);

        JsonObject result = new JsonObject();
        if(userDTO != null){
            HttpSession session = request.getSession();
            session.setAttribute("logIn", userDTO);

            result.addProperty("result", "success");
        }else {
            result.addProperty("result", "fail");
        }

        PrintWriter writer = response.getWriter();
        writer.print(result);
    }
}

















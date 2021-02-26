package mortal.learn.dev.web.servlet.learn03sessionmanagement.usesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "UserView",
        urlPatterns = "/user.view",
        loadOnStartup = 21
)
public class UserView extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Object login = request.getSession().getAttribute("login");

        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head><body>");

        out.println("<h1>已登录<h1><br>");
        out.println(login.toString());

        out.println("</body></html>");
    }
}

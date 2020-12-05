package mortal.learn.dev.web.servlet.learn01codingandconfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name="Path",
        urlPatterns = "/",
        loadOnStartup = 2
)
public class Path extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String request_url = request.getRequestURI();
        String context_path = request.getContextPath();
        String servlet_path = request.getServletPath();
        String path_info = request.getPathInfo();

        PrintWriter out = response.getWriter();
        out.println("request_url  = " + request_url + "<br>");
        out.println("context_path = " + context_path + "<br>");
        out.println("servlet_path = " + servlet_path + "<br>");
        out.println("path_info    = " + path_info + "<br>");

    }
}

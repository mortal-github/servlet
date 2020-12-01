package mortal.learn.dev.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name="Mapping",
        urlPatterns = "/mapping/*",
        loadOnStartup = 3
)
public class Mapping extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpServletMapping mapping = request.getHttpServletMapping();
        String match = mapping.getMappingMatch().toString();
        String value = mapping.getMatchValue();
        String pattern = mapping.getPattern();
        PrintWriter out = response.getWriter();

        out.print("match   = " + match  + "<br>");
        out.print("value   = " + value  + "<br>");
        out.print("pattern = " + pattern  + "<br>");
    }
}

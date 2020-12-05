package mortal.learn.dev.web.servlet.learn02requestandresponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

@WebServlet(
        name = "ResponseEncoding",
        urlPatterns = "/response/encoding",
        loadOnStartup = 13
)
public class ResponseEncoding extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servlet_context = getServletContext();
        String content_type = servlet_context.getMimeType("xxx.html");

        response.setLocale(Locale.CHINA);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(content_type);

        PrintWriter out = response.getWriter();
        out.println("探测编码");
    }
}

package mortal.learn.dev.web.servlet.learn03sessionmanagement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.IntStream;

@WebServlet(
        name = "URLRewriting",
        urlPatterns = "/url_rewriting",
        loadOnStartup = 18
)
public class URLRewriting extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = Optional.ofNullable(request.getParameter("page")).orElse("1");
        int p = Integer.parseInt(page);

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head><body>");

        out.println("<ul>");
        IntStream.rangeClosed(1, 10).forEach(i -> out.printf("<li>搜索结果 %d</li>", i));
        out.println("</ul>");

        IntStream.rangeClosed(1, 10).forEach(i->{
            if(i == p)
                out.print(i);
            else
                out.printf("<a href=\"url_rewriting?page=%d\">%d</a>", i, i);//URL重写保留分页信息。
        });

        out.println("</body></html>");
    }
}

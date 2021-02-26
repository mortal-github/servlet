package mortal.learn.dev.web.servlet.learn03sessionmanagement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(
        name = "HiddenField",
        urlPatterns = "/hidden_field",
        loadOnStartup = 16
)
public class HiddenField extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String page = Optional.ofNullable(request.getParameter("page")).orElse("page1");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head><body>");
        out.println("<form action=\"hidden_field\" method=\"post\">");

        switch(page){
            case "page1":page1(out);
                break;
            case "page2":page2(request, out);
                break;
            case "finish":page3(request, out);
                break;
            default:;
        }

        out.println("</from></body></html>");
    }

    private void page1(PrintWriter out){
        out.println("问题一：<input type=\"text\" name=\"p1q1\"><br>");
        out.println("问题一：<input type=\"text\" name=\"p1q2\"><br>");
        out.println("<input type=\"submit\" name=\"page\" value=\"page2\"><br>");
    }
    private void page2(HttpServletRequest request, PrintWriter out){
        String p1q1 = request.getParameter("p1q1");
        String p1q2 = request.getParameter("p1q2");
        out.println("问题三：<input type=\"text\" name=\"p2q1\"><br>");
        out.println("<input type=\"submit\" name=\"page\" value=\"finish\"><br>");
        //隐藏域
        out.printf("<input type=\"hidden\" name=\"p1q1\" value=\"%s\"><br>", p1q1);
        out.printf("<input type=\"hidden\" name=\"p1q2\" value=\"%s\"><br>", p1q2);
    }
    private void page3(HttpServletRequest request, PrintWriter out){
        out.println("问题一：" + request.getParameter("p1q1") + "<br>");
        out.println("问题二：" + request.getParameter("p1q2") + "<br>");
        out.println("问题三：" + request.getParameter("p2q1") + "<br>");
    }
}

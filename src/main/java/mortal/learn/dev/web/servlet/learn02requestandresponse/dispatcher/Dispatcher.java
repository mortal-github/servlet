package mortal.learn.dev.web.servlet.learn02requestandresponse.dispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "RequestDispatcher",
        urlPatterns = "/request/dispatcher",
        loadOnStartup = 9
)
public class Dispatcher extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String share = "共享对象一号，一个字符串对象";
        request.setAttribute("share", share);
        String will_removed = "共享对象二号，会被移除";
        request.setAttribute("will_removed", will_removed);

        PrintWriter out = response.getWriter();

        out.println("dispatcher include begin");
        request.getRequestDispatcher("include?name=钟景文&钟景文=大佬&请求调派=包含").include(request, response);
        out.println("dispatcher include end");

        if(null != request.getParameter("flush")){
            response.flushBuffer();//调用forward的servlet响应无效，若是已经确认了响应，则forward抛出异常IllegatStateException。
        }
        out.println("dispatcher forward begin");
        try{
            request.getRequestDispatcher("forward?响应=目前Servlet无效&若响应确认=则抛出IllegalStateException异常").forward(request, response);
        }catch(IllegalStateException e){
            out.println(e.toString());
        }
        out.println("dispatcher forward end");
    }
}

package mortal.learn.dev.web.servlet.learn02requestandresponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ResponseSend",
        urlPatterns = "/response/send",
        loadOnStartup = 15
)
public class ResponseSend extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String select = request.getParameter("select");

        if(null == select){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "没有就是没有！不要来找我，自己查百度");
        }else{
            response.sendRedirect("../index.html");
        }
    }
}

package mortal.learn.dev.web.servlet.learn02requestandresponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(
        name= "RequestParam",
        urlPatterns = "/request/param",
        loadOnStartup = 4
)
public class RequestParameter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = Optional.ofNullable(request.getParameter("param")).orElse("Guest");
        String[] params = request.getParameterValues("param");
        Enumeration<String> names = request.getParameterNames();
        Map<String, String[]> map = request.getParameterMap();

        ArrayList<String> list = Collections.list(names);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("request.getParameter(param) = " + param);
        out.println("request.getParameterValues(param) = " + Arrays.toString(params));
        out.println("request.getParameterNames() = " + list.toString() );
        out.println("request.getParameterMap() = ");
        for(Map.Entry<String, String[]> entry : map.entrySet()){
            String key = entry.getKey();
            String[] values = entry.getValue();
            out.println(key + " = " + Arrays.toString(values));
        }
    }
}



















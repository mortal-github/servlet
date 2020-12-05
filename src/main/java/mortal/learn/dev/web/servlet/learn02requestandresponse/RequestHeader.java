package mortal.learn.dev.web.servlet.learn02requestandresponse;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;

@WebServlet(
        name = "RequestHeader",
        urlPatterns = "/request/header",
        loadOnStartup = 5
)
public class RequestHeader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String header = request.getHeader("repeat-header");
        Enumeration<String> headers = request.getHeaders("repeat-header");
        Enumeration<String> names = request.getHeaderNames();

        ArrayList<String> headers_list = Collections.list(headers);
        ArrayList<String> names_list = Collections.list(names);

        int integer = 0;
        long date = 0;
        try{
            integer = request.getIntHeader("int-header");
        }catch(NumberFormatException e){}
        try{
            date = request.getDateHeader("date-header");
        }catch(IllegalArgumentException e){}

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("request.getHeader(  Content-Type ) : " + header);
        out.println("request.getHeaders( Content-Type ) : " + headers_list.toString());
        out.println("request.getHeaderNames()           : " + names_list.toString());
        out.println("request.getIntHeader(int-header)   : " + integer);
        out.println("request.getDateHeader(date-header) : " + date);
        out.println("Date(date) = " + new Date(date));
    }
}

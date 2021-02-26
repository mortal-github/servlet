package mortal.learn.dev.web.servlet.learn03sessionmanagement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "UseCookie",
        urlPatterns = "/useCookie",
        loadOnStartup = 17
)
public class UseCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for(Cookie cookie : cookies){
                out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }

        Cookie cookie = new Cookie("name", "mortal");
        cookie.setMaxAge(60);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        out.println(cookie.isHttpOnly());

        response.addCookie(cookie);

        cookie = new Cookie("input", "8000");
        cookie.setMaxAge(60);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        out.println(cookie.isHttpOnly());

        response.addCookie(cookie);
    }
}

package mortal.learn.dev.web.servlet.learn03sessionmanagement.usesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(
        name = "User",
        urlPatterns = "/user",
        loadOnStartup = 20
)
public class User extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Optional<Object> token = Optional.ofNullable(session.getAttribute("login"));

        if(token.isPresent()){
            request.getRequestDispatcher("user.view").forward(request, response);
        }else{
            response.sendRedirect("login");
        }
    }
}

package mortal.learn.dev.web.servlet.learn02requestandresponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(
        name = "ResponseOutputStream",
        urlPatterns = "/response/outputStream",
        loadOnStartup = 14
)
public class ResponseOutputStream extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/png");

        try(InputStream in = getServletContext().getResourceAsStream("/WEB-INF/浏览器不可直接访问的资源.png")){
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while(-1 != (length = in.read(buffer))){
                out.write(buffer, 0, length);
            }
        }
    }
}

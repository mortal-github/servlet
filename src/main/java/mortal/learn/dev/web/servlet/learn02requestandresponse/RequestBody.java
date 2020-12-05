package mortal.learn.dev.web.servlet.learn02requestandresponse;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(
        name = "RequestBody",
        urlPatterns = "/request/body",
        loadOnStartup = 7
)
public class RequestBody extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String select = Optional.ofNullable(request.getHeader("select")).orElse("getInputStream");

        if(select.equals("getReader")){
            BufferedReader reader = request.getReader();
            PrintWriter out = response.getWriter();

            String input;
            while(null != (input = reader.readLine())){
                out.println(input);
            }
        }else if(select.equals("getInputStream")){
            ServletInputStream input_stream = request.getInputStream();
            ServletOutputStream output_stream = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int length ;
            while(-1 != (length = input_stream.read(buffer))){
                output_stream.write(buffer, 0, length);
            }
        }
    }
}

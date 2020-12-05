package mortal.learn.dev.web.servlet.learn02requestandresponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(
        name = "ResponseHeader",
        urlPatterns = "/response/header",
        loadOnStartup = 12
)
public class ResponseHeader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置响应标头
        response.setHeader("abc", "a");

        response.addHeader("abc", "b");
        response.addHeader("abc","c");

        response.setIntHeader("cba", 1000);
        response.setDateHeader("dac", new Date().getTime());


        //缓冲区操作
        int buffer_size = response.getBufferSize();
        response.setBufferSize(buffer_size);//必须在getWriter和getOutputStream之前调用，否则抛出异常
        PrintWriter out = response.getWriter();

        boolean is_committed = response.isCommitted();
        out.println("is_committed = " + is_committed);

        if(null != request.getParameter("reset")){
            response.reset();//清除标头，重置响应内容。
        }
        if(null != request.getParameter("resetBuffer")){
            response.resetBuffer();//仅重置响应内容。
        }

        //配合状态码的标头，例如重定向
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "../index.html");

        response.flushBuffer(); //在响应确认之后调用reset()，resetBuffer()会抛出IllegalStateException
    }
}

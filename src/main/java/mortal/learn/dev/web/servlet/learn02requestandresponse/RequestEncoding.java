package mortal.learn.dev.web.servlet.learn02requestandresponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(
        name = "RequestEncoding",
        urlPatterns = "/request/encoding",
        loadOnStartup = 6
)
public class RequestEncoding extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String param = request.getParameter("param");
        //假设浏览器以UTF-8送URL
        //HTTP服务器以UTF-8处理
        //查询参数只能手动转换编码
        param = new String(param.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("param = " + param);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //假设浏览器以UTF-8编码
        //tomcat8之前容器默认以ISO-8859-1编码处理，tomcat之后默认以UTF-8处理。
        String encoding = request.getCharacterEncoding();//从Content-Type标头中查询字符编码，
        // 若为null则使用默认编码处理，否则按照其所指示的编码来处理。
        request.setCharacterEncoding("UTF-8");           //设置处理请求的编码

        String body = request.getReader().readLine();

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("encoding = " + encoding);
        out.println(body);
    }
}

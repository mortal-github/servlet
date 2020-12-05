package mortal.learn.dev.web.servlet.learn02requestandresponse.dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(
        name = "Forward",
        urlPatterns = "/request/forward",
        loadOnStartup = 11
)
public class Forward extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        //forward-请求范围参数之保留名称
        out.println("forward-请求范围属性之共享对象 ：");
        Object javax_servlet_forward_request_uri = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        Object javax_servlet_forward_context_path = request.getAttribute(RequestDispatcher.FORWARD_CONTEXT_PATH);
        Object javax_servlet_forward_servlet_path = request.getAttribute(RequestDispatcher.FORWARD_SERVLET_PATH);
        Object javax_servlet_forward_path_info = request.getAttribute(RequestDispatcher.FORWARD_PATH_INFO);
        Object javax_servlet_forward_query_string = request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
        Object javax_servlet_forward_mapping = request.getAttribute(RequestDispatcher.FORWARD_MAPPING);
        //forward-请求范围参数之共享对象：
        out.println("forward-请求范围参数之共享对象 ：");
        Enumeration<String> enumeration = request.getAttributeNames();
        ArrayList<String> list = Collections.list(enumeration);
        for(String name : list){
            out.println(name + " : " + request.getAttribute(name));
        }
        //所有请求参数
        out.println("forward-请求范围属性之所有请求参数 ： ");
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()){
            out.println(entry.getKey() + " : " + Arrays.toString(entry.getValue()));
        }
    }
}

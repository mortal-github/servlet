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
        name = "Include",
        urlPatterns = "/request/include",
        loadOnStartup = 10
)
public class Include extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("include-请求范围属性之保留名称 ：");
        //请求范围属性之保留名称
        Object javax_servlet_include_request_uri  = request.getAttribute(RequestDispatcher.INCLUDE_REQUEST_URI);
        Object javax_servlet_include_context_path = request.getAttribute(RequestDispatcher.INCLUDE_CONTEXT_PATH);
        Object javax_servlet_include_servlet_path = request.getAttribute(RequestDispatcher.INCLUDE_SERVLET_PATH);
        Object javax_servlet_include_path_info    = request.getAttribute(RequestDispatcher.INCLUDE_PATH_INFO);
        Object javax_servlet_include_query_string = request.getAttribute(RequestDispatcher.INCLUDE_QUERY_STRING);
        Object javax_servlet_include_mapping      = request.getAttribute(RequestDispatcher.INCLUDE_MAPPING);
        out.println("javax.servlet.include.request_uri : " + javax_servlet_include_request_uri);
        out.println("javax.servlet.include.context_path : " + javax_servlet_include_context_path);
        out.println("javax.servlet.include.servlet_path : " + javax_servlet_include_servlet_path);
        out.println("javax.servlet.include.path_info : " + javax_servlet_include_path_info);
        out.println("javax.servlet.include.query_string : " + javax_servlet_include_query_string);
        out.println("javax.servlet.include.mapping : " + javax_servlet_include_mapping);
        //请求范围属性之共享对象：
        out.println("include-请求范围属性之共享对象 ：");
        request.removeAttribute("will_removed");
        Enumeration<String> enumeration = request.getAttributeNames();
        ArrayList<String> list = Collections.list(enumeration);
        for(String name : list){
            out.println(name + " : " + request.getAttribute(name));
        }
        //所有请求参数
        out.println("include-请求范围属性之所有请求参数 ： ");
        Map<String, String[]> map = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : map.entrySet()){
            out.println(entry.getKey() + " : " + Arrays.toString(entry.getValue()));
        }
    }
}

package mortal.learn.dev.web.servlet.learn03sessionmanagement.usesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "Login",
        urlPatterns = "/login",
        loadOnStartup = 19
)
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head><body>");

        out.println("<form action=\"login\" method=\"post\">");
        out.println("名称：<input type=\"text\" name=\"user\"><br>");
        out.println("密码：<input type=\"password\" name=\"password\"><br>");
        out.println("自动登录：<input type=\"radio\" name=\"auto\"><br>");
        out.println("<input type=\"submit\" name=\"提交\" value=\"login\">");
        out.println("</form>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        if(login(user, password)){

            if(null != request.getSession(false)){
                //为了安全，登录后要改变SessionId。(如果有Session对象的情况)
                changeSessionId(request);
                //servlet3.1 以后才有这个方法
                //request.changeSessionId();
            }
            request.getSession().setAttribute("login", user);//保存登录状态
            response.sendRedirect("user");
        }else{
            response.sendRedirect("login");
        }
    }

    private boolean login(String user, String password){
        if(null == user || null == password)
            return false;

        boolean success = false;
        switch(user){
            case "钟景文":
                if(password.equals("3118005434"))
                    success = true;
                break;
            case "游客":
                if(password.equals("123456"))
                    success = true;
                break;
            default:;
        }
        return success;
    }

    private void changeSessionId(HttpServletRequest request){
        HttpSession oldSession = request.getSession();

        Map<String, Object> map = new HashMap<>();
        for(String name : Collections.list(oldSession.getAttributeNames())){
            map.put(name, oldSession.getAttribute(name));
        }

        oldSession.invalidate();//令目前的Session失效

        //设置一个新的Session
        HttpSession newSession = request.getSession();
        for(String name : map.keySet()){
            newSession.setAttribute(name, map.get(name));
        }
    }
}

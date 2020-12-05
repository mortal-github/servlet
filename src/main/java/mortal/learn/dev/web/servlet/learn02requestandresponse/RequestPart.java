package mortal.learn.dev.web.servlet.learn02requestandresponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig(
        fileSizeThreshold = 595129,
        location = "C:\\Workspace\\git\\servlet\\src\\main\\webapp\\temp",
        maxFileSize = 595129,
        maxRequestSize = 59512900
)
@WebServlet(
        name = "RequestPart",
        urlPatterns = "/request/part",
        loadOnStartup = 8
)
public class RequestPart extends HttpServlet {

    private final Pattern fileNameRegex = Pattern.compile("filename=\"(.*)\"");
    private int counter = 0;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//为了处理中文文件名

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Part part = request.getPart("part");
        String Content_Disposition = part.getHeader("Content-Disposition");
        String Content_Type = part.getContentType();
        String name = part.getName();

        Collection<Part> parts = request.getParts();

        out.println("request.getPart(part)               = " + part);
        out.println("part.getHeader(Content-Disposition) = " + Content_Disposition);
        out.println("part.getContentType()               = " + Content_Type);
        out.println("part.getName()                      = " + name);
        out.println("request.getParts()                  = " + parts.toString());

        //String file_name_auto = part.getSubmittedFileName();//tomcat7不支持
        String file_name_hand = getSubmittedFileName(part);
        //out.println("part.getSubmittedFileName()     = " + file_name_auto);
        out.println("self.getSubmittedFileName(part) = " + file_name_hand);

        counter++;
        part.write("file" + counter);
        try(InputStream in = part.getInputStream()){
            byte[] buffer = new byte[1024];
            int length;
            while(-1 != (length = in.read(buffer))){
                out.write(new String(buffer, 0, length));
            }
        }
    }

    private String getSubmittedFileName(Part part){
        String header = part.getHeader("Content-Disposition");
        Matcher matcher = fileNameRegex.matcher(header);
        matcher.find();

        String filename = matcher.group(1);
        if(filename.contains("\\")){
            return filename.substring(filename.lastIndexOf("\\") + 1);
        }else if(filename.contains("/")){
            return filename.substring(filename.lastIndexOf("/") + 1);
        }else{
            return filename;
        }
    }
}

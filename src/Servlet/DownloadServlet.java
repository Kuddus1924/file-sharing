package Servlet;
import model.CountFile;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by Куддус on 11.12.2017.
 */
public class DownloadServlet extends HttpServlet{
    private CountFile countFile;
    public void init()
    {
         countFile = new CountFile("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\baseFile.txt");
        countFile.read();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        init();
    String filePath = "C:\\Users\\Куддус\\IdeaProjects\\kursovay\\web\\META-INF\\upload\\";
    String name = request.getParameter("fileName");
    countFile.fileAdd(name);
    File download = new File(filePath+name);
    FileInputStream stream = new FileInputStream (download);
    ServletContext context = getServletContext();
    String mimeType = context.getMimeType(filePath);
    if(mimeType == null)
        mimeType ="application/octet-stream";
    response.setContentType(mimeType);
    response.setContentLength((int)download.length());
    String header = "attachment; filename=\""+ download.getName()+"\"";
    response.setHeader("Content-Disposition",header);
    OutputStream out = response.getOutputStream();
    byte[] buffer = new byte[40000];
    int bytesRead = -1;
    while ((bytesRead = stream.read(buffer)) != -1)
    {
        out.write(buffer,0,bytesRead);
    }
    stream.close();
    out.close();
        request.getRequestDispatcher("/LoginServlet").forward(request, response);

    }
}

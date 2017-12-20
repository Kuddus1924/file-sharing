package Servlet;

import model.CountFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.LongSummaryStatistics;

/**
 * Created by Куддус on 15.12.2017.
 */
public class GDServlet extends HttpServlet {
    private CountFile countFile;
    public void init()
    {
        countFile = new CountFile("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\baseFile.txt");
        countFile.read();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String time = request.getParameter("time");
        String ittime = request.getParameter("ittime");
        int lt = Integer.parseInt(time);
        long ltime = 60000*lt;
        long it = Long.parseLong(ittime);
        Date date = new Date();
        long times = date.getTime();
        if(times >  (ltime+ it))
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("time out,suka");
            out.println("</html></body>");
        }
        else {
            String filePath = "C:\\Users\\Куддус\\IdeaProjects\\kursovay\\web\\META-INF\\upload\\";
            File download = new File(filePath + fileName);
            countFile.fileAdd(fileName);
            FileInputStream stream = new FileInputStream(download);
            ServletContext context = getServletContext();
            String mimeType = context.getMimeType(filePath);
            if (mimeType == null)
                mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            response.setContentLength((int) download.length());
            String header = "attachment; filename=\"" + download.getName() + "\"";
            response.setHeader("Content-Disposition", header);
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[40000];
            int bytesRead = -1;
            while ((bytesRead = stream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            stream.close();
            out.close();
        }
    }

}

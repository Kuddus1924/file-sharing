package Servlet;

import model.Base;
import model.CountFile;
import model.ListFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Куддус on 17.12.2017.
 */
public class CatalogServlet extends HttpServlet {
    Base base ;
    private CountFile countFile;
    public void init()  {
        base = new Base("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\users.txt");
        countFile = new CountFile("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\baseFile.txt");
        countFile.read();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        init();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script src='list.js' defer></script>");
        out.println("<link rel=\"StyleSheet\" href=\"style.css\" type=\"text/css\">");
        out.println("<html><body>");
        String time = request.getParameter("time");
        String ittime = request.getParameter("ittime");
        int lt = Integer.parseInt(time);
        long ltime = 60000*lt;
        long it = Long.parseLong(ittime);
        Date date = new Date();
        long times = date.getTime();
        if(times >  (ltime+ it))
        {
            out.println("<a class=\"mid\">Time out</a>");
            out.println("</html></body>");
        }
        else {
            String name = request.getParameter("file");
            ListFile list = base.get(name).getFile();
            ArrayList<String> arrayList = list.getList();
            printAll(out, arrayList, name);
            out.println("</html></body>");
        }
    }
    private void printAll(PrintWriter out,ArrayList<String> arrayList,String name)throws IOException
    {
        for(int i = 0; i < arrayList.size(); i++){
            String str = arrayList.get(i);
            String tmp  = str.substring(name.length());
            out.println("<table border = \"2\" class = \"tab\" >" +
                    "<tr>" +
                    "<td rowspan=\"2\">" + "<div id = \"text\">" + tmp + "</div></td>" +
                    "<td width = \"3%\" >" +"<form action=\"URLDownload\" method=\"get\"> <input type=\"hidden\" name=\"fileName\" value= \"" + str + "\"><input type=\"submit\" value=\"Download\"  class=\"my_button\"  onclick=\"download()\"></form>"+ "</td>" +
                    "</table>");
        }
    }
}

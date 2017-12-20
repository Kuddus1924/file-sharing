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

/**
 * Created by Куддус on 10.12.2017.
 * hidden value ;
 */
public class ListOfFile extends HttpServlet {
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
        out.println("<html><body>");
        String name = (String)request.getSession(false).getAttribute("name");
        ListFile list = base.get(name).getFile();
        String f = request.getParameter("deleteFileName");
        if( f != null ) {
            list.delete(f);
            countFile.delete(f);
        }
        if(name == null)
        {
            out.println("error");
            out.println("</html></body>");
        }
        ArrayList<String> arrayList = list.getList();
        request.getRequestDispatcher("link.html").include(request,response);
       printS(out,arrayList,name);
       printAll(out,arrayList,name);
        out.println("</html></body>");
    }
    private void printAll(PrintWriter out,ArrayList<String> arrayList,String name)throws IOException
    {
        for(int i = 0; i < arrayList.size(); i++){
            String str = arrayList.get(i);
            String tmp  = str.substring(name.length());
            out.println("<table border = \"4\" class = \"tab\" >" +
                    "<tr>" +
                    "<td rowspan=\"2\">" + "<div id = \"text\">" + tmp + "</div></td>" +
                    "<td width = \"3%\" >" +"<form action=\"URLDownload\" method=\"get\"> <input type=\"hidden\" name=\"fileName\" value= \"" + str + "\"><input type=\"submit\" value=\"Download\"  class=\"my_button\"  onclick=\"download()\"></form>"+ "</td>" +
                    "<td width = \"3%\" >" +"<form action> <input type=\"hidden\" name=\"deleteFileName\" id=\"delete\" value= \"" + str + "\"><input type=\"submit\"  class=\"my_button1\"  value=\"Delete\" onclick=\"delete()\"></form>"+ "</td>" +
                    "<td width = \"15%\">"+"<div id = \"counter\">" + countFile.gets(str) + "</div></td>"+
                    "</tr>" +
                    "</table>");
        }
    }
    private void printS(PrintWriter out,ArrayList<String> arrayList,String name)throws IOException
    {
        out.println("<form action=\"\"> " +  "<p><strong>Formed link<strong><input  class=\"forms\" name=\"textfield\" type=\"text\"id=\"status\" value=\"\"}></p></p>");
        out.println("<p><strong>select file     </strong><select name=\"file\" id =\"FILE\"></p>");
        for(int i = 0; i < arrayList.size(); i++){
            String str = arrayList.get(i);
            String tmp  = str.substring(name.length());
            out.println("<option value=\""+ str+"\">"+tmp+"</option>");

        }
        out.println("<option value=\"all\">"+ "All catalog"+"</option>");
        out.println("</select>");
        out.println("<input type=\"hidden\" id=\"polis\" name=\"Name\" value= \"" + name + "\">");
        out.println("<p><p><strong>select time</strong><select name=\"time\" id =\"time\"></p>");
        for(int i = 10; i <= 120; i+=10){
            out.println("<option value=\""+ i+"\">"+i+"</option>");

        }
        out.println("</select>");
        out.println( "<input type=\"button\" value=\"get\" onclick=\"sen()\"></form>");
    }

}

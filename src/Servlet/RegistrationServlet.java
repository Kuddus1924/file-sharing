package Servlet;

import model.Base;
import model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
public class RegistrationServlet extends HttpServlet {
   Base base ;
    public void  init()
    {
        base = new Base("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\users.txt");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        init();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        out.println("<html><body>");
        if(!base.checkName(name))
        {
            base.addUser(name,password);
            base.writeBase();
            request.getRequestDispatcher("link.html").include(request,response);
            out.println("Welcome "+ name);
            HttpSession session = request.getSession();
            session.setAttribute("name",name);
        }
        else
        {
            request.getRequestDispatcher("registr.html").include(request,response);
            out.println("<a class=\"midl\">A user with this name exists</a>");
        }
        out.println("</body></html>");
        out.close();
    }
}

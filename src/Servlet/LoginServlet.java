package Servlet;

import model.Base;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
public class LoginServlet extends HttpServlet {

    private Base base;
    public  void init()
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
        boolean flag = base.check(name,password);
        out.println("<html><body>");
        if (flag) {
            request.getRequestDispatcher("link.html").include(request, response);
            out.println("<a>Welcome " + name+"</a>");
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
        }
        else {
            request.getRequestDispatcher("login.html").include(request, response);
            out.println("<a class=\"mid\">The username or password you entered is incorrect</a>");
        }
        out.println("</body></html>");
        out.close();

    }



}

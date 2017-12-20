package Servlet;
import model.Base;
import model.CountFile;
import model.ListFile;
import model.Person;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.jms.Session;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Куддус on 10.12.2017.
 */
public class LoadServlet extends HttpServlet {
   static final int fileMaxSize = 10000 * 1024;
    static final int memMaxSize = 10000 * 1024;
    private CountFile countFile;
    Base base ;
    public void  init()
    {
        countFile = new CountFile("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\baseFile.txt");
        countFile.read();
        base = new Base("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\users.txt");
    }

    private String filePath = "C:\\Users\\Куддус\\IdeaProjects\\kursovay\\web\\META-INF\\upload";
    private File file;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String docType = "<!DOCTYPE html>";
        Date date = new Date();
        String name = (String) request.getSession(false).getAttribute("name");
        PrintWriter writer = response.getWriter();
        Person person = base.get(name);
        ListFile list = person.getFile();
        ArrayList<String>  listik = list.getList();
        if(person == null)
            System.out.println("suka");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);


        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        try {
            List<FileItem>  fileItems = upload.parseRequest(request);

            Iterator iterator = fileItems.iterator();


            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!fileItem.isFormField()) {

                    String fileName = fileItem.getName();
                    Random rand = new Random();
                    if(fileItem.getSize() == 0){
                        request.getRequestDispatcher("fileDow.html").forward(request,response);
                        return;
                    }
                    for(int i = 0;i < listik.size();i++)
                    {
                        String top = new String();
                        if (fileName.lastIndexOf("\\") >= 0) {
                            top = fileName.substring(fileName.lastIndexOf("\\"));
                        }
                        else
                        {
                            top = fileName.substring(fileName.lastIndexOf("\\") + 1);
                        }

                        if(listik.get(i).equals(name + top))
                        {
                            name = name + (rand.nextInt()%10000);
                        }
                    }
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + "\\"+ name+ fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + "\\"+ name+ fileName.substring(fileName.lastIndexOf("\\") + 1));

                    }
                    fileItem.write(file);
                    person.getFile().addFile(file.getName());
                    countFile.fileAdds(file.getName());
                }
            }
            request.getRequestDispatcher("link.html").include(request,response);
            writer.println("<a>Uploaded successfully</a>");
            writer.println("</body>" +
                    "</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }


package model;

import java.io.File;

public class Person {
    private String name;
    private String password;
    private ListFile file;

    public Person(String n, String p)
    {
        name = n;
        password = p;
        file = new ListFile("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\web\\META-INF\\upload\\user-file\\" + name +".txt");

    }
    public String getName()
    {
        return name;
    }
    public String getPassword()
    {
        return  password;
    }
    public ListFile getFile()
    {
        return file;
    }
}

package test;

import model.ListFile;
import model.Person;
import org.junit.Test;
import org.junit.*;
/**
 * Created by Куддус on 17.12.2017.
 */
public class PersonTest {
    Person test = new Person("test","test1");
    @Test
    public void testName()
    {
        Assert.assertTrue(test.getName().equals("test"));
    }
    @Test
    public void testPassword()
    {
        Assert.assertTrue(test.getPassword().equals("test1"));
    }
    @Test
    public void testFile()
    {
        Assert.assertTrue(test.getFile().equals(new ListFile("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\web\\META-INF\\upload\\user-file\\test.txt")));
    }

}

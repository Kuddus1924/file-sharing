package test;
import model.Base;
import model.Person;
import org.junit.Test;
import java.io.File;
import org.junit.*;
import java.io.IOException;
/**
 * Created by Куддус on 17.12.2017.
 */
public class BaseTest {
    public Base base = new Base("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\users.txt");
    @Test
    public void testCheck()
    {
        Assert.assertTrue(base.check("kuddus","1"));
        Assert.assertTrue(!base.check("kudduddsdss","1dfdfd"));
    }
    @Test
    public void testGet()
    {
        Person per = new Person("bob","1");
        Person tmp = base.get("bob");
        Assert.assertTrue(((per.getPassword().equals(tmp.getPassword()))&&(per.getName().equals(tmp.getName()))));

    }



}

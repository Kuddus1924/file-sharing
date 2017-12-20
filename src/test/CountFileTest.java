package test;
import model.Base;
import model.CountFile;
import model.ListFile;
import model.Person;
import org.junit.Test;

import org.junit.*;

import java.util.ArrayList;
/**
 * Created by Куддус on 18.12.2017.
 */
public class CountFileTest {
    CountFile count = new CountFile("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\test\\oo.txt");
    @Test
    public void testGets()
    {
        count.read();
        Assert.assertTrue(5 == count.gets("bob2.4.tif"));
    }
}

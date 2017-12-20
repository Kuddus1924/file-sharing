package test;
import model.Base;
import model.ListFile;
import model.Person;
import org.junit.Test;

import org.junit.*;

import java.util.ArrayList;

/**
 * Created by Куддус on 18.12.2017.
 */
public class ListFileTest {
    private ListFile list = new ListFile("C:\\Users\\Куддус\\IdeaProjects\\kursovay\\src\\test\\ex.txt");
    ArrayList<String> str = new ArrayList<>();
    @Test
    public void testGetList()
    {
        str.add("bob2.4.tif");
        str.add("bob007l.m");
        Assert.assertTrue(list.getList().equals(str));
    }

}

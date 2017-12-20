package test;

import model.ListFile;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Created by Куддус on 21.06.2017.
 */
public class TestRunner {

    public static void main(String[] args) {
        System.out.println("BaseTest");
        Result result = JUnitCore.runClasses(BaseTest.class);

        System.out.println("Total number of tests " + result.getRunCount());

        System.out.println("Total number of tests failed " + result.getFailureCount());
        System.out.println("CountFileTest");
        Result result1 = JUnitCore.runClasses(CountFileTest.class);

        System.out.println("Total number of tests " + result1.getRunCount());

        System.out.println("Total number of tests failed " + result1.getFailureCount());
        System.out.println("ListFileTest");
        Result result2 = JUnitCore.runClasses(ListFileTest.class);

        System.out.println("Total number of tests " + result2.getRunCount());

        System.out.println("Total number of tests failed " + result.getFailureCount());
        System.out.println("PersonTest");
        Result result3 = JUnitCore.runClasses(PersonTest.class);

        System.out.println("Total number of tests " + result3.getRunCount());

        System.out.println("Total number of tests failed " + result1.getFailureCount());

    }
}
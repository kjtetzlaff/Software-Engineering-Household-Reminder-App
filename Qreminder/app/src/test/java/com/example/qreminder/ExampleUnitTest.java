package com.example.qreminder;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //Test number 3
    @Test
    public void editTask(){
        Date tempDate;
        Date finalDate;
        List<String> aphabet = new ArrayList<String>();
        aphabet.add("a");
        aphabet.add("b");
        aphabet.add("c");
        aphabet.add("d");
        aphabet.add("e");
        aphabet.add("f");
        aphabet.add("g");
        aphabet.add("h");
        aphabet.add("i");
        aphabet.add("j");
        aphabet.add("k");
        aphabet.add("l");
        aphabet.add("m");
        aphabet.add("n");
        aphabet.add("o");
        aphabet.add("p");
        aphabet.add("q");
        aphabet.add("r");
        aphabet.add("s");
        aphabet.add("t");
        int x;
        int runTime = (int) (Math.random()*100)+1;

        for (x=0;x<runTime;x++) {
             tempDate = new Date(2014, 2, 11);
             finalDate = new Date((int)(Math.random()*1000)+2000, (int)(Math.random()*11)+1, (int)(Math.random()*20)+1);
             int finalFrequency = (int)(Math.random()*8)+1;
             String name = aphabet.get((int)(Math.random()*aphabet.size()));

            Task task1 = new Task("Take out trash", tempDate, 1, 2);
            Task finTask = new Task(name, finalDate, finalFrequency, 2);

            task1.setName(name);
            assertEquals(finTask.getName(), task1.getName());

            task1.setFrequency(finalFrequency);
            assertEquals(finTask.getFrequency(), task1.getFrequency());

            task1.setDateDue(finTask.getDateDue());
            assertEquals(finTask.getDateDue(), task1.getDateDue());
        }
        System.out.println(runTime+ " Iterations of different tasks checked");
        assertEquals(runTime,x);
    }
}
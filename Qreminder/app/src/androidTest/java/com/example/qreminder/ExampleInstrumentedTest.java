package com.example.qreminder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

import android.content.Context;

import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.qreminder", appContext.getPackageName());
    }

    @Test
    public void startClick() {
        onView(withId(R.id.startButton)).perform(click());
<<<<<<< Updated upstream
        onView(withText("Add Task")).check(matches(isDisplayed()));

    }
=======
        //onView(withText("Add Task")).check(matches(isDisplayed()));

    }

    //Test Number 2
    @Test
    public void addTasks(){
        onView(withId(R.id.startButton)).perform(click());
        try{
            Espresso.onView(ViewMatchers.withText("Edit Tasks")).perform(ViewActions.click());
            onView(withId(R.id.floatingActionButton)).perform(click());
            onView(withId(R.id.add_custom_task)).perform(click());

        } catch(Exception e) {onView(withId(R.id.add_custom_task)).perform(click());}

        int x = customTask((int) (Math.random()*100)+1);
        onView(withId(R.id.add_tasks_done)).perform(click());


    }


    //Creates x amount of custom tasks and adds them to the database.
    public int customTask(int x){
        int i = 0;
        List<String> choices = new ArrayList<String>();
        choices.add("Everyday");
        choices.add("Every 2 days");
        choices.add("Every 5 days");
        choices.add("Every 7 days");
        choices.add("Every 10 days");
        choices.add("Every 30 days");
        choices.add("Every 45 days");
        choices.add("Every 60 days");
        choices.add("Every 90 days");
        choices.add("Every 180 days");
        choices.add("Every 365 days");


        while (i<x) {
            if (i!=0){onView(withId(R.id.add_custom_task)).perform(click());}

            onView(withId(R.id.task_name)).perform(typeText("Task "+i));
            onView(withId(R.id.task_name)).perform(closeSoftKeyboard());
            onView(withId(R.id.reminder_dropdown)).perform(click());
            onView(withText(choices.get((int)(Math.random()*choices.size()))))
                    .inRoot(RootMatchers.isPlatformPopup())
                    .perform(click());
            onView(withId(R.id.createTaskDoneButton)).perform(click());
            i++;
        }
    return x;
    }

    //Test number 1
    @Test
    public void changeScreen(){
        onView(withId(R.id.startButton)).perform(click());
        onView(withText("Overdue Tasks:")).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withText("Edit Tasks")).perform(ViewActions.click());
        onView(withText("Tasks")).check(matches(isDisplayed()));
        onView(withId(R.id.floatingActionButton)).perform(click());
        onView(withText("Add Your Tasks +")).check(matches(isDisplayed()));
        onView(withId(R.id.add_custom_task)).perform(click());
        onView(withText("Customize Task")).check(matches(isDisplayed()));
        onView(withId(R.id.createTaskDoneButton)).perform(click());
        onView(withText("Add Your Tasks +")).check(matches(isDisplayed()));
        onView(withId(R.id.add_tasks_done)).perform(click());
        onView(withText("Overdue Tasks:")).check(matches(isDisplayed()));
    }

>>>>>>> Stashed changes
}
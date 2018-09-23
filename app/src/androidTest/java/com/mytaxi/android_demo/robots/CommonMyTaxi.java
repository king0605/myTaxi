package com.mytaxi.android_demo.robots;

/**
 * Created by sraj2 on 9/22/18.
 */

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.testdata.TestData;
import com.mytaxi.android_demo.testdata.TestSettings;

import org.junit.Rule;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * All generic automated actions not specific to any activity or class.
 *
 */

public class CommonMyTaxi extends TestSettings {

    // Robots should implement this in order to provide verification if a screen is displayed or not.
    public CommonMyTaxi closeKeyboard() {
        closeSoftKeyboard();
        return this;
    }

    public CommonMyTaxi goBack() {
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressBack();
        return this;
    }

    public void logIn(){
        onView(withId(R.id.edt_username)).perform(click(), typeText(TestData.username));
        onView(withId(R.id.edt_password)).perform(click(), typeText(TestData.password));
        closeKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        smallWait();
        onView(withId(R.id.textSearch)).check(matches(isDisplayed()));
    }

    public void callDriver(){
        onView(withId(R.id.fab)).perform(click());
        goBack();
    }

    /**
     * Performs a manual sleep. This method should be avoided till no work around found.
     */

    public CommonMyTaxi waitFor(int milliseconds) {
        SystemClock.sleep(milliseconds);
        return this;
    }

    public CommonMyTaxi smallWait() {
        return waitFor(1000);
    }




}

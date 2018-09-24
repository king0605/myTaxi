package com.mytaxi.android_demo.tests;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.robots.CommonMyTaxi;
import com.mytaxi.android_demo.testdata.TestData;
import com.mytaxi.android_demo.testdata.TestSettings;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mytaxi.android_demo.R.id.nav_logout;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by sraj2 on 9/22/18.
 */


@RunWith(AndroidJUnit4.class)
public class SearchDriverAndroidTest extends CommonMyTaxi {

    @Rule
    public ActivityTestRule<AuthenticationActivity> mlogInRule = new ActivityTestRule<>(AuthenticationActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> mactivityRule = new ActivityTestRule<>(MainActivity.class);

    @BeforeClass
    public static void init() {
        new TestSettings()
                .initialize();
    }

    @Before
    public void setUp() throws InterruptedException {
        new TestSettings()
                .initialize();
        mlogInRule.getActivity();
        logIn();
    }

    @Test
    public void Search() throws InterruptedException {
        mactivityRule.getActivity();
        onView(withId(R.id.textSearch)).perform(click());
        try {
            onView(withId(R.id.textSearch))
                    .perform(typeText(TestData.searchText));
            onView(withText(TestData.driverName))
                    .inRoot(withDecorView(not(is(mactivityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            onView(withText(TestData.driverName))
                    .inRoot(withDecorView(not(is(mactivityRule.getActivity().getWindow().getDecorView()))))
                    .perform(click());

        } catch (Exception e) {
            /**
             This code will be executed as a fallback, if the AutoCompleteTextView popup doesn't show up;
             unable to replicate but few times saw AutoCompleteTextView failed to display
             */

            onView(withText(TestData.driverName))
                    .inRoot(withDecorView(not(is(mactivityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            onView(withId(R.id.textSearch))
                    .perform(ViewActions.pressKey(KeyEvent.KEYCODE_S));
            smallWait();
            onView(withId(R.id.textSearch))
                    .perform(ViewActions.pressKey(KeyEvent.KEYCODE_A));
            onView(withText(TestData.driverName))
                    .inRoot(withDecorView(not(is(mactivityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            onView(withText(TestData.driverName))
                    .inRoot(withDecorView(not(is(mactivityRule.getActivity().getWindow().getDecorView()))))
                    .perform(click());
        }
        callDriver();
        logOut();

    }


    public void logOut() throws InterruptedException {
        smallWait();
        onView(withContentDescription(mactivityRule.getActivity().getString(R.string.navigation_drawer_open))).perform(click());
        smallWait();
        onView(allOf(withText(R.string.text_item_title_logout), isDisplayed()));
        onView(allOf(withText(R.string.text_item_title_logout), isDisplayed())).perform(click());
    }


}

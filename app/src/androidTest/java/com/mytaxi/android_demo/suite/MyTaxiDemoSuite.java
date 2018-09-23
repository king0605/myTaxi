package com.mytaxi.android_demo.suite;

import com.mytaxi.android_demo.tests.SearchDriverAndroidTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by sraj2 on 9/22/18.
 */
/**
     * Class with JUnit annotations for executing a specific test suite. Suites are executed in the order
     * specified here.
 */
    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            SearchDriverAndroidTest.class,

    })
    public class MyTaxiDemoSuite {}



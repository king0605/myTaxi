package com.mytaxi.android_demo.testdata;

import android.Manifest;
import android.os.Build;
import android.util.Log;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Automated actions related to the application as a whole are reflected here.
 */
public class TestSettings {

	private final static String TAG = TestSettings.class.getSimpleName();
	public TestSettings initialize() {
		grantPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
		grantPermission(Manifest.permission.READ_CONTACTS);
		grantPermission(Manifest.permission.SET_ANIMATION_SCALE);
		grantPermission(Manifest.permission.RECEIVE_SMS);
		disableAnimations();
		return this;
	}

	// Grant permissions to preempt any system dialogs
	public TestSettings grantPermission(String permission) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			Log.i("Granting permission:", permission);
			getInstrumentation().getUiAutomation().executeShellCommand("pm grant " + getTargetContext().getPackageName() + " " + permission);
		}
		return this;
	}

	// Disable animations at runtime for test stability
	public TestSettings disableAnimations() {
		getInstrumentation().getUiAutomation().executeShellCommand("settings put global window_animation_scale 0.0");
		getInstrumentation().getUiAutomation().executeShellCommand("settings put global transition_animation_scale 0.0");
		getInstrumentation().getUiAutomation().executeShellCommand("settings put global animator_duration_scale 0.0");
		return this;
	}




}

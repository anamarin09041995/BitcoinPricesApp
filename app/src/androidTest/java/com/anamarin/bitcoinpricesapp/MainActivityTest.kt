package com.anamarin.bitcoinpricesapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.anamarin.bitcoinpricesapp.presentation.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun WhenUpdateButtonIsPressedInfoIsUpdated() {
        onView(withId(R.id.updateBtn)).perform(click())
        //onView(withId(R.id.lineChart)).check(ViewAssertion())
    }
}

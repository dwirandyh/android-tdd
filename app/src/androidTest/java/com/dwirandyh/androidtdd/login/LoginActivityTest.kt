package com.dwirandyh.androidtdd.login

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import com.dwirandyh.androidtdd.LoginActivity
import com.dwirandyh.androidtdd.R
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField // for generate Getter & Setter java
    var activityRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun checkEmailAndPasswordFieldIsVisible() {
        onView(withId(R.id.edit_email)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_password)).check(matches(isDisplayed()))
    }

    @Test
    fun checkErrorMessageIsDisplayedForEmptyData() {
        onView(withId(R.id.btn_login))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("Please check your email or password."))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkErrorMaxLoginAttempt(){
        onView(withId(R.id.edit_email))
            .perform(typeText("zxy"), closeSoftKeyboard())
        onView(withId(R.id.edit_password))
            .perform(typeText("abc"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_login)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_login)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_login)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(2000)
        onView(withText("You have exceeded MAX attempt")).check(matches(isDisplayed()))
    }

    @Test
    fun checkLoginSuccess() {
        onView(withId(R.id.edit_email))
            .perform(typeText("dwi"), closeSoftKeyboard())
        onView(withId(R.id.edit_password))
            .perform(typeText("randy"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).check(matches(isDisplayed())).perform(click())
        onView(withText("Login Successful")).check(matches(isDisplayed()))
    }


}
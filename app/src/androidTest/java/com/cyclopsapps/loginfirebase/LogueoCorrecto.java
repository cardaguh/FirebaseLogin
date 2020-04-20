package com.cyclopsapps.loginfirebase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LogueoCorrecto {
    public static final String STRING_TO_BE_TYPED = "pruebacalidadd@gmail.com";
    public static final String STRING_TO_BE_TYPED2 = "prueba123456";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loguearUsuario() {
        onView(withId(R.id.etEmail))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.etPassword))
                .perform(typeText(STRING_TO_BE_TYPED2), closeSoftKeyboard());
        onView(withId(R.id.btnSignUp)).perform(click());

        // Check that the text was changed.
        //onView(withId(R.id.STRING_TO_BE_TYPED)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

}

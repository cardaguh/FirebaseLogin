package com.cyclopsapps.loginfirebase;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LogueoFirebase {
    public static final String STRING_TO_BE_TYPED = "prueba@gmail.com";
    public static final String STRING_TO_BE_TYPED2 = "prueba123";

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void logInFirebase() {
        onView(withId(R.id.etEmail))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.etPassword))
                .perform(typeText(STRING_TO_BE_TYPED2), closeSoftKeyboard());
        onView(withId(R.id.btnSignUp)).perform(click());

        // Check that the text was changed.
        //onView(withId(R.id.STRING_TO_BE_TYPED)).check(matches(withText(STRING_TO_BE_TYPED)));
    }
}

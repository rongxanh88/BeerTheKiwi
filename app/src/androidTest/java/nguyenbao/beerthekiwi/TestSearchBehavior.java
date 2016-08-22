//package nguyenbao.beerthekiwi;
//
//import android.support.test.filters.LargeTest;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
///**
// * Created by Bao Nguyen on 8/18/2016.
// */
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class TestSearchBehavior {
//
//    public static final String CITY_TO_BE_TYPED = "Denver";
//    public static final String POSTAL_CODE_TO_BE_TYPED = "80202";
//    public static final String REGION_TO_BE_TYPED = "Colorado";
//    public static final String COUNTRY_TO_BE_TYPED = "United States";
//
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
//            MainActivity.class);
//
//    //    @Test
////    public void changeText_sameActivity() {
////        // Type text and then press the button.
////        onView(withId(R.id.editTextUserInput))
////                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
////        onView(withId(R.id.changeTextBt)).perform(click());
////
////        // Check that the text was changed.
////        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
////    }
////
////    @Test
////    public void changeText_newActivity() {
////        // Type text and then press the button.
////        onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED),
////                closeSoftKeyboard());
////        onView(withId(R.id.activityChangeTextBtn)).perform(click());
////
////        // This view is in a different Activity, no need to tell Espresso.
////        onView(withId(R.id.show_text_view)).check(matches(withText(STRING_TO_BE_TYPED)));
////    }
//}

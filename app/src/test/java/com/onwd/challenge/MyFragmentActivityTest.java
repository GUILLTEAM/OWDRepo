/*package com.onwd.challenge;


*****

 The test would permit to assert the good performance of the Search Fragment
1 - performing a click on the search button and assert that the Device List resulted is not empty

*****

import com.onwd.challenge.fragment.DeviceSearch;
import junit.framework.TestCase;
import static org.junit.Assert.assertNotNull;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MyFragmentActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity myFragmentActivity;
    DeviceSearch myFragment;

    public MyFragmentActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFragmentActivity = (MainActivity) getActivity();
        myFragment = myFragmentActivity.myFragment;
    }

    public void testPreConditions() {
        assertNotNull(myFragmentActivity);
        assertNotNull(myFragment);
    }

    public void testSearchDevice() {
        onView(withId(R.id.search_button)).perform(click());
        assertFalse(myFragment.users.isEmpty());
       onView(withId(R.id.button_open)).perform(click());

    }
}*/
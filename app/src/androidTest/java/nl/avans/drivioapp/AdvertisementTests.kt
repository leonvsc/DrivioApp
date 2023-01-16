package nl.avans.drivioapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AdvertisementTests {

    @Test
    fun getAdvertisementTitle() {

        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.tvTitle))
            .check(matches(withText("Audi A5")))
    }

    @Test
    fun makeReservation() {

        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.btnReserve))
            .perform(click())
        onView(withId(R.id.tvResult))
            .check(matches(withText("Reservation succesfull!!")))
    }

    @Test
    fun editAdvertisement() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.profileFragment))
            .perform(click())

        onView(withId(R.id.register_btn))
            .perform(click())

        onView(withId(R.id.edit_email))
            .perform(typeText("hello@hello.nl"))

        onView(withId(R.id.edit_password))
            .perform(typeText("123"))

        onView(withId(R.id.register_user_btn))
            .perform(click())

        onView(withId(R.id.floatingActionButton2))
            .perform(click())

        onView(withId(R.id.login_btn))
            .perform(click())

        onView(withId(R.id.email_login))
            .perform(typeText("hello@hello.nl"))

        onView(withId(R.id.password_login))
            .perform(typeText("123"))

        onView(withId(R.id.login))
            .perform(click())

        onView(withId(R.id.btnAdvertisement))
            .perform(click())

        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.ibtnEdit))
            .perform(click())

        onView(withId(R.id.etTitle))
            .perform(replaceText("Volkswagen Tiguan"))

        onView(withId(R.id.etDescription))
            .perform(replaceText("Een hele mooie Volkswagen Tiguan"))

        onView(withId(R.id.etPrice))
            .perform(replaceText("60"))

        onView(withId(R.id.btnConfirm))
            .perform(click())

        onView(withId(R.id.tvResult))
            .check(matches(withText("Advertisement succesfull updated!!")))
    }
}
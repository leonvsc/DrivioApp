package nl.avans.drivioapp.database

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import nl.avans.drivioapp.model.User
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDatabaseTest : TestCase() {

    private lateinit var database: UserDatabase
    private lateinit var dao: UserDao


    // this method will be called before each test function
    @Before
    public override fun setUp() {
        // instantiate database
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java).build()
        dao = database.userDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    // runBlocking because addUser() = suspended fun
    @Test
    fun insertUserToDatabaseIsSuccessful() = runBlocking {
        val user = User(1, "jan@avans.nl", "123")
        dao.addUser(user)

        val newUser = dao.getUsers()
        assertThat(newUser.contains(user)).isTrue()
    }

    @Test
    fun emptyPasswordReturnsFalse() = runBlocking {
        val user = User(1, "jan@avans.nl", "")
        dao.addUser(user)

        val userCredential = dao.getUserCredential(user.email, user.password)
        assertThat(userCredential.password).isNotEmpty()
    }

    @Test
    fun emailAlreadyExists() = runBlocking {
        val emails = listOf("tom@avans.nl", "jerry@avans.nl")

        val user = User(1, emails[0], "111")
        dao.addUser(user)

        val newUser = dao.getUserCredential(user.email, user.password)

        for (email in emails) {
            if (email[0].toString() == user.email) {
                assertThat(newUser.email).matches(email)
            }
        }
    }
}







package redbible.basic

import org.junit.Assert.assertEquals
import org.junit.Test
import redbible.basic.ui.MainActivity

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val mainActivity = MainActivity()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testtt() {
        mainActivity.call()
//        assertEquals(5, mainActivity.getValue())
    }
}

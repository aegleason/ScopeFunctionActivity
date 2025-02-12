package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testData = getTestDataArray()
        Log.d("MainActivity", "Generated sorted list: $testData")

        val sampleList = listOf(10, 20, 30, 40, 50)
        val exampleView = getView(2, null, sampleList, this)

        // findViewById<LinearLayout>(R.id.container).addView(exampleView)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    /* private fun getTestDataArray() : List<Int> {
        val testArray = MutableList(10){ Random.nextInt()}
        testArray.sort()
        return testArray
    }
     */

    private fun getTestDataArray(): List<Int> = List(10) { (1..100).random() }.sorted()
    // SEF for lab 2/12. Sorts list in ascending order. Tried to be as brief as possible


    // Return true if average value in list is greater than median value, false otherwise
    /* private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean {
        val avg = listOfNumbers.average()
        val sortedList = listOfNumbers.sorted()
        val median = if (sortedList.size % 2 == 0)
            (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
        else
            sortedList[sortedList.size / 2]

        return avg < median
    }

     */

    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean =
        listOfNumbers.run { average() < sorted().let { it[it.size / 2] } }
    // SEF for lab 2/12. average() computes mean, sorted() sorts, etc. let gets median

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    /* private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View {
        val textView: TextView

        if (recycledView != null) {
            textView = recycledView as TextView
        } else {
            textView = TextView(context)
            textView.setPadding(5, 10, 10, 0)
            textView.textSize = 22f
        }

        textView.text = collection[position].toString()

        return textView
    }

     */

    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView ?: TextView(context).apply { layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) }).apply {
            if (this is TextView) text = collection[position].toString()
        }

    // if recycledView isn't null then reuse it
    // apply sets layoutParams and makes sure a recycledView lays out correctly

}
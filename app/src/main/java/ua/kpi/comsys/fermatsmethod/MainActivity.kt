package ua.kpi.comsys.fermatsmethod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.calculate)
        val notFoundText = findViewById<TextView>(R.id.not_found)
        val divisor1 = findViewById<TextView>(R.id.divisor1)
        val divisor2 = findViewById<TextView>(R.id.divisor2)
        val numberView = findViewById<EditText>(R.id.number)
        notFoundText.isVisible = false
        button.setOnClickListener {
            notFoundText.isVisible = false
            divisor1.text = ""
            divisor2.text = ""
            val number = numberView.text.toString()
            if (number != "") {
                val result = FermatsMethod(number.toInt())
                if (result.first == -1) {
                    notFoundText.isVisible = true
                } else {
                    divisor1.text = result.first.toString()
                    divisor2.text = result.second.toString()
                }
            }
        }
    }

    fun FermatsMethod(number: Int) : Pair<Int, Int> {
        val initialValue = Math.sqrt(number.toDouble())
        val value = ceil(initialValue)
        var intValue = value.toInt()
        while (intValue != (number + 1) / 2) {
            val diff = intValue * intValue - number
            val sqrt = isPerfectSquare(diff)
            if (sqrt != -1) {
                return Pair(intValue + sqrt, intValue - sqrt)
            }
            ++intValue
        }
        return Pair(-1, -1)
    }

    fun isPerfectSquare(number : Int) : Int {
        val sqrt = Math.sqrt(number.toDouble())
        if (ceil(sqrt) == sqrt) return sqrt.toInt()
        return -1
    }
}
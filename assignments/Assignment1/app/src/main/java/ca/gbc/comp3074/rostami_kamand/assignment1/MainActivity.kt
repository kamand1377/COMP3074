package ca.gbc.comp3074.rostami_kamand.assignment1

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etHours: EditText
    private lateinit var etRate: EditText
    private lateinit var etTaxRate: EditText
    private lateinit var tvPay: TextView
    private lateinit var tvOvertime: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvTax: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Push the content down by their heights so nothing is hidden.
        val root = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val tv = TypedValue()
            theme.resolveAttribute(androidx.appcompat.R.attr.actionBarSize, tv, true)
            val actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
            val pad = (24 * resources.displayMetrics.density).toInt()  // the 24dp padding from the XML
            v.setPadding(bars.left + pad, bars.top + actionBarHeight + pad, bars.right + pad, bars.bottom + pad)
            insets
        }

        // INPUT: connect Kotlin variables to the views in the XML
        etHours = findViewById(R.id.etHours)
        etRate = findViewById(R.id.etRate)
        etTaxRate = findViewById(R.id.etTaxRate)
        tvPay = findViewById(R.id.tvPay)
        tvOvertime = findViewById(R.id.tvOvertime)
        tvTotal = findViewById(R.id.tvTotal)
        tvTax = findViewById(R.id.tvTax)

        findViewById<Button>(R.id.btnCalculate).setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        // toDoubleOrNull() returns null instead of crashing on empty/bad input
        val hours = etHours.text.toString().toDoubleOrNull()
        val rate = etRate.text.toString().toDoubleOrNull()
        val taxRate = etTaxRate.text.toString().toDoubleOrNull()

        if (hours == null) { etHours.error = "Enter hours worked"; return }
        if (rate == null) { etRate.error = "Enter hourly rate"; return }
        if (taxRate == null) { etTaxRate.error = "Enter tax rate"; return }

        // CALCULATION
        val pay: Double
        val overtimePay: Double
        if (hours <= 40) {
            pay = hours * rate
            overtimePay = 0.0
        } else {
            pay = 40 * rate
            overtimePay = (hours - 40) * rate * 1.5
        }
        val totalPay = pay + overtimePay
        val tax = pay * taxRate

        // OUTPUT
        tvPay.text = "Pay: $%.2f".format(pay)
        tvOvertime.text = "Overtime pay: $%.2f".format(overtimePay)
        tvTotal.text = "Total pay: $%.2f".format(totalPay)
        tvTax.text = "Tax: $%.2f".format(tax)
    }

    // NAVIGATION: put the menu in the ActionBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // NAVIGATION: react when "About" is tapped
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
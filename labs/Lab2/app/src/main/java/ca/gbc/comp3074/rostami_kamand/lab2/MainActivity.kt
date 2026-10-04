package ca.gbc.comp3074.rostami_kamand.lab2
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // The app's memory (state)
    private var count = 0
    private var step = 1

    private lateinit var textOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textOutput = findViewById(R.id.textOutput)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonReset = findViewById<Button>(R.id.buttonReset)
        val buttonStep = findViewById<Button>(R.id.buttonStep)

        buttonAdd.setOnClickListener {
            count += step
            updateOutput()
        }

        buttonSubtract.setOnClickListener {
            count -= step
            updateOutput()
        }

        buttonReset.setOnClickListener {
            count = 0
            step = 1          // back to default behaviour
            updateOutput()
        }

        buttonStep.setOnClickListener {
            step = 2          // new behaviour: +/- by two
        }

        updateOutput()
    }

    private fun updateOutput() {
        textOutput.text = count.toString()
    }
}
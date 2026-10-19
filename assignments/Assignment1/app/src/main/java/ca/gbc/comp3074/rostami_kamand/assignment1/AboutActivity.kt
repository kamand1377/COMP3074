package ca.gbc.comp3074.rostami_kamand.assignment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.title = "About"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // shows the ← arrow
    }

    // Called when the ← arrow is tapped
    override fun onSupportNavigateUp(): Boolean {
        finish()  // close this screen; the existing MainActivity reappears
        return true
    }
}
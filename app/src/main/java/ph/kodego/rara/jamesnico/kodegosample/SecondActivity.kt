package ph.kodego.rara.jamesnico.kodegosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//
//        title = findViewById(R.id.title)
//
//        title.text = "New Second Activity"
    }
}
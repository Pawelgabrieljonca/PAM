package pbs.edu.lab3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RelativeLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ustawienie layoutu activity_relative.xml (przykład widoku Rys. 6)
        setContentView(R.layout.activity_relative)
    }
}
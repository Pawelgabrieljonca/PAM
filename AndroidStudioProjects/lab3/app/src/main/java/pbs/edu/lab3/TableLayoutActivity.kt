package pbs.edu.lab3


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TableLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ustawienie layoutu activity_table.xml (przykład widoku Rys. 7)
        setContentView(R.layout.activity_table)
    }
}
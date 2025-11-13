package pbs.edu.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Znajdowanie przycisków po ID (bez View Binding)
        val btnLinear: Button = findViewById(R.id.btnLinear)
        val btnRelative: Button = findViewById(R.id.btnRelative)
        val btnTable: Button = findViewById(R.id.btnTable)

        // Obsługa kliknięcia przycisku btnLinear
        btnLinear.setOnClickListener {
            val intent = Intent(this, LinearLayoutActivity::class.java)
            startActivity(intent)
        }

        // Obsługa kliknięcia przycisku btnRelative
        btnRelative.setOnClickListener {
            val intent = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intent)
        }

        // Obsługa kliknięcia przycisku btnTable
        btnTable.setOnClickListener {
            val intent = Intent(this, TableLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}
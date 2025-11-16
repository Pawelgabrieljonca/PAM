package pbs.edu.lab05

import android.os.Bundle
import android.view.KeyEvent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SimpleListsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_lists)

        val carList = findViewById<ListView>(R.id.carList)
        val cars = arrayOf(
            "Toyota", "BMW", "Audi", "Mercedes", "Ford", "Honda", "Tesla",
            "Volkswagen", "Volvo", "Mazda", "Ferrari", "Lamborghini", "Porsche",
            "Bugatti", "Maserati", "Jaguar"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cars)
        carList.adapter = adapter

        carList.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedCar = cars[position]
                Toast.makeText(applicationContext, "Wybrano: $selectedCar", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

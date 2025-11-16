package pbs.edu.lab05

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Random

class HarderListsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var mAdapter: CarsAdapter? = null
    private val wybor: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harder_lists)

        recyclerView = findViewById(R.id.recycler_view)

        // --- Start of changes ---

        val possibleCars = arrayOf(
            "Toyota", "BMW", "Audi", "Mercedes", "Ford",
            "Honda", "Tesla", "Volkswagen", "Volvo", "Mazda"
        )
        val rand = Random()

        // Create a MutableList of Car objects instead of an Array of Strings
        val cars = MutableList(30) {
            val carName = "${possibleCars[rand.nextInt(possibleCars.size)]} ${it + 1}"
            Car(carName) // Create a Car object
        }

        // --- End of changes ---


        if (wybor) {
            val mLayoutManager = GridLayoutManager(applicationContext, 4)
            recyclerView.layoutManager = mLayoutManager
            recyclerView.itemAnimator = DefaultItemAnimator()
            mAdapter = CarsAdapter(cars) // Now passing the correct type
            recyclerView.adapter = mAdapter
        } else {
            recyclerView.layoutManager = LinearLayoutManager(this)
            mAdapter = CarsAdapter(cars) // Now passing the correct type
            recyclerView.adapter = mAdapter
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            finish()
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }
}

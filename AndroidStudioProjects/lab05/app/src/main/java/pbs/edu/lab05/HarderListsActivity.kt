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
    private var mAdapter: SimpleCarsAdapter? = null
    private val wybor: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_harder_lists)

        recyclerView = findViewById(R.id.recycler_view)

        val possibleCars = arrayOf(
            "Toyota", "BMW", "Audi", "Mercedes", "Ford",
            "Honda", "Tesla", "Volkswagen", "Volvo", "Mazda"
        )
        val rand = Random()

        // Tworzymy dummy imageResId (nie będziemy go używać)
        val cars = MutableList(30) {
            val carName = "${possibleCars[rand.nextInt(possibleCars.size)]} ${it + 1}"
            Car(carName, R.drawable.ic_launcher_foreground) // dummy
        }

        if (wybor) {
            val mLayoutManager = GridLayoutManager(applicationContext, 4)
            recyclerView.layoutManager = mLayoutManager
            recyclerView.itemAnimator = DefaultItemAnimator()
            mAdapter = SimpleCarsAdapter(cars) // Używamy SimpleCarsAdapter
            recyclerView.adapter = mAdapter
        } else {
            recyclerView.layoutManager = LinearLayoutManager(this)
            mAdapter = SimpleCarsAdapter(cars) // Używamy SimpleCarsAdapter
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
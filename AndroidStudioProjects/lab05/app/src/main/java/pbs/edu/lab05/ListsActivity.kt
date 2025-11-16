package pbs.edu.lab05

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListsActivity : AppCompatActivity(), CarsAdapter.OnCarInteractionListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarsAdapter
    private val cars = mutableListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lists)

        recyclerView = findViewById(R.id.recyclerView)

        val sampleNames = listOf("Toyota", "BMW", "Audi", "Mercedes", "Ford", "Honda", "Tesla", "Volkswagen")
        val sampleImages = listOf(
            R.drawable.car_toyota, R.drawable.car_bmw, R.drawable.car_audi, R.drawable.car_mercedes,
            R.drawable.car_ford, R.drawable.car_honda, R.drawable.car_tesla, R.drawable.car_vw
        )

        for (i in 1..100) {
            val idx = (i - 1) % sampleNames.size
            cars.add(Car("${sampleNames[idx]} $i", sampleImages[idx]))
        }

        adapter = CarsAdapter(cars, this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    override fun onDelete(position: Int) {
        val removed = cars.removeAt(position)
        adapter.notifyItemRemoved(position)
        Toast.makeText(this, "Usunięto: ${removed.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onNameLongClicked(name: String) {
        Toast.makeText(this, "Długie kliknięcie: $name", Toast.LENGTH_SHORT).show()
    }

    override fun onImageTouched(name: String) {
        Toast.makeText(this, "Dotknięto obrazka: $name", Toast.LENGTH_SHORT).show()
    }
}

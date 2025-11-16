package pbs.edu.lab05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarsAdapter(
    private val cars: List<Car>,
    private val listener: OnCarInteractionListener // Listener passed from the Activity
) : RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {

    // --- ADD THIS INTERFACE ---
    interface OnCarInteractionListener {
        fun onDelete(position: Int)
        fun onNameLongClicked(name: String)
        fun onImageTouched(name: String)
    }
    // --------------------------

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carName: TextView = itemView.findViewById(R.id.car_name)
        val carImage: ImageView = itemView.findViewById(R.id.car_image)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)

        init {
            // Setup listeners to call back to the Activity
            deleteButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onDelete(adapterPosition)
                }
            }

            carName.setOnLongClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onNameLongClicked(cars[adapterPosition].name)
                }
                true // Return true to indicate the event was handled
            }

            carImage.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onImageTouched(cars[adapterPosition].name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.carName.text = car.name
        holder.carImage.setImageResource(car.imageResId)
    }

    override fun getItemCount() = cars.size
}

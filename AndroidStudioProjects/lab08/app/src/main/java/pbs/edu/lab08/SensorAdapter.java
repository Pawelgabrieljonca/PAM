package pbs.edu.lab08;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class SensorAdapter extends ArrayAdapter<Sensor> {

    public SensorAdapter(@NonNull Context context, List<Sensor> sensors) {
        super(context, 0, sensors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_sensor, parent, false);
        }

        Sensor sensor = getItem(position);
        TextView tvName = convertView.findViewById(R.id.tvSensorName);
        ImageView imgIcon = convertView.findViewById(R.id.imgSensorIcon);

        if (sensor != null) {
            tvName.setText(sensor.getName());

            // Prosta logika doboru ikon (możesz rozwinąć case'y zgodnie z tabelą w PDF)
            // Używam domyślnej ikony, aby kod był czytelny, ale tu jest miejsce na Twój switch.
            switch (sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    // imgIcon.setImageResource(R.drawable.ic_movement);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    // imgIcon.setImageResource(R.drawable.ic_rotation);
                    break;
                // ... reszta case'ów
                default:
                    imgIcon.setImageResource(R.drawable.ic_sensor); // Domyślna ikona
                    break;
            }
        }
        return convertView;
    }
}
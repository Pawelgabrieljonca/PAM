package pbs.edu.lab07;

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
        // Sprawdzamy, czy widok już istnieje, jeśli nie - tworzymy go
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_sensor, parent, false);
        }

        // Pobieramy aktualny sensor z listy
        Sensor currentSensor = getItem(position);

        // Znajdujemy elementy widoku
        TextView tvName = convertView.findViewById(R.id.tvSensorName);
        ImageView imgIcon = convertView.findViewById(R.id.imgSensorIcon);

        // Ustawiamy nazwę sensora
        if (currentSensor != null) {
            tvName.setText(currentSensor.getName());

            // Opcjonalnie: Tutaj można dodać logikę 'switch' zmieniającą ikonę
            // w zależności od currentSensor.getType(), zgodnie z tabelą w PDF.
            // Dla uproszczenia ustawiamy ikonę domyślną przygotowaną w Kroku 1.
            imgIcon.setImageResource(R.drawable.ic_sensor);
        }

        return convertView;
    }
}
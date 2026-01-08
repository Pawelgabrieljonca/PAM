package pbs.edu.lab08;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class SensorAdapter extends ArrayAdapter<Sensor> {
    public SensorAdapter(Context context, List<Sensor> sensors) {
        super(context, 0, sensors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_sensor, parent, false);
        }

        Sensor sensor = getItem(position);
        TextView nameText = convertView.findViewById(R.id.sensorName);
        ImageView iconImage = convertView.findViewById(R.id.sensorIcon);

        nameText.setText(sensor.getName());

        // Wybór ikonki na podstawie typu sensora [cite: 22]
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                iconImage.setImageResource(android.R.drawable.ic_menu_directions);
                break;
            case Sensor.TYPE_GYROSCOPE:
                iconImage.setImageResource(android.R.drawable.ic_menu_rotate);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                iconImage.setImageResource(android.R.drawable.ic_menu_compass);
                break;
            case Sensor.TYPE_LIGHT:
                iconImage.setImageResource(android.R.drawable.ic_menu_day);
                break;
            default:
                iconImage.setImageResource(android.R.drawable.ic_menu_info_details);
                break;
        }
        return convertView;
    }
}
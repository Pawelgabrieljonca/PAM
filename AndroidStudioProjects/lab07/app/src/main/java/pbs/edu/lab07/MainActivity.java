package pbs.edu.lab07;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.sensorListView);
        Button btnSend = findViewById(R.id.btnSendSms);

        // 1. Pobranie SensorManager i listy sensorów [cite: 50]
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // 2. Ustawienie adaptera do wyświetlenia listy [cite: 51]
        SensorAdapter adapter = new SensorAdapter(this, sensorList);
        listView.setAdapter(adapter);

        // 3. Obsługa przycisku wysyłania SMS [cite: 52]
        btnSend.setOnClickListener(v -> sendSmsWithSensors());
    }

    private void sendSmsWithSensors() {
        if (sensorList == null || sensorList.isEmpty()) {
            Toast.makeText(this, "Brak sensorów do wysłania!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Budowanie treści wiadomości [cite: 53]
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Lista sensorów w moim urządzeniu:\n");

        for (Sensor sensor : sensorList) {
            messageBuilder.append("- ").append(sensor.getName()).append("\n");
        }

        String message = messageBuilder.toString();

        // Tworzenie Intentu do wysłania SMS (bez uprawnień w Manifest) [cite: 54]
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:")); // Tylko aplikacje SMS obsłużą ten intent
        intent.putExtra("sms_body", message); // Treść wiadomości

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Nie znaleziono aplikacji SMS.", Toast.LENGTH_SHORT).show();
        }
    }
}
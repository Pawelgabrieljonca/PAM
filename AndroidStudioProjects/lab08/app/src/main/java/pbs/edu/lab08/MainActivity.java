package pbs.edu.lab08;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private List<Sensor> sensorList;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Obsługa uprawnień [cite: 55]
        checkPermissions();

        // 2. Pobieranie listy sensorów [cite: 14, 48]
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        // 3. Konfiguracja ListView
        ListView listView = findViewById(R.id.sensorListView);
        SensorAdapter adapter = new SensorAdapter(this, sensorList);
        listView.setAdapter(adapter);

        // 4. Inicjalizacja klienta lokalizacji [cite: 53]
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // 5. Obsługa przycisku SMS [cite: 16, 51]
        findViewById(R.id.btnSendSms).setOnClickListener(v -> prepareAndSendSms());
    }

    private void checkPermissions() {
        String[] perms = {Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION};
        if (ActivityCompat.checkSelfPermission(this, perms[0]) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, perms[1]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, perms, 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Jeśli użytkownik odmówi uprawnień, aplikacja się zamyka [cite: 21, 55]
        if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            finish();
        }
    }

    private void prepareAndSendSms() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                StringBuilder sb = new StringBuilder("Lista sensorów:\n");
                for (Sensor s : sensorList) sb.append("- ").append(s.getName()).append("\n");

                if (location != null) {
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    // Dodanie lokalizacji i linku do Google Maps [cite: 18, 52]
                    sb.append("\nLokalizacja: (").append(lat).append(", ").append(lon).append(")");
                    sb.append("\nLink: https://maps.google.com/?q=lat,lng").append(lat).append(",").append(lon);
                }

                // Wysyłanie przez Intent [cite: 17, 54]
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"));
                intent.putExtra("sms_body", sb.toString());
                startActivity(intent);
            });
        }
    }
}
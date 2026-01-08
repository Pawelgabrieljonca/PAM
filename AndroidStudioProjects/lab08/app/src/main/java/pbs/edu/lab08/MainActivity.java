package pbs.edu.lab08;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private ListView listView;
    private List<Sensor> sensorList;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.sensorListView);
        Button btnSend = findViewById(R.id.btnSendSms);

        // Inicjalizacja klienta lokalizacji
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Sprawdzenie i żądanie uprawnień na starcie [cite: 56, 91]
        checkAndRequestPermissions();

        // Pobranie i wyświetlenie sensorów [cite: 50, 51]
        loadSensors();

        btnSend.setOnClickListener(v -> prepareAndSendSms());
    }

    private void loadSensors() {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        SensorAdapter adapter = new SensorAdapter(this, sensorList);
        listView.setAdapter(adapter);
    }

    private void checkAndRequestPermissions() {
        String[] permissions = {
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        if (!hasPermissions(permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean hasPermissions(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    // Obsługa wyniku żądania uprawnień
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            if (grantResults.length > 0) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        allGranted = false;
                        break;
                    }
                }
            } else {
                allGranted = false;
            }

            if (!allGranted) {
                Toast.makeText(this, "Wymagane uprawnienia zostały odrzucone. Zamykanie aplikacji.", Toast.LENGTH_LONG).show();
                finish(); // Zamknij aplikację jeśli brak uprawnień
            }
        }
    }

    private void prepareAndSendSms() {
        // Sprawdzamy uprawnienia ponownie przed akcją
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Brak uprawnień do lokalizacji", Toast.LENGTH_SHORT).show();
            return;
        }

        // Pobranie ostatniej lokalizacji [cite: 54, 89]
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    String locationText = "Lokalizacja nieznana";
                    String mapLink = "";

                    if (location != null) {
                        double lat = location.getLatitude();
                        double lng = location.getLongitude();
                        locationText = "Lat: " + lat + ", Lng: " + lng;
                        // Link do map Google [cite: 54]
                        mapLink = "http://maps.google.com/?q=" + lat + "," + lng;
                    }

                    sendSmsIntent(locationText, mapLink);
                });
    }

    private void sendSmsIntent(String locationInfo, String mapLink) {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista sensorów:\n");
        for (Sensor s : sensorList) {
            sb.append("- ").append(s.getName()).append("\n");
        }
        sb.append("\nMoja lokalizacja:\n").append(locationInfo);
        if (!mapLink.isEmpty()) {
            sb.append("\nMapa: ").append(mapLink);
        }

        // Wysyłanie przez Intent [cite: 53, 90]
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"));
        intent.putExtra("sms_body", sb.toString());

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Nie znaleziono aplikacji SMS", Toast.LENGTH_SHORT).show();
        }
    }
}
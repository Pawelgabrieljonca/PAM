package pbs.edu.lab06;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja przycisków
        Button btnSingle = findViewById(R.id.btnSingleChoice);
        Button btnMulti = findViewById(R.id.btnMultiChoice);
        Button btnTime = findViewById(R.id.btnTime);
        Button btnDate = findViewById(R.id.btnDate);
        Button btnCustom = findViewById(R.id.btnCustom);

        // Ustawienie słuchaczy (Listeners)
        btnSingle.setOnClickListener(v -> new SingleChoiceDialogFragment().show(getSupportFragmentManager(), "single"));
        btnMulti.setOnClickListener(v -> new MultiChoiceDialogFragment().show(getSupportFragmentManager(), "multi"));
        btnTime.setOnClickListener(v -> new TimePickerDialogFragment().show(getSupportFragmentManager(), "time"));
        btnDate.setOnClickListener(v -> new DatePickerDialogFragment().show(getSupportFragmentManager(), "date"));
        btnCustom.setOnClickListener(v -> new CustomDialogFragment().show(getSupportFragmentManager(), "custom"));
    }
}
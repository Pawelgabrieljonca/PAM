package pbs.edu.lab06;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Użyj aktualnej daty jako domyślnej
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Formatowanie dd.MM.yyyy (miesiące są indeksowane od 0, więc dodajemy 1)
        String date = String.format("%02d.%02d.%04d", dayOfMonth, month + 1, year);
        Toast.makeText(getActivity(), "Wybrana data: " + date, Toast.LENGTH_SHORT).show();
    }
}
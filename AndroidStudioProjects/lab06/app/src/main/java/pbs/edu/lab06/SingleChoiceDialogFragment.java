package pbs.edu.lab06;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class SingleChoiceDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Przykładowa lista opcji
        final String[] colors = {"Czerwony", "Zielony", "Niebieski", "Żółty"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Wybierz kolor")
                .setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Wyświetlenie wyniku w Toast
                        Toast.makeText(getActivity(), "Wybrano: " + colors[which], Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
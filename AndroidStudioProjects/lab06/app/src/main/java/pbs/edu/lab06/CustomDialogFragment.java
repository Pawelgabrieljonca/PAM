package pbs.edu.lab06;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // "Nadmuchiwanie" (inflate) widoku z pliku XML
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);

        final EditText etInput = dialogView.findViewById(R.id.etInput);

        builder.setView(dialogView)
                .setTitle("Wpisz dane")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String inputText = etInput.getText().toString();
                        Toast.makeText(getActivity(), "Wpisano: " + inputText, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Anuluj", null);

        return builder.create();
    }
}
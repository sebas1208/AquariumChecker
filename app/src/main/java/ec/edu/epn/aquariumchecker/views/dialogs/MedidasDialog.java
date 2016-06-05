package ec.edu.epn.aquariumchecker.views.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.zip.Inflater;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.views.NuevoAcuario;

/**
 * Created by sebastian on 04/06/16.
 */
public class MedidasDialog extends DialogFragment {
//    public static int alto;
    public int ancho;
    public int profundidad;
    private EditText alto;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.acuarios_medidas_dialog, null));
        View v = inflater.inflate(R.layout.acuarios_medidas_dialog, null);
        alto = (EditText) v.findViewById(R.id.acuario_medida_alto);

        builder.setPositiveButton(R.string.positivo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ((NuevoAcuario)getActivity()).actualizarMedidas(alto.getText().toString());
                        mListener.onDialogPositiveClick(MedidasDialog.this);
                    }
                })
                .setNegativeButton(R.string.negativo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(MedidasDialog.this);
                    }
                });
        return builder.create();
    }

    

    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}

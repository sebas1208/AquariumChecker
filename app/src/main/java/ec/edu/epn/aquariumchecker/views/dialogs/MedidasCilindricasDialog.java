package ec.edu.epn.aquariumchecker.views.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import ec.edu.epn.aquariumchecker.R;

/**
 * Created by sebastian on 05/06/16.
 */
public class MedidasCilindricasDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.acuarios_medidas_cilindricas_dialog, null))
                .setPositiveButton(R.string.positivo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onCilindricoDialogPositiveClick(MedidasCilindricasDialog.this);
                    }
                })
                .setNegativeButton(R.string.negativo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onCilindricoDialogNegativeClick(MedidasCilindricasDialog.this);
                    }
                });
        return builder.create();
    }



    public interface NoticeDialogListener {
        void onCilindricoDialogPositiveClick(DialogFragment dialog);
        void onCilindricoDialogNegativeClick(DialogFragment dialog);
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

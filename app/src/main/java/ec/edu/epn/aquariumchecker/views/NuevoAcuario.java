package ec.edu.epn.aquariumchecker.views;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v4.app.DialogFragment;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasDialog;

public class NuevoAcuario extends AppCompatActivity implements MedidasDialog.NoticeDialogListener{

    private Spinner cmbtiposAgua;
    private Spinner cmbtiposForma;
    private EditText edtMedida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acuarios_activity_nuevo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cmbtiposAgua = (Spinner) findViewById(R.id.cmbTipoAgua);
        cmbtiposForma = (Spinner) findViewById(R.id.cmbForma);
        edtMedida = (EditText) findViewById(R.id.medidas_editText);

        String[] tiposAgua = {"Dulce","Salada"};
        String[] tiposForma = {"Rectangular", "Redondo"};

        ArrayAdapter<String> adaptadorTiposAgua = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposAgua);
        ArrayAdapter <String> adaptadorTiposFormas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,tiposForma);


        cmbtiposAgua.setAdapter(adaptadorTiposAgua);
        cmbtiposForma.setAdapter(adaptadorTiposFormas);
    }


    public void abrirMedidasDialog(View view){
        DialogFragment dialog = new MedidasDialog();
        dialog.show(getSupportFragmentManager(),"String");
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        String a = ((EditText)dialog.getActivity().findViewById(R.id.acuario_medida_alto)).getText().toString();
        edtMedida.setText(a);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}

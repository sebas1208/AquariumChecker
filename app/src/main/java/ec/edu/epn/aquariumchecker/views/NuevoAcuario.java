package ec.edu.epn.aquariumchecker.views;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v4.app.DialogFragment;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasDialog;

public class NuevoAcuario extends AppCompatActivity implements MedidasDialog.NoticeDialogListener {

    private Spinner cmbtiposAgua;
    private Spinner cmbtiposForma;
    private EditText edtMedida;
    private EditText edtVolumen;
    private EditText edtAlto;

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
        edtVolumen = (EditText) findViewById(R.id.volumen_editText);
        edtVolumen.setText("Hola");


        String[] tiposAgua = {"Dulce", "Salada"};
        String[] tiposForma = {"Rectangular", "Redondo"};

        ArrayAdapter<String> adaptadorTiposAgua = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposAgua);
        ArrayAdapter<String> adaptadorTiposFormas = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposForma);


        cmbtiposAgua.setAdapter(adaptadorTiposAgua);
        cmbtiposForma.setAdapter(adaptadorTiposFormas);
    }


    public void abrirMedidasDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.acuarios_medidas_dialog, null));
        builder.setPositiveButton(R.string.positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                edtAlto = (EditText)findViewById(R.id.acuario_medida_alto);
                edtVolumen.setText(edtAlto.getText());
            }
        }).setNegativeButton(R.string.negativo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void actualizarMedidas(String v) {
        edtVolumen.setText(v);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
//        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//        View v = inflater.inflate(R.layout.acuarios_activity_nuevo, null);
//        EditText e = (EditText)v.findViewById(R.id.acuario_medida_alto);
//        Log.v("Edit Text","Hola" + e.getText().toString());
//        String a = e.getText();
//        edtMedida.setText(a);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}

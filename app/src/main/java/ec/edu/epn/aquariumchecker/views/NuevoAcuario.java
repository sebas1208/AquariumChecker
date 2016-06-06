package ec.edu.epn.aquariumchecker.views;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
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
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasDialog;

public class NuevoAcuario extends AppCompatActivity implements MedidasDialog.NoticeDialogListener {

    private EditText nombreAcuario;
    private Spinner cmbtiposAgua;
    private Spinner cmbtiposForma;
    private EditText edtMedida;
    private EditText edtVolumen;
    private Double alto;
    private Double ancho;
    private Double profundidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acuarios_activity_nuevo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombreAcuario = (EditText) findViewById(R.id.txtnombreAcuario);
        cmbtiposAgua = (Spinner) findViewById(R.id.cmbTipoAgua);
        cmbtiposForma = (Spinner) findViewById(R.id.cmbForma);
        edtMedida = (EditText) findViewById(R.id.medidas_editText);
        edtVolumen = (EditText) findViewById(R.id.volumen_editText);


        String[] tiposAgua = {"Dulce", "Salada"};
        String[] tiposForma = {"Rectangular", "Redondo"};

        ArrayAdapter<String> adaptadorTiposAgua = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposAgua);
        ArrayAdapter<String> adaptadorTiposFormas = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposForma);


        cmbtiposAgua.setAdapter(adaptadorTiposAgua);
        cmbtiposForma.setAdapter(adaptadorTiposFormas);
    }

    public void guardarAcuario (View view){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(getApplicationContext());
        SQLiteDatabase db = op.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_NOMBRE,nombreAcuario.getText().toString());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_TIPOAGUA,cmbtiposAgua.getSelectedItem().toString());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_FORMA,cmbtiposForma.getSelectedItem().toString());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ALTO,alto);
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ANCHO,ancho);
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS,profundidad);
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN,Double.parseDouble(edtVolumen.getText().toString()));
        db.insert(AquariumCheckerAppContract.TablaAcuario.NOMBRE_TABLA, null, valores);
        db.close();

    }


    public void abrirMedidasDialog(View view) {
        DialogFragment dialog = new MedidasDialog();
        dialog.show(getSupportFragmentManager(),"String");
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        EditText alto = (EditText)d.findViewById(R.id.acuario_medida_alto);
        EditText ancho = (EditText)d.findViewById(R.id.acuario_medida_ancho);
        EditText profundidad = (EditText)d.findViewById(R.id.acuario_medida_profundidad);
        obtenterMedidasRectangulares(
                alto.getText().toString(),
                ancho.getText().toString(),
                profundidad.getText().toString());
        String medidas = obtenerMedidasRectangularesString(
                alto.getText().toString(),
                ancho.getText().toString(),
                profundidad.getText().toString());
        edtMedida.setText(medidas);
        edtVolumen.setText(obtenerVolumenString());
    }

    private String obtenerMedidasRectangularesString(String alto,String ancho, String profundidad){
        return alto + "x" + ancho + "x" + profundidad;
    }

    private void obtenterMedidasRectangulares(String alto,String ancho, String profundidad){
        this.alto = Double.parseDouble(alto);
        this.ancho = Double.parseDouble(ancho);
        this.profundidad = Double.parseDouble(profundidad);
    }

    private String obtenerVolumenString(){
        return "" + (alto*ancho*profundidad)*0.001;
    };

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}

package ec.edu.epn.aquariumchecker.views;


import android.app.Dialog;
<<<<<<< HEAD
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
=======
>>>>>>> f5dc4e648bdbfc07201c9ae11bbf9e1e60586cca
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v4.app.DialogFragment;

import ec.edu.epn.aquariumchecker.R;
<<<<<<< HEAD
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasDialog;
=======
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasCilindricasDialog;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasRectangularesDialog;
>>>>>>> f5dc4e648bdbfc07201c9ae11bbf9e1e60586cca

public class NuevoAcuario extends AppCompatActivity implements
        MedidasRectangularesDialog.NoticeDialogListener,MedidasCilindricasDialog.NoticeDialogListener {

    private EditText nombreAcuario;
    private Spinner cmbtiposAgua;
    private Spinner cmbtiposForma;
    private EditText edtMedida;
    private EditText edtVolumen;
    private Double alto;
    private Double ancho;
    private Double profundidad;
    private Double profundidadCilindrica;
    private Double diametro;
    private final Double pi = Math.PI;

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
        if(cmbtiposForma.getSelectedItem() == "Rectangular"){
            DialogFragment dialog = new MedidasRectangularesDialog();
            dialog.show(getSupportFragmentManager(),"String");
        }else{
            DialogFragment dialog = new MedidasCilindricasDialog();
            dialog.show(getSupportFragmentManager(),"String");
        }
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
        return alto + "cm x " + ancho + "cm x " + profundidad + "cm";
    }

    private void obtenterMedidasRectangulares(String alto,String ancho, String profundidad){
        this.alto = Double.parseDouble(alto);
        this.ancho = Double.parseDouble(ancho);
        this.profundidad = Double.parseDouble(profundidad);
    }

    private String obtenerVolumenString(){
<<<<<<< HEAD
        return "" + (alto*ancho*profundidad)*0.001;
    };
=======
        return "" + (alto*ancho*profundidad)*0.001 + " Litros";
    }
>>>>>>> f5dc4e648bdbfc07201c9ae11bbf9e1e60586cca

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onCilindricoDialogPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        EditText diametro = (EditText)d.findViewById(R.id.acuario_medidas_cilindricas_diametro);
        EditText profundidad = (EditText)d.findViewById(R.id.acuario_medidas_cilindricas_profundidad);
        obtenterMedidasCilindricas(
                diametro.getText().toString(),
                profundidad.getText().toString());
        String medidas = obtenerMedidasCilindricasString(
                diametro.getText().toString(),
                profundidad.getText().toString());
        edtMedida.setText(medidas);
        edtVolumen.setText(obtenerVolumenCilindricoString());
    }

    private void obtenterMedidasCilindricas(String diametro, String profundidad){
        this.diametro = Double.parseDouble(diametro);
        this.profundidadCilindrica = Double.parseDouble(profundidad);
    }

    private String obtenerMedidasCilindricasString(String diametro, String profundidad){
        return diametro + "cm x " + profundidad + "cm";
    }

    private String obtenerVolumenCilindricoString(){
        return "" + ((diametro/2)*(diametro/2)*pi)*profundidadCilindrica*0.001 + " Litros";
    }

    @Override
    public void onCilindricoonDialogNegativeClick(DialogFragment dialog) {

    }
}

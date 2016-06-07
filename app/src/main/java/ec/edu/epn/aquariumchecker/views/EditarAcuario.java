package ec.edu.epn.aquariumchecker.views;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasCilindricasDialog;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasRectangularesDialog;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;

public class EditarAcuario extends AppCompatActivity implements
        MedidasRectangularesDialog.NoticeDialogListener,MedidasCilindricasDialog.NoticeDialogListener {

    private Spinner cmbtiposAgua;
    private Spinner cmbtiposForma;
    private EditText edtNombre;
    private EditText edtMedida;
    private EditText edtVolumen;
    private final Double pi = Math.PI;
    private AcuarioVO acuarioEditar;

    private String[] tiposAgua = {"Dulce", "Salada"};
    private String[] tiposForma = {"Rectangular", "Cilindrico"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        getAcuarioEdit();
        setEditFields();
    }

    private void initComponents(){
        setContentView(R.layout.acuarios_activity_nuevo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cmbtiposAgua = (Spinner) findViewById(R.id.cmbTipoAgua);
        cmbtiposForma = (Spinner) findViewById(R.id.cmbForma);
        edtNombre = (EditText) findViewById(R.id.txtnombreAcuario);
        edtMedida = (EditText) findViewById(R.id.medidas_editText);
        edtVolumen = (EditText) findViewById(R.id.volumen_editText);

        ArrayAdapter<String> adaptadorTiposAgua = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposAgua);
        ArrayAdapter<String> adaptadorTiposFormas = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposForma);


        cmbtiposAgua.setAdapter(adaptadorTiposAgua);
        cmbtiposForma.setAdapter(adaptadorTiposFormas);
    }

    private void getAcuarioEdit(){
        acuarioEditar = (AcuarioVO)getIntent().getSerializableExtra("acuarioEditar");
        if(acuarioEditar == null){
            acuarioEditar = new AcuarioVO();
        }
    }

    private void setEditFields(){
        edtNombre.setText(acuarioEditar.getNombre());
        if(Objects.equals(acuarioEditar.getTipo_agua(), tiposAgua[0])){
            cmbtiposAgua.setSelection(0);
        }
        else {
            cmbtiposAgua.setSelection(1);
        }

        if(Objects.equals(acuarioEditar.getForma(), tiposForma[0])){
            cmbtiposForma.setSelection(0);
            edtMedida.setText(obtenerMedidasRectangularesString(Double.toString(acuarioEditar.getAlto()),
                    Double.toString(acuarioEditar.getAncho()),Double.toString(acuarioEditar.getProfundidad_rectangular())));
            edtVolumen.setText(obtenerVolumenRectangularString());
        }
        else {
            cmbtiposForma.setSelection(1);
            edtMedida.setText(obtenerMedidasCilindricasString(Double.toString(acuarioEditar.getDiametro()),
                    Double.toString(acuarioEditar.getProfundidad_redondo())));
            edtVolumen.setText(obtenerVolumenCilindricoString(acuarioEditar.getDiametro(),
                    acuarioEditar.getProfundidad_redondo()));
        }
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
        obtenerVolumenRectangular();
        edtMedida.setText(medidas);
        edtVolumen.setText(obtenerVolumenRectangularString());
    }

    private String obtenerMedidasRectangularesString(String alto,String ancho, String profundidad){
        return alto + "cm x " + ancho + "cm x " + profundidad + "cm";
    }

    private void obtenterMedidasRectangulares(String alto,String ancho, String profundidad){
        acuarioEditar.setAlto(Double.parseDouble(alto));
        acuarioEditar.setAncho(Double.parseDouble(ancho));
        acuarioEditar.setProfundidad_rectangular(Double.parseDouble(profundidad));
    }

    private void obtenerVolumenRectangular(){
        acuarioEditar.setVolumen((acuarioEditar.getAlto()*acuarioEditar.getAncho()*
                acuarioEditar.getProfundidad_rectangular())*0.001);
    }

    private void obtenerVolumenCilindrico(){
        acuarioEditar.setVolumen(((acuarioEditar.getDiametro()/2)*(acuarioEditar.getDiametro()/2)*pi)*
                acuarioEditar.getProfundidad_redondo()*0.001);
    }


    private String obtenerVolumenRectangularString(){
        return "" + (acuarioEditar.getAlto()*acuarioEditar.getAncho()*
                acuarioEditar.getProfundidad_rectangular())*0.001 + " Litros";
    }

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
        obtenerVolumenCilindrico();
    }

    private void obtenterMedidasCilindricas(String diametro, String profundidad){
        acuarioEditar.setDiametro(Double.parseDouble(diametro));
        acuarioEditar.setProfundidad_redondo(Double.parseDouble(profundidad));
    }

    private String obtenerMedidasCilindricasString(String diametro, String profundidad){
        return diametro + "cm x " + profundidad + "cm";
    }

    private String obtenerVolumenCilindricoString(){
        return "" + ((acuarioEditar.getDiametro()/2)*(acuarioEditar.getDiametro()/2)*pi)*
                acuarioEditar.getProfundidad_redondo()*0.001 + " Litros";
    }

    private String obtenerVolumenCilindricoString(Double diametro, Double profundidadCilindrica){
        return "" + ((diametro/2)*(diametro/2)*pi)*profundidadCilindrica*0.001 + " Litros";
    }

    @Override
    public void onCilindricoonDialogNegativeClick(DialogFragment dialog) {

    }

    public void guardarAcuario(View view){
        AquariumCheckerAppOpenHelper op = new AquariumCheckerAppOpenHelper(getApplicationContext());
        SQLiteDatabase db = op.getWritableDatabase();
        String[] id = {Integer.toString(acuarioEditar.getId())};

        ContentValues valores = new ContentValues();
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_NOMBRE,edtNombre.getText().toString());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_TIPOAGUA,cmbtiposAgua.getSelectedItem().toString());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_FORMA,cmbtiposForma.getSelectedItem().toString());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ALTO,acuarioEditar.getAlto());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ANCHO,acuarioEditar.getAncho());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS,acuarioEditar.getProfundidad_rectangular());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_DIAMETRO,acuarioEditar.getDiametro());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO,acuarioEditar.getProfundidad_redondo());
        valores.put(AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN,acuarioEditar.getVolumen());
        db.update(AquariumCheckerAppContract.TablaAcuario.NOMBRE_TABLA,valores,
                AquariumCheckerAppContract.TablaAcuario._ID + " = ?",id);
        db.close();
    }

}

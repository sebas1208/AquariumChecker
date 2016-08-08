package ec.edu.epn.aquariumchecker.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasCilindricasDialog;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasRectangularesDialog;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class EditarAcuario extends AppCompatActivity implements
        MedidasRectangularesDialog.NoticeDialogListener,MedidasCilindricasDialog.NoticeDialogListener {

    private MaterialBetterSpinner tipoAguaSpinner;
    private MaterialBetterSpinner formaSpinner;
    private EditText edtNombre;
    private EditText edtMedida;
    private EditText edtVolumen;
    private final Double pi = Math.PI;

    private Acuario acuarioEditar;

    private String[] tiposAgua = {"Dulce", "Salada"};
    private String[] tiposForma = {"Rectangular", "Cilindrico"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        getAcuarioEdit();
        setEditFields();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initComponents();
        getAcuarioEdit();
        setEditFields();
    }

    private void initComponents(){
        setContentView(R.layout.acuarios_activity_nuevo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tipoAguaSpinner = (MaterialBetterSpinner) findViewById(R.id.cmbTipoAgua);
        formaSpinner = (MaterialBetterSpinner) findViewById(R.id.cmbForma);
        edtNombre = (EditText) findViewById(R.id.txtnombreAcuario);
        edtMedida = (EditText) findViewById(R.id.medidas_editText);
        edtVolumen = (EditText) findViewById(R.id.volumen_editText);

        ArrayAdapter<String> adaptadorTiposAgua = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposAgua);
        ArrayAdapter<String> adaptadorTiposFormas = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposForma);


        tipoAguaSpinner.setAdapter(adaptadorTiposAgua);
        formaSpinner.setAdapter(adaptadorTiposFormas);
    }

    private void getAcuarioEdit(){
        acuarioEditar = (Acuario)getIntent().getSerializableExtra("varAcuario");
        if(acuarioEditar == null){
            acuarioEditar = new Acuario();
        }
    }

    private void setEditFields(){
        edtNombre.setText(acuarioEditar.getNombre());
        if(Objects.equals(acuarioEditar.getTipo_agua(), tiposAgua[0])){
            tipoAguaSpinner.setText(acuarioEditar.getTipo_agua());
        }
        else {
            tipoAguaSpinner.setText(acuarioEditar.getTipo_agua());
        }

        if(Objects.equals(acuarioEditar.getForma(), tiposForma[0])){
            formaSpinner.setSelection(0);
            edtMedida.setText(acuarioEditar.obtenerMedidasRectangularesString());
            edtVolumen.setText(acuarioEditar.obtenerVolumenRectangularString());
        }
        else {
            formaSpinner.setText(acuarioEditar.getForma());
            edtMedida.setText(acuarioEditar.obtenerMedidasCilindricasString());
            edtVolumen.setText(acuarioEditar.obtenerVolumenCilindricoString());
        }
    }

    public void abrirMedidasDialog(View view) {
        if(Objects.equals(formaSpinner.getText().toString(), tiposForma[0])){
            DialogFragment dialog = new MedidasRectangularesDialog();
            dialog.show(getSupportFragmentManager(),"String");
        }else{
            DialogFragment dialog = new MedidasCilindricasDialog();
            dialog.show(getSupportFragmentManager(),"String");
        }
    }


    @Override
    public void onDialogRectangularPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        EditText edtAlto = (EditText)d.findViewById(R.id.acuario_medida_alto);
        EditText edtAncho = (EditText)d.findViewById(R.id.acuario_medida_ancho);
        EditText edtProfundidad = (EditText)d.findViewById(R.id.acuario_medida_profundidad);
        setMedidasRectangulares(
                edtAlto.getText().toString(),
                edtAncho.getText().toString(),
                edtProfundidad.getText().toString());
        setVolumenRectangular();
        edtMedida.setText(acuarioEditar.obtenerMedidasRectangularesString());
        edtVolumen.setText(acuarioEditar.obtenerVolumenRectangularString());
    }

    private void setMedidasRectangulares(String alto, String ancho, String profundidad){
        acuarioEditar.setMedidasRectangulares(Double.parseDouble(alto),Double.parseDouble(ancho),
                Double.parseDouble(profundidad));
    }

    private void setVolumenRectangular(){
        acuarioEditar.setVolumenDatosRectangulares();
    }

    private void setVolumenCilindrico(){
        acuarioEditar.setVolumenDatosCilindricos();
    }

    @Override
    public void onCilindricoDialogPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        EditText diametro = (EditText)d.findViewById(R.id.acuario_medidas_cilindricas_diametro);
        EditText profundidad = (EditText)d.findViewById(R.id.acuario_medidas_cilindricas_profundidad);
        obtenterMedidasCilindricas(
                diametro.getText().toString(),
                profundidad.getText().toString());
        setVolumenCilindrico();
        edtMedida.setText(acuarioEditar.obtenerMedidasCilindricasString());
        edtVolumen.setText(acuarioEditar.obtenerVolumenCilindricoString());
    }

    private void obtenterMedidasCilindricas(String diametro, String profundidad){
        acuarioEditar.setDiametro(Double.parseDouble(diametro));
        acuarioEditar.setProfundidad_cilindrica(Double.parseDouble(profundidad));
    }

    public void guardarAcuario(View view){
        acuarioEditar.setNombre(edtNombre.getText().toString());
        acuarioEditar.setTipo_agua(tipoAguaSpinner.getText().toString());
        acuarioEditar.setForma(formaSpinner.getText().toString());

        if(acuarioEditar.camposValidos()){
            AcuarioService service = new AcuarioService();
            service.editarAcuario(acuarioEditar);
            Intent i = new Intent(this, AcuarioDetail.class);
            i.putExtra("varAcuario",acuarioEditar);
            startActivity(i);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"Llene todos los campos", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void cancelar(View view){
        Intent i = new Intent(this, MisAcuarios.class);
        startActivity(i);
    }

    @Override
    public void onDialogRectangularNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onCilindricoDialogNegativeClick(DialogFragment dialog) {

    }

}

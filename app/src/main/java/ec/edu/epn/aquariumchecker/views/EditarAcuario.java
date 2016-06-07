package ec.edu.epn.aquariumchecker.views;

import android.app.Dialog;
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
import ec.edu.epn.aquariumchecker.services.AcuarioService;
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
            edtMedida.setText(acuarioEditar.obtenerMedidasRectangularesString());
            edtVolumen.setText(acuarioEditar.obtenerVolumenRectangularString());
        }
        else {
            cmbtiposForma.setSelection(1);
            edtMedida.setText(acuarioEditar.obtenerMedidasCilindricasString());
            edtVolumen.setText(acuarioEditar.obtenerVolumenCilindricoString());
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
        acuarioEditar.setTipo_agua(cmbtiposAgua.getSelectedItem().toString());
        acuarioEditar.setForma(cmbtiposForma.getSelectedItem().toString());

        AcuarioService service = new AcuarioService(getApplicationContext());
        service.editarAcuario(acuarioEditar);
    }

    @Override
    public void onDialogRectangularNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onCilindricoDialogNegativeClick(DialogFragment dialog) {

    }

}

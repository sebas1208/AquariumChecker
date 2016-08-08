package ec.edu.epn.aquariumchecker.views;

import android.app.Dialog;
import android.content.Intent;
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

import java.util.Objects;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasCilindricasDialog;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasRectangularesDialog;
import ec.edu.epn.aquariumchecker.vo.Acuario;


public class NuevoAcuario extends AppCompatActivity implements
        MedidasRectangularesDialog.NoticeDialogListener, MedidasCilindricasDialog.NoticeDialogListener {

    private MaterialBetterSpinner tipoAguaSpinner;
    private MaterialBetterSpinner tipoFormaSpinner;
    private EditText edtMedida;
    private EditText edtNombre;
    private EditText edtVolumen;
    private Acuario nuevoAcuario = new Acuario();
    String[] tiposAgua = {"Dulce", "Salada"};
    String[] tiposForma = {"Rectangular", "Cilindrico"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
    }

    private void initComponents() {
        setContentView(R.layout.acuarios_activity_nuevo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tipoAguaSpinner = (MaterialBetterSpinner) findViewById(R.id.cmbTipoAgua);
        tipoFormaSpinner = (MaterialBetterSpinner) findViewById(R.id.cmbForma);
        edtMedida = (EditText) findViewById(R.id.medidas_editText);
        edtVolumen = (EditText) findViewById(R.id.volumen_editText);
        edtNombre = (EditText) findViewById(R.id.txtnombreAcuario);


        ArrayAdapter<String> adaptadorTiposAgua = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposAgua);
        ArrayAdapter<String> adaptadorTiposFormas = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposForma);


        tipoAguaSpinner.setAdapter(adaptadorTiposAgua);
        tipoFormaSpinner.setAdapter(adaptadorTiposFormas);
    }


    public void abrirMedidasDialog(View view) {
        Log.i("Tipo Forma", "Hola " + tipoFormaSpinner.getText().toString());
        if (Objects.equals(tipoFormaSpinner.getText().toString(), tiposForma[0])) {
            DialogFragment dialog = new MedidasRectangularesDialog();
            dialog.show(getSupportFragmentManager(), "String");
        } else {
            DialogFragment dialog = new MedidasCilindricasDialog();
            dialog.show(getSupportFragmentManager(), "String");
        }
    }


    @Override
    public void onDialogRectangularPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        EditText edtAlto = (EditText) d.findViewById(R.id.acuario_medida_alto);
        EditText edtAncho = (EditText) d.findViewById(R.id.acuario_medida_ancho);
        EditText edtProfundidad = (EditText) d.findViewById(R.id.acuario_medida_profundidad);

        setMedidasRectangulares(
                edtAlto.getText().toString(),
                edtAncho.getText().toString(),
                edtProfundidad.getText().toString());
        setVolumenRectangular();
        edtMedida.setText(nuevoAcuario.obtenerMedidasRectangularesString());
        edtVolumen.setText(nuevoAcuario.obtenerVolumenRectangularString());
    }


    private void setMedidasRectangulares(String alto, String ancho, String profundidad) {
        nuevoAcuario.setMedidasRectangulares(Double.parseDouble(alto), Double.parseDouble(ancho),
                Double.parseDouble(profundidad));
    }

    private void setVolumenRectangular() {
        nuevoAcuario.setVolumenDatosRectangulares();
    }

    @Override
    public void onCilindricoDialogPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        EditText diametro = (EditText) d.findViewById(R.id.acuario_medidas_cilindricas_diametro);
        EditText profundidad = (EditText) d.findViewById(R.id.acuario_medidas_cilindricas_profundidad);
        setMedidasCilindricas(
                diametro.getText().toString(),
                profundidad.getText().toString());
        setVolumenCilindrico();
        edtMedida.setText(nuevoAcuario.obtenerMedidasCilindricasString());
        edtVolumen.setText(nuevoAcuario.obtenerVolumenCilindricoString());
    }

    private void setVolumenCilindrico() {
        nuevoAcuario.setVolumenDatosCilindricos();
    }


    private void setMedidasCilindricas(String diametro, String profundidad) {
        nuevoAcuario.setMedidasCilindricas(Double.parseDouble(diametro), Double.parseDouble(profundidad));
    }

    public void guardarAcuario(View view) {
        nuevoAcuario.setNombre(edtNombre.getText().toString());
        nuevoAcuario.setTipo_agua(tipoAguaSpinner.getText().toString());
        nuevoAcuario.setForma(tipoFormaSpinner.getText().toString());

        if (nuevoAcuario.camposValidos()) {
            AcuarioService service = new AcuarioService();
            service.crearAcuario(nuevoAcuario);
            Intent i = new Intent(this, AquariumCherckerMain.class);
            startActivity(i);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void cancelar(View view) {
        Intent i = new Intent(this, AquariumCherckerMain.class);
        startActivity(i);
    }

    @Override
    public void onCilindricoDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogRectangularNegativeClick(DialogFragment dialog) {

    }

}

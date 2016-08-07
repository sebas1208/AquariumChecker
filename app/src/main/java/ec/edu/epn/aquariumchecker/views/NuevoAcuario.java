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
import android.widget.Spinner;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasCilindricasDialog;
import ec.edu.epn.aquariumchecker.views.dialogs.MedidasRectangularesDialog;
import ec.edu.epn.aquariumchecker.vo.Acuario;


public class NuevoAcuario extends AppCompatActivity implements
        MedidasRectangularesDialog.NoticeDialogListener,MedidasCilindricasDialog.NoticeDialogListener {

    private Spinner cmbtiposAgua;
    private Spinner cmbtiposForma;
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

    private void initComponents(){
        setContentView(R.layout.acuarios_activity_nuevo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cmbtiposAgua = (Spinner) findViewById(R.id.cmbTipoAgua);
        cmbtiposForma = (Spinner) findViewById(R.id.cmbForma);
        edtMedida = (EditText) findViewById(R.id.medidas_editText);
        edtVolumen = (EditText) findViewById(R.id.volumen_editText);
        edtNombre = (EditText) findViewById(R.id.txtnombreAcuario);


        ArrayAdapter<String> adaptadorTiposAgua = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposAgua);
        ArrayAdapter<String> adaptadorTiposFormas = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tiposForma);


        cmbtiposAgua.setAdapter(adaptadorTiposAgua);
        cmbtiposForma.setAdapter(adaptadorTiposFormas);
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
        edtMedida.setText(nuevoAcuario.obtenerMedidasRectangularesString());
        edtVolumen.setText(nuevoAcuario.obtenerVolumenRectangularString());
    }


    private void setMedidasRectangulares(String alto, String ancho, String profundidad){
        nuevoAcuario.setMedidasRectangulares(Double.parseDouble(alto),Double.parseDouble(ancho),
                Double.parseDouble(profundidad));
    }

    private void setVolumenRectangular(){
        nuevoAcuario.setVolumenDatosRectangulares();
    }

    @Override
    public void onCilindricoDialogPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        EditText diametro = (EditText)d.findViewById(R.id.acuario_medidas_cilindricas_diametro);
        EditText profundidad = (EditText)d.findViewById(R.id.acuario_medidas_cilindricas_profundidad);
        setMedidasCilindricas(
                diametro.getText().toString(),
                profundidad.getText().toString());
        setVolumenCilindrico();
        edtMedida.setText(nuevoAcuario.obtenerMedidasCilindricasString());
        edtVolumen.setText(nuevoAcuario.obtenerVolumenCilindricoString());
    }

    private void setVolumenCilindrico(){
        nuevoAcuario.setVolumenDatosCilindricos();
    }


    private void setMedidasCilindricas(String diametro, String profundidad){
        nuevoAcuario.setMedidasCilindricas(Double.parseDouble(diametro),Double.parseDouble(profundidad));
    }

    public void guardarAcuario (View view){
        nuevoAcuario.setNombre(edtNombre.getText().toString());
        nuevoAcuario.setTipo_agua(cmbtiposAgua.getSelectedItem().toString());
        nuevoAcuario.setForma(cmbtiposForma.getSelectedItem().toString());

        if(nuevoAcuario.camposValidos()){
            CrearAcuario crearAcuario = new CrearAcuario();
            crearAcuario.execute(nuevoAcuario);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"Llene todos los campos", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public class CrearAcuario extends AsyncTask<Acuario, Void, Void> {
        @Override
        protected Void doInBackground(Acuario... params) {
            Log.v("buscar", "2");
            Acuario acuario = params [0];
            Acuario acuario1 = new Acuario();
            /*libros.add(new Libro(1,"Libro1","autor1",10));
            libros.add(new Libro(2,"Libro2","autor2",10));
            libros.add(new Libro(3,"Libro3","autor3",10));*/
            final String url = "http://192.168.135.1:8080/AcuariosRest/acuario/"+""+acuario;
            Log.v("buscar", "3");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Acuario acuario2 = restTemplate.postForObject(url,acuario, Acuario.class);
            acuario1=acuario2;
            Log.v("buscar", "4");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(NuevoAcuario.this, MisAcuarios.class);
            startActivity(i);
        }
    }

        public void cancelar(View view){
        Intent i = new Intent(this, MisAcuarios.class);
        startActivity(i);
    }

    @Override
    public void onCilindricoDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogRectangularNegativeClick(DialogFragment dialog) {

    }

}

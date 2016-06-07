package ec.edu.epn.aquariumchecker.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.services.FotoService;
import ec.edu.epn.aquariumchecker.services.GaleriaService;
import ec.edu.epn.aquariumchecker.services.HistorialService;
import ec.edu.epn.aquariumchecker.vo.*;
import ec.edu.epn.aquariumchecker.vo.Historiales;


public class Historial_Ciclado extends AppCompatActivity implements View.OnClickListener {

    private Spinner cmbCo2;
    private Button btnCalendario;
    private int anio,mes,dia,hora,minuto;
    private Spinner cmbIluminacion;
    private TextView txtFecha,txtHora;
    private EditText txtph,txtgh,txtkh,txtobs;

    private AcuarioVO acuarioSeleccionado;
    private ec.edu.epn.aquariumchecker.vo.Historiales nuevoHistorial = new ec.edu.epn.aquariumchecker.vo.Historiales();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_ciclado);
        btnCalendario = (Button) findViewById(R.id.btnCalendario);
        txtFecha = (TextView) findViewById(R.id.txtFechaView);
        txtph =(EditText) findViewById(R.id.txtph);
        txtgh =(EditText) findViewById(R.id.txtghact);
        txtkh =(EditText) findViewById(R.id.txtkh);
        txtobs=(EditText) findViewById(R.id.txtobservaciones);
        cmbCo2 = (Spinner)findViewById(R.id.cmbCo2);
        cmbIluminacion = (Spinner)findViewById(R.id.cmbIluminacion);

        //combo de co2
        String[]co2= {"Si","No"};
        ArrayAdapter<String> adaptadorCo2 =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        co2);
        cmbCo2.setAdapter(adaptadorCo2);
        btnCalendario.setOnClickListener(this);


        //combo de iluminacion
        String[]iluminacion= {"Alta","Media","Baja"};
        ArrayAdapter<String> adaptadorIluminacion =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        iluminacion);

        cmbIluminacion.setAdapter(adaptadorIluminacion);
        obtenerAcuarioSeleccionado();

    }
    private void obtenerAcuarioSeleccionado(){
        acuarioSeleccionado = (AcuarioVO)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(acuarioSeleccionado == null){
            acuarioSeleccionado = new AcuarioVO();
        }
    }
    public void onClick(View v) {

        if (v == btnCalendario) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            anio = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            txtFecha.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, anio, mes, dia);
            datePickerDialog.show();
        }
    }

    public void guardarHistorial(View v){
        nuevoHistorial.setFecha(txtFecha.getText().toString());
        nuevoHistorial.setPh(txtph.getText().toString());
        nuevoHistorial.setGh(txtgh.getText().toString());
        nuevoHistorial.setKh(txtkh.getText().toString());
        nuevoHistorial.setCo2(cmbCo2.getSelectedItem().toString());
        nuevoHistorial.setIluminacion(cmbIluminacion.getSelectedItem().toString());
        nuevoHistorial.setObservaciones(txtobs.getText().toString());

        nuevoHistorial.setAcuario(String.valueOf(acuarioSeleccionado.getId()));

        HistorialService historialService = new HistorialService(getApplicationContext());
        historialService.crearHistorial(nuevoHistorial);
        Toast.makeText(Historial_Ciclado.this, "Datos almacenados",Toast.LENGTH_LONG).show();
    }

    public void cancelar(View view){
        Intent i = new Intent(this, ListHistorial.class);
        startActivity(i);
    }

}

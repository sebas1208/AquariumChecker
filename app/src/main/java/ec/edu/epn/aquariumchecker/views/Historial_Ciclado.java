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

import java.util.Calendar;
import java.util.Date;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.HistorialService;
import ec.edu.epn.aquariumchecker.vo.*;


public class Historial_Ciclado extends AppCompatActivity implements View.OnClickListener {

    private Spinner cmbCo2;
    private Button btnCalendario;
    private Acuario acuario;
    private int anio, mes, dia, hora, minuto;
    private Spinner cmbIluminacion;
    private TextView txtFecha, txtHora;
    private EditText txtph, txtgh, txtkh, txtobs;

    private Acuario acuarioSeleccionado;
    private ec.edu.epn.aquariumchecker.vo.Historiales nuevoHistorial = new ec.edu.epn.aquariumchecker.vo.Historiales();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerAcuarioSeleccionado();
    }

    private void initComponents(){
        setContentView(R.layout.activity_historial_ciclado);
        btnCalendario = (Button) findViewById(R.id.btnCalendario);
        txtFecha = (TextView) findViewById(R.id.txtFechaView);
        txtph = (EditText) findViewById(R.id.txtph);
        txtgh = (EditText) findViewById(R.id.txtghact);
        txtkh = (EditText) findViewById(R.id.txtkh);
        txtobs = (EditText) findViewById(R.id.txtobservaciones);
        cmbCo2 = (Spinner) findViewById(R.id.cmbCo2);
        cmbIluminacion = (Spinner) findViewById(R.id.cmbIluminacion);

        //combo de co2
        String[] co2 = {"Si", "No"};
        ArrayAdapter<String> adaptadorCo2 =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        co2);
        cmbCo2.setAdapter(adaptadorCo2);
        btnCalendario.setOnClickListener(this);


        //combo de iluminacion
        String[] iluminacion = {"Alta", "Media", "Baja"};
        ArrayAdapter<String> adaptadorIluminacion =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        iluminacion);

        cmbIluminacion.setAdapter(adaptadorIluminacion);
    }

    private void obtenerAcuarioSeleccionado() {
        acuarioSeleccionado = (Acuario) getIntent().getSerializableExtra("acuarioSeleccionado");
        if (acuarioSeleccionado == null) {
            acuarioSeleccionado = new Acuario();
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

    public void guardarHistorial(View v) {
        nuevoHistorial.setFecha(new Date());
        nuevoHistorial.setPh(Integer.valueOf(txtph.getText().toString()));
        nuevoHistorial.setGh(Integer.valueOf(txtgh.getText().toString()));
        nuevoHistorial.setKh(Integer.valueOf(txtkh.getText().toString()));
        nuevoHistorial.setCo2(cmbCo2.getSelectedItem().toString());
        nuevoHistorial.setIluminacion(cmbIluminacion.getSelectedItem().toString());
        nuevoHistorial.setObservaciones(txtobs.getText().toString());

        nuevoHistorial.setIdAcuario(acuarioSeleccionado.getId());

        HistorialService service = new HistorialService();
        service.createHistorial(nuevoHistorial);
        //  HistorialService historialService = new HistorialService(getApplicationContext());
        // historialService.crearHistorial(nuevoHistorial);

        Intent i = new Intent(this, ListHistorial.class);
        startActivity(i);
    }

    public void cancelar(View view) {
        Intent i = new Intent(this, ListHistorial.class);
        startActivity(i);
    }

}

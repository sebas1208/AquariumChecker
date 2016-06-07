package ec.edu.epn.aquariumchecker.views;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.HistorialService;
import ec.edu.epn.aquariumchecker.services.RecordatorioService;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;

import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Forma;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;
import ec.edu.epn.aquariumchecker.R;

public class Recordatorios extends AppCompatActivity implements View.OnClickListener {
    private TextView txtFecha,txtHora;
    private Spinner cmbTipo;
    private Button  btnCalendario;
    private Button  btnHoras;
    private int anio,mes,dia,hora,minuto;
    private AcuarioVO acuarioSeleccionado;
    private ec.edu.epn.aquariumchecker.vo.Recordatorio nuevoRecordatorio = new ec.edu.epn.aquariumchecker.vo.Recordatorio();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //VIEWS
        cmbTipo = (Spinner)findViewById(R.id.cmbTipo);
        btnCalendario = (Button) findViewById(R.id.btnCalendario);
        btnHoras = (Button) findViewById(R.id.btnhora);
        txtFecha = (TextView) findViewById(R.id.txtFechaView);
        txtHora =(TextView) findViewById(R.id.txtHoraView);
        //CALENDARIO
        final Calendar cal = Calendar.getInstance();
        anio = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);



        String[]Tipo= {"Cambio de agua","Abonado","Agregar Plantas","Agregar Peces" };
        ArrayAdapter<String> adaptadorTipo =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Tipo);

        cmbTipo.setAdapter(adaptadorTipo);


        btnHoras.setOnClickListener(this);
        btnCalendario.setOnClickListener(this);
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
        if (v == btnHoras) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minuto = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                            txtHora.setText(hourOfDay + ":" + minute);
                        }
                    }, hora, minuto, false);
            timePickerDialog.show();
        }
    }

    public void guardarRecordatorio(View v){
        nuevoRecordatorio.setFecha(txtFecha.getText().toString());
        nuevoRecordatorio.setHora(txtHora.getText().toString());
        nuevoRecordatorio.setTipoCambio(cmbTipo.getSelectedItem().toString());

        nuevoRecordatorio.setAcuario(String.valueOf(acuarioSeleccionado.getId()));

        RecordatorioService recordatorioService = new RecordatorioService(getApplicationContext());
        recordatorioService.crearRecordatorio(nuevoRecordatorio);
        Toast.makeText(Recordatorios.this, "Datos almacenados",Toast.LENGTH_LONG).show();

    }

    public void cancelar(View view){
        Intent i = new Intent(this, ListRecordatorios.class);
        startActivity(i);
    }



}

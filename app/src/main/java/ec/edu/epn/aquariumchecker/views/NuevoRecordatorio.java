package ec.edu.epn.aquariumchecker.views;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.RecordatorioService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class NuevoRecordatorio extends AppCompatActivity{

    private TextView recordatorioResumen;
    private MaterialBetterSpinner cmbTipo;
    private Acuario acuario;
    private String[] tipoRecordatorio = {"Cambio de agua","Abonado","Agregar Plantas","Agregar Peces" };
    private ec.edu.epn.aquariumchecker.vo.Recordatorio nuevoRecordatorio = new ec.edu.epn.aquariumchecker.vo.Recordatorio();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cmbTipo = (MaterialBetterSpinner)findViewById(R.id.cmbTipo);
        recordatorioResumen = (TextView) findViewById(R.id.resumen_recordatorio_text_view);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, tipoRecordatorio);

        cmbTipo.setAdapter(adaptador);
        obtenerAcuarioSeleccionado();
    }

    private void obtenerAcuarioSeleccionado(){
        acuario = (Acuario)getIntent().getSerializableExtra("varAcuario");
    }

    public void abrirCalendario(View v){
        final Calendar c = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Date fecha = new Date(year, monthOfYear, dayOfMonth);
                        recordatorioResumen.setText(new SimpleDateFormat("dd/M/yyyy").format(fecha));
                        nuevoRecordatorio.setFecha(fecha);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void abrirHora(View v){
        final Calendar c = Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                        Date hora = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),hourOfDay,minute);
                        recordatorioResumen.setText(new SimpleDateFormat("hh:mm").format(hora));
                        nuevoRecordatorio.setHora(hora);
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    public void guardarRecordatorio(View v){
        nuevoRecordatorio.setTipoRecordatorio(cmbTipo.getText().toString());

        nuevoRecordatorio.setIdAcuario(String.valueOf(acuario.getId()));

        RecordatorioService service = new RecordatorioService();
        service.createRecordatorio(nuevoRecordatorio);
        Toast.makeText(getApplicationContext(), "Recordatorio Creado",Toast.LENGTH_LONG).show();

        Intent misRecoradatorios = new Intent(this, MisRecordatorios.class);
        misRecoradatorios.putExtra("varAcuario", acuario);
        startActivity(misRecoradatorios);

        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_HOUR, nuevoRecordatorio.getHora().getHours());
        i.putExtra(AlarmClock.EXTRA_MINUTES, nuevoRecordatorio.getHora().getMinutes());
        startActivity(i);
    }

    public void cancelar(View view){
        Intent i = new Intent(this, MisRecordatorios.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }
}

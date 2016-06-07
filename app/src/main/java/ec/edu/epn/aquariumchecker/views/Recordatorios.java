package ec.edu.epn.aquariumchecker.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
=======
>>>>>>> 6792d7ab91295e1ac15db52576a56b7aaf3a2ca5
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Forma;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;
=======
import ec.edu.epn.aquariumchecker.R;
>>>>>>> 6792d7ab91295e1ac15db52576a56b7aaf3a2ca5


public class Recordatorios extends AppCompatActivity {
    private Spinner cmbAcuarios;

    private Spinner cmbTipo;
    private Button  btnCalendario;
    private Button  btnHoras;
    private int anio,mes,dia,hora,minuto;
    static final int di_log = 0;
    static final int di_log1 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        cmbAcuarios = (Spinner)findViewById(R.id.cmbAcuario);
        cmbTipo = (Spinner)findViewById(R.id.cmbTipo);


        final Calendar cal = Calendar.getInstance();
        anio = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);


        String[]Acuarios= {"Acuario 1","Acuario 2"};
        ArrayAdapter<String> adaptadorAcuarios =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        Acuarios);

        cmbAcuarios.setAdapter(adaptadorAcuarios);

        String[]Tipo= {"Cambio de agua","Abonado","Agregar Plantas","Agregar Peces" };
        ArrayAdapter<String> adaptadorTipo =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        Tipo);

        cmbTipo.setAdapter(adaptadorTipo);

        showDialogOnButtonClick();
        showTimePickerDialog();

    }

    public void showDialogOnButtonClick(){
        btnCalendario = (Button)findViewById(R.id.btnCalendario);

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(di_log);
            }
        });
    }

    public void showTimePickerDialog(){
        btnHoras = (Button)findViewById(R.id.btnhora);

        btnHoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(di_log1);
            }
        });
    }

    protected Dialog onCreateDialog(int id,int id2){
        if(id == di_log)
            return new DatePickerDialog(this,dpickerListener, anio,mes,dia);
        if(id2 == di_log1)
            return new TimePickerDialog(Recordatorios.this,kTimepickerListener, hora,minuto,false);
        return null;

    }

    protected TimePickerDialog.OnTimeSetListener kTimepickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora= hourOfDay;
                minuto = minute;
            Toast.makeText(Recordatorios.this, hora+":"+minuto,Toast.LENGTH_LONG).show();
        }
    };
    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                anio = year;
                mes = monthOfYear+1;
                dia = dayOfMonth;
            Toast.makeText(Recordatorios.this, anio+ "/" + mes+"/"+dia,Toast.LENGTH_LONG).show();
        }

    };



}

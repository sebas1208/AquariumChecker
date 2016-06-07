package ec.edu.epn.aquariumchecker.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;

import ec.edu.epn.aquariumchecker.vo.Forma;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;
import ec.edu.epn.aquariumchecker.R;


public class Recordatorios extends AppCompatActivity implements View.OnClickListener {
    private Spinner cmbAcuarios;
    private TextView txtFecha,txtHora;
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
        btnCalendario = (Button) findViewById(R.id.btnCalendario);
        btnHoras = (Button) findViewById(R.id.btnhora);
        txtFecha = (TextView) findViewById(R.id.txtFechaView);
        txtHora =(TextView) findViewById(R.id.txtHoraView);

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


        btnHoras.setOnClickListener(this);
        btnCalendario.setOnClickListener(this);
      //  showDialogOnButtonClick();
      //  showTimePickerDialog();

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
    /*
    public void showDialogOnButtonClick(){
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(di_log);
            }
        });
    }

    public void showTimePickerDialog(){
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
*/


}

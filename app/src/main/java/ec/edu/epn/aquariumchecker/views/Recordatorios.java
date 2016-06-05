package ec.edu.epn.aquariumchecker.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Forma;


public class Recordatorios extends AppCompatActivity {
    private Spinner cmbAcuarios;
    private Spinner cmbHora;
    private Spinner cmbTipo;
    private Button  btnCalendario;
    private int anio,mes,dia;
    static final int di_log = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        cmbAcuarios = (Spinner)findViewById(R.id.cmbAcuario);
        cmbHora = (Spinner)findViewById(R.id.cmbHora);
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

        String[]Hora= {"1","2","3","4","5","6","7","8","9","10","12","13","14","15","16","17","18","19","20","21","22","23","24"};
        ArrayAdapter<String> adaptadorHora =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        Hora);

        cmbHora.setAdapter(adaptadorHora);

        String[]Tipo= {"Cambio de agua","Abonado","Agregar Plantas","Agregar Peces" };
        ArrayAdapter<String> adaptadorTipo =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        Tipo);

        cmbTipo.setAdapter(adaptadorTipo);
        showDialogOnButtonClick();

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

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == di_log)
            return new DatePickerDialog(this,dpickerListener, anio,mes,dia);
            return null;

    }
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

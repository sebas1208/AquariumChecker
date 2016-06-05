package ec.edu.epn.aquariumchecker.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Forma;


public class Recordatorios extends AppCompatActivity {
    private Spinner cmbAcuarios;
    private Spinner cmbHora;
    private Spinner cmbTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cmbAcuarios = (Spinner)findViewById(R.id.cmbAcuario);
        cmbHora = (Spinner)findViewById(R.id.cmbHora);
        cmbTipo = (Spinner)findViewById(R.id.cmbTipo);

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


    }
}

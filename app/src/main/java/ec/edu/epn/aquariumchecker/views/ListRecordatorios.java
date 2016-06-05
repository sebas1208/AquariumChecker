package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;

import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Forma;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;

public class ListRecordatorios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recordatorios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Recordatorio> recordatorios = new ArrayList<>();
        recordatorios.add(new Recordatorio("Acuario 1","2016-02-02",1,"Abonado"));
        RecordatoriosAdapter adapter = new RecordatoriosAdapter(this,recordatorios);
        ListView misRecordatorios = (ListView)findViewById(R.id.recordatorios_list);
        misRecordatorios.setAdapter(adapter);
    }

    public void abrirNuevoRecordatorio(View view){
        Intent i = new Intent(this, Recordatorios.class);
        startActivity(i);
    }

    public void abrirRecordatorio(View v){
        Intent i = new Intent(this, Recordatorios.class);
        startActivity(i);
    }

}

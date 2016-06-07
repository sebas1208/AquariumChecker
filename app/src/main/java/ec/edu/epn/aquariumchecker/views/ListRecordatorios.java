package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;

import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.services.RecordatorioService;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;

public class ListRecordatorios extends AppCompatActivity {



    ListView misRecordatorios;

    List<Recordatorio> recordatorios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recordatorios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recordatorios.add(new Recordatorio("acuarios 1","2016-02-02","1","Abonado"));
        RecordatoriosAdapter adapter = new RecordatoriosAdapter(this,recordatorios);
        misRecordatorios = (ListView)findViewById(R.id.recordatorios_list);
        misRecordatorios.setAdapter(adapter);

        RecordatorioService service = new RecordatorioService(getApplicationContext());
        recordatorios.addAll(service.listRecordatorios());

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

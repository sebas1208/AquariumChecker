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
import ec.edu.epn.aquariumchecker.adapters.HistorialAdapter;
import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.vo.Historiales;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;

public class ListHistorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_historial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Historiales> historial = new ArrayList<>();
        historial.add(new Historiales("Acuario 1","2016-02-02","7","2","12","Si","Media","Sin cambio de agua"));
        HistorialAdapter adapter = new HistorialAdapter(this,historial);
        ListView historiales = (ListView)findViewById(R.id.historial_list);
        historiales.setAdapter(adapter);
    }

    public void abrirNuevoHistorial(View view){
        Intent i = new Intent(this, Historial_Ciclado.class);
        startActivity(i);
    }

    public void abrirHistorial(View v){
        Intent i = new Intent(this, Historial_Ciclado.class);
        startActivity(i);
    }

}

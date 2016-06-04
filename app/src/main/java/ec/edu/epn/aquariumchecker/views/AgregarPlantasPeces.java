package ec.edu.epn.aquariumchecker.views;

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
import ec.edu.epn.aquariumchecker.adapters.PlantasAdapter;
import ec.edu.epn.aquariumchecker.vo.Planta;

public class AgregarPlantasPeces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_plantas_peces);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List<Planta> plantas = new ArrayList<>();
        plantas.add(new Planta("Planta 1","Esta es la descripcion para una planta",2,"www.google.com"));
        plantas.add(new Planta("Planta 2","Esta es la descripcion para una planta",2,"www.google.com"));
        PlantasAdapter adapter = new PlantasAdapter(this,plantas);
        ListView list = (ListView) findViewById(R.id.mis_plantas_list);
        list.setAdapter(adapter);
    }

}

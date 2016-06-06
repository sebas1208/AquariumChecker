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
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.adapters.PecesAdapter;
import ec.edu.epn.aquariumchecker.vo.Peces;

public class MisPeces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peces_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Peces> peces = new ArrayList<>();
        peces.add(new Peces("Pez 1", "Pes feo", 2, "Foto"));
        PecesAdapter adapter = new PecesAdapter(this,peces);
        ListView misAcuarios = (ListView)findViewById(R.id.mis_peces_list);
        misAcuarios.setAdapter(adapter);
    }

}

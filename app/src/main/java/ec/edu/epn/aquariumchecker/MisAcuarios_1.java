package ec.edu.epn.aquariumchecker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Forma;

public class MisAcuarios_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_acuarios_1);
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

        List<Acuario> acuarios = new ArrayList<>();
        acuarios.add(new Acuario("Acuario 1", "Agua Salada", new Forma("Rectangular",2.0),2.0));
        acuarios.add(new Acuario("Acuario 2", "Agua Dulce", new Forma("Rectangular",2.0),2.0));
        MisAcuariosAdapter adapter = new MisAcuariosAdapter(this,acuarios);
        ListView misAcuarios = (ListView)findViewById(R.id.mis_acuarios_list);
        misAcuarios.setAdapter(adapter);
    }

}

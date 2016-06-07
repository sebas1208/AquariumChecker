package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.HistorialAdapter;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Historiales;
import ec.edu.epn.aquariumchecker.vo.Recordatorio;

public class ListHistorial extends AppCompatActivity {

    private ListView misAcuarios;
    private List<AcuarioVO> acuarios = new ArrayList<>();
    List<Historiales> historial = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acuarios_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MisAcuariosAdapter adapter = new MisAcuariosAdapter(this, acuarios);
        misAcuarios = (ListView) findViewById(R.id.mis_acuarios_list);
        misAcuarios.setAdapter(adapter);

        AcuarioService service = new AcuarioService(getApplicationContext());
        acuarios.addAll(service.listAcuarios());
    }

    public void abrirAcuario(View v) {
        int position = misAcuarios.getPositionForView((LinearLayout)v.getParent());
        Intent i = new Intent(this, ec.edu.epn.aquariumchecker.views.Historiales.class);
        i.putExtra("acuarioSeleccionado",acuarios.get(position));
        startActivity(i);
    }

    public void abrirNuevoAcuario(View view) {
        Intent i = new Intent(this, NuevoAcuario.class);
        startActivity(i);
    }

}

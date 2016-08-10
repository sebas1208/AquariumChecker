package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.HistorialAdapter;
import ec.edu.epn.aquariumchecker.services.HistorialService;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Historiales;

public class ListHistorial extends AppCompatActivity {

    private ListView listaHistorial;
    private List<Historiales> historial = new ArrayList<>();
    private HistorialAdapter adapter;
    private Acuario acuario;
    private List<Acuario> acuarios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_historial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* MisAcuariosAdapter adapter = new MisAcuariosAdapter(this, acuarios);
        misAcuarios = (ListView) findViewById(R.id.mis_acuarios_list);
        misAcuarios.setAdapter(adapter);*/

//        AcuarioService service = new AcuarioService(getApplicationContext());
//        //service.listAcuarios(acuarios,adapter);
    }

    /*public void abrirAcuario(View v) {
        int position = misAcuarios.getPositionForView((LinearLayout)v.getParent());
        Intent i = new Intent(this, ec.edu.epn.aquariumchecker.views.Historiales.class);
        i.putExtra("acuarioSeleccionado",acuarios.get(position));
        startActivity(i);
    }

    public void abrirNuevoAcuario(View view) {
        Intent i = new Intent(this, NuevoAcuario.class);
        startActivity(i);
    }*/

    private void obtenerAcuarioSeleccionado(){
        acuario = (Acuario)getIntent().getSerializableExtra("varAcuario");
    }

    private void obtenerListaHistoriales(){
        HistorialService historialesService = new HistorialService();
        historialesService.listHistorialesPorAcuario(acuario, historial, adapter);
    }

    public void abrirNuevoHistorial(View v) {
        Intent i = new Intent(this, ec.edu.epn.aquariumchecker.views.Historiales.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    public void eliminar(View view){
        int position = listaHistorial.getPositionForView((LinearLayout) view.getParent());
        HistorialService historialesService = new HistorialService();
        historialesService.removeHistorial(historial.get(position));
        historial.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Se elimino el historial", Toast.LENGTH_SHORT).show();
    }

}

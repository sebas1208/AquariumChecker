package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.PlantasAdapter;
import ec.edu.epn.aquariumchecker.services.PlantasService;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Planta;

public class MisPlantas extends AppCompatActivity {

    private ListView listaplantas;
    private List<Planta> plantas = new ArrayList<>();
    private PlantasAdapter adapter;

    private Acuario accuarioSeleccionado;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerAcuarioSeleccionado();
        obtenerListaPlantas();
    }

    private void initComponents(){
        setContentView(R.layout.plantas_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new PlantasAdapter(this, plantas);
        listaplantas = (ListView) findViewById(R.id.mis_plantas_list);
        listaplantas.setAdapter(adapter);
    }

    private void obtenerAcuarioSeleccionado(){
        accuarioSeleccionado = (Acuario)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(accuarioSeleccionado == null){
            accuarioSeleccionado = new Acuario();
        }
    }

    private void obtenerListaPlantas(){
        PlantasService plantasService = new PlantasService(getApplicationContext());
        plantas.addAll(plantasService.listaPlantasPorAcuario(accuarioSeleccionado));
    }


    public void abrirPlantas(View v) {
        Intent i = new Intent(this, NuevoPlanta.class);
        i.putExtra("acuarioSeleccionado", accuarioSeleccionado);
        startActivity(i);
    }

    public void eliminar(View view){
        int position = listaplantas.getPositionForView((LinearLayout)view.getParent());
        PlantasService service = new PlantasService(getApplicationContext());
        service.removePlantas(plantas.get(position));
        plantas.remove(position);
        adapter.notifyDataSetChanged();
    }

}

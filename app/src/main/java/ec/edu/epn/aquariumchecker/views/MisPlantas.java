package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.adapters.PecesAdapter;
import ec.edu.epn.aquariumchecker.adapters.PlantasAdapter;
import ec.edu.epn.aquariumchecker.services.PecesService;
import ec.edu.epn.aquariumchecker.services.PlantasService;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Forma;
import ec.edu.epn.aquariumchecker.vo.Peces;
import ec.edu.epn.aquariumchecker.vo.Planta;

public class MisPlantas extends AppCompatActivity {

    private ListView listaplantas;
    private List<Planta> plantas = new ArrayList<>();
    private PlantasAdapter adapter;

    private AcuarioVO accuarioSeleccionado;


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
        accuarioSeleccionado = (AcuarioVO)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(accuarioSeleccionado == null){
            accuarioSeleccionado = new AcuarioVO();
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

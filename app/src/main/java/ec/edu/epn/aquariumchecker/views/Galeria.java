package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.GaleriaRvAdapter;
import ec.edu.epn.aquariumchecker.services.GaleriaService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class Galeria extends AppCompatActivity {
    private List<ec.edu.epn.aquariumchecker.vo.Galeria> galeriasList = new ArrayList<>();
    private Acuario acuario;
    private RecyclerView galeriaRecyclerView;
    private GaleriaRvAdapter adapter;
    static final int NUEVA_GALERIA_REQUEST = 1;
    static final int MOSTRAR_GALERIA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerAcuarioSeleccionado();
        obtenerGaleriasPorAcuario();
    }

    @Override
    protected void onRestart() {
        super.onRestart();initComponents();
        obtenerAcuarioSeleccionado();
        obtenerGaleriasPorAcuario();
    }

    private void initComponents(){
        setContentView(R.layout.galeria_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        galeriaRecyclerView = (RecyclerView) findViewById(R.id.galeria_recycler_view);
        galeriaRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new GaleriaRvAdapter(galeriasList);
        galeriaRecyclerView.setAdapter(adapter);
    }

    private void obtenerAcuarioSeleccionado(){
        acuario = (Acuario)getIntent().getSerializableExtra("varAcuario");
    }

    private void obtenerGaleriasPorAcuario(){
        GaleriaService service = new GaleriaService();
        service.listGaleriasPorAcuario(acuario,galeriasList,adapter);
    }

    public void abrirNuevaGaleria(View view){
        Intent i = new Intent(this,NuevaGaleria.class);
        i.putExtra("varAcuario", acuario);
        startActivityForResult(i,NUEVA_GALERIA_REQUEST);
    }

    public void abrirGaleriaDetail(View view){
        int position = galeriaRecyclerView.getChildPosition((LinearLayout)view.getParent());
        Intent i = new Intent(this, GaleriaDetail.class);
        i.putExtra("varGaleria", galeriasList.get(position));
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }
}

package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.GaleriaAdapter;
import ec.edu.epn.aquariumchecker.services.GaleriaService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class Galeria extends AppCompatActivity {
    private List<ec.edu.epn.aquariumchecker.vo.Galeria> galeriasList = new ArrayList<>();
    private Acuario acuario;
    private RecyclerView galeriaRecyclerView;
    private GaleriaAdapter adapter;
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
        adapter = new GaleriaAdapter(getApplicationContext(),galeriasList);
        //galeriasListView.setAdapter(adapter);
    }

    private void obtenerAcuarioSeleccionado(){
        acuario = (Acuario)getIntent().getSerializableExtra("varAcuario");
        if(acuario == null){
            acuario = new Acuario();
        }
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

//    public void abrirFotos(View view){
//        int position = galeriasListView.getPositionForView((LinearLayout)view.getParent());
//        Intent i = new Intent(this, Fotos.class);
//        i.putExtra("varGaleria", galeriasList.get(position));
//        startActivityForResult(i, MOSTRAR_GALERIA_REQUEST);
//    }
//
//    public void eliminarGaleria(View view){
//        int position = galeriasListView.getPositionForView((LinearLayout)view.getParent());
//        GaleriaService galeriaService = new GaleriaService();
//        galeriaService.removeGaleria(galeriasList.get(position));
//
//        adapter.notifyDataSetChanged();
//        Toast.makeText(getApplicationContext(),"Se elimino la Galeria",Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NUEVA_GALERIA_REQUEST) {
            if (resultCode == RESULT_OK) {
                obtenerAcuarioSeleccionado();
                obtenerGaleriasPorAcuario();
            }
        }
    }
}

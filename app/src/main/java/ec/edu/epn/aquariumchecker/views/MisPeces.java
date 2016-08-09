package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.PecesAdapter;
import ec.edu.epn.aquariumchecker.services.PecesService;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Peces;

public class MisPeces extends AppCompatActivity {

    private ListView listapeces;
    private List<Peces> peces = new ArrayList<>();
    private PecesAdapter adapter;
    private Acuario accuarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerAcuarioSeleccionado();
        obtenerListaPeces();
    }

    private void initComponents(){
        setContentView(R.layout.peces_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new PecesAdapter(this, peces);
        listapeces = (ListView) findViewById(R.id.mis_peces_list);
        listapeces.setAdapter(adapter);
    }

    private void obtenerAcuarioSeleccionado(){
        accuarioSeleccionado = (Acuario)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(accuarioSeleccionado == null){
            accuarioSeleccionado = new Acuario();
        }
    }

    private void obtenerListaPeces(){
        PecesService pecesService = new PecesService();
        pecesService.listaPecesPorAcuario(accuarioSeleccionado, peces, adapter);
    }

    public void abrirPez(View v) {
        Intent i = new Intent(this, NuevoPez.class);
        i.putExtra("acuarioSeleccionado", accuarioSeleccionado);
        startActivity(i);
    }

    public void eliminar(View view){
        PecesService pecesService = new PecesService();
        pecesService.eliminarPeces(accuarioSeleccionado);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Se elimino el pez", Toast.LENGTH_SHORT).show();
    }

}

package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
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
import ec.edu.epn.aquariumchecker.adapters.GaleriaAdapter;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.services.GaleriaService;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;

public class Galeria extends AppCompatActivity {
    private List<ec.edu.epn.aquariumchecker.vo.Galeria> galeriasList = new ArrayList<>();
    private AcuarioVO acuarioSeleccionado;
    private ListView galerias;
    static final int NUEVA_GALERIA_REQUEST = 1;

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

        GaleriaAdapter adapter = new GaleriaAdapter(this, galeriasList);
        galerias = (ListView) findViewById(R.id.galeria_list);
        galerias.setAdapter(adapter);
    }

    private void obtenerAcuarioSeleccionado(){
        acuarioSeleccionado = (AcuarioVO)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(acuarioSeleccionado == null){
            acuarioSeleccionado = new AcuarioVO();
        }
    }

    private void obtenerGaleriasPorAcuario(){
        GaleriaService service = new GaleriaService(getApplicationContext());
        galeriasList.addAll(service.listGaleriasPorAcuario(acuarioSeleccionado));
    }

    public void abrirNuevaGaleria(View view){
        Intent i = new Intent(this,NuevaGaleria.class);
        i.putExtra("acuarioSeleccionado",acuarioSeleccionado);
        startActivityForResult(i,NUEVA_GALERIA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NUEVA_GALERIA_REQUEST) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

}

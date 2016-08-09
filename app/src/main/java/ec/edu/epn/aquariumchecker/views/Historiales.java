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
import ec.edu.epn.aquariumchecker.adapters.HistorialAdapter;
import ec.edu.epn.aquariumchecker.services.HistorialService;
import ec.edu.epn.aquariumchecker.vo.*;

public class Historiales extends AppCompatActivity {
        private List<ec.edu.epn.aquariumchecker.vo.Historiales> historialeslist = new ArrayList<>();
        private Acuario acuarioSeleccionado;

        private ListView historiales;
        private HistorialAdapter adapter;
        static final int NUEVO_HISTORIAL_REQUEST = 1;
        static final int MOSTRAR_HISTORIAL_REQUEST = 1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            initComponents();
            obtenerAcuarioSeleccionado();
            obtenerHistorialesPorAcuario();
        }

        @Override
        protected void onRestart() {
            super.onRestart();initComponents();
            obtenerAcuarioSeleccionado();
            obtenerHistorialesPorAcuario();
        }

    private void initComponents(){
        setContentView(R.layout.activity_historiales);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        HistorialAdapter adapter = new HistorialAdapter(this, historialeslist);
        historiales = (ListView) findViewById(R.id.historial_list);
        historiales.setAdapter(adapter);
    }

    private void obtenerAcuarioSeleccionado(){
        acuarioSeleccionado = (Acuario)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(acuarioSeleccionado == null){
            acuarioSeleccionado = new Acuario();
        }
    }

    private void obtenerHistorialesPorAcuario(){

        HistorialService service = new HistorialService();
        service.listHistorialesPorAcuario(acuarioSeleccionado,historialeslist,adapter);
    }

    public void abrirNuevoHistorial(View view){
        Intent i = new Intent(this,Historial_Ciclado.class);
        i.putExtra("acuarioSeleccionado",acuarioSeleccionado);
        startActivityForResult(i,NUEVO_HISTORIAL_REQUEST);
    }

    public void abrirHistorial(View view){
        int position = historiales.getPositionForView((LinearLayout)view.getParent());
        Intent i = new Intent(this, Historial_Ciclado.class);
       // i.putExtra("galeriaSeleccionada",historiales.get(position));
        startActivityForResult(i, MOSTRAR_HISTORIAL_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NUEVO_HISTORIAL_REQUEST) {
            if (resultCode == RESULT_OK) {
                obtenerAcuarioSeleccionado();
                obtenerHistorialesPorAcuario();
            }
        }
    }


}

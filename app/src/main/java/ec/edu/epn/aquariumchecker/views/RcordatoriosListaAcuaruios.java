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
import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.services.HistorialService;
import ec.edu.epn.aquariumchecker.services.RecordatorioService;
import ec.edu.epn.aquariumchecker.vo.*;

public class RcordatoriosListaAcuaruios extends AppCompatActivity {
        private List<ec.edu.epn.aquariumchecker.vo.Recordatorio> recordatorioslist = new ArrayList<>();
        private AcuarioVO acuarioSeleccionado;
        private ListView recordatorios;
        static final int NUEVO_RECORDATORIO_REQUEST = 1;
        static final int MOSTRAR_RECORDATORIO_REQUEST = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            initComponents();
            obtenerAcuarioSeleccionado();
            obtenerRecordatorioPorAcuario();
        }

        @Override
        protected void onRestart() {
            super.onRestart();initComponents();
            obtenerAcuarioSeleccionado();
            obtenerRecordatorioPorAcuario();
        }

    private void initComponents(){
        setContentView(R.layout.activity_rcordatorios_lista_acuaruios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecordatoriosAdapter adapter = new RecordatoriosAdapter(this, recordatorioslist);
        recordatorios = (ListView) findViewById(R.id.recordatorios_list);
        recordatorios.setAdapter(adapter);
    }

    private void obtenerAcuarioSeleccionado(){
        acuarioSeleccionado = (AcuarioVO)getIntent().getSerializableExtra("acuarioSeleccionado");
        if(acuarioSeleccionado == null){
            acuarioSeleccionado = new AcuarioVO();
        }
    }

    private void obtenerRecordatorioPorAcuario(){
        RecordatorioService service = new RecordatorioService(getApplicationContext());
        recordatorioslist.addAll(service.listRecordatorios(acuarioSeleccionado));
    }

    public void abrirNuevoRecordatorio(View view){
        Intent i = new Intent(this,Recordatorios.class);
        i.putExtra("acuarioSeleccionado",acuarioSeleccionado);
        startActivityForResult(i,NUEVO_RECORDATORIO_REQUEST);
    }

    public void abrirRecordatorio(View view){
        int position = recordatorios.getPositionForView((LinearLayout)view.getParent());
        Intent i = new Intent(this, Recordatorios.class);
        // i.putExtra("galeriaSeleccionada",historiales.get(position));
        startActivityForResult(i, MOSTRAR_RECORDATORIO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NUEVO_RECORDATORIO_REQUEST) {
            if (resultCode == RESULT_OK) {
                obtenerAcuarioSeleccionado();
                obtenerRecordatorioPorAcuario();
            }
        }
    }

    }



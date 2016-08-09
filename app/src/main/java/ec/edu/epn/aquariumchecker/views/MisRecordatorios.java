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
import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.services.RecordatorioService;
import ec.edu.epn.aquariumchecker.vo.*;

public class MisRecordatorios extends AppCompatActivity {
    private List<ec.edu.epn.aquariumchecker.vo.Recordatorio> recordatorioslist = new ArrayList<>();
    private Acuario acuario;

    private ListView recordatorios;
    static final int NUEVO_RECORDATORIO_REQUEST = 1;
    static final int MOSTRAR_RECORDATORIO_REQUEST = 1;
    private RecordatoriosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerAcuarioSeleccionado();
        obtenerRecordatorioPorAcuario();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initComponents();
        obtenerAcuarioSeleccionado();
        obtenerRecordatorioPorAcuario();
    }

    private void initComponents() {
        setContentView(R.layout.activity_rcordatorios_lista_acuaruios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new RecordatoriosAdapter(this, recordatorioslist);
        recordatorios = (ListView) findViewById(R.id.recordatorios_list);
        recordatorios.setAdapter(adapter);
        recordatorioslist.clear();
    }

    private void obtenerAcuarioSeleccionado() {
        acuario = (Acuario) getIntent().getSerializableExtra("varAcuario");
    }

    private void obtenerRecordatorioPorAcuario() {
        RecordatorioService service = new RecordatorioService();
        service.listRecordatoriosPorAcuario(acuario, recordatorioslist, adapter);
    }

    public void eliminar(View view) {
        int position = recordatorios.getPositionForView((LinearLayout) view.getParent());
        RecordatorioService service = new RecordatorioService();
        service.removeRecordatorio(recordatorioslist.get(position));
        recordatorioslist.remove(position);
        adapter.notifyDataSetChanged();
    }


    public void abrirNuevoRecordatorio(View view) {
        Intent i = new Intent(this, NuevoRecordatorio.class);
        i.putExtra("varAcuario", acuario);
        startActivityForResult(i, NUEVO_RECORDATORIO_REQUEST);
    }

    public void abrirRecordatorio(View view) {
        int position = recordatorios.getPositionForView((LinearLayout) view.getParent());
        Intent i = new Intent(this, NuevoRecordatorio.class);
        // i.putExtra("galeriaSeleccionada",historiales.get(position));
        startActivityForResult(i, MOSTRAR_RECORDATORIO_REQUEST);
    }
}



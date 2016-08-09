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
import ec.edu.epn.aquariumchecker.adapters.RecordatoriosAdapter;
import ec.edu.epn.aquariumchecker.services.RecordatorioService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class ListRecordatorios extends AppCompatActivity {
    private ListView listaRecordatorio;
    private List<ec.edu.epn.aquariumchecker.vo.Recordatorio> recordatorio = new ArrayList<>();
    private RecordatoriosAdapter adapter;
    private Acuario acuario;

    private List<Acuario> acuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recordatorios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*MisAcuariosAdapter adapter = new MisAcuariosAdapter(this, acuarios);
        misAcuarios = (ListView) findViewById(R.id.mis_acuarios_list);
        misAcuarios.setAdapter(adapter);*/

//        AcuarioService service = new AcuarioService(getApplicationContext());
//        //acuarios.addAll(service.listAcuarios());
    }

   /* public void abrirAcuario(View v) {
        int position = misAcuarios.getPositionForView((LinearLayout)v.getParent());
        Intent i = new Intent(this, ec.edu.epn.aquariumchecker.views.RcordatoriosListaAcuaruios.class);
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
        RecordatorioService recordatoriosService = new RecordatorioService();
        recordatoriosService.listRecordatoriosPorAcuario(acuario, recordatorio, adapter);
    }

    public void abrirNuevoRecordatorio(View v) {
        Intent i = new Intent(this, Recordatorios.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    public void eliminar(View view){
        int position = listaRecordatorio.getPositionForView((LinearLayout) view.getParent());
        RecordatorioService recordatoriossService = new RecordatorioService();
        recordatoriossService.removeRecordatorio(recordatorio.get(position));
        recordatorio.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Se elimino el recordatorio", Toast.LENGTH_SHORT).show();
    }

}

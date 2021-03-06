package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.AcuariosRvAdapter;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class AquariumCherckerMain extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView acuarioRecyvlerView;
    private List<Acuario> acuarios;
    private AcuariosRvAdapter adapter;
    private static final int NUEVO_ACUARIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        acuarioRecyvlerView = (RecyclerView) findViewById(R.id.acuario_recycler_view);
        acuarioRecyvlerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        AcuarioService service = new AcuarioService(getApplicationContext());
        acuarios = new ArrayList<>();
        adapter = new AcuariosRvAdapter(acuarios);
        acuarioRecyvlerView.setAdapter(adapter);
        service.listAcuarios(acuarios,adapter);
    }


    public void abrirAcuario(View v) {
        int position = acuarioRecyvlerView.getChildPosition((LinearLayout)v.getParent());
        Intent i = new Intent(this, AcuarioDetail.class);
        i.putExtra("varAcuario",acuarios.get(position));
        startActivity(i);
    }


    public void abrirNuevoAcuario(View view){
        Intent i = new Intent(this, NuevoAcuario.class);
        startActivity(i);
    }

    public void abrirAyuda (View view){
        Intent i = new Intent (this,Ayuda.class);
        startActivity(i);
    }

    @Override
    public void onRefresh() {
        AcuarioService service = new AcuarioService(getApplicationContext());
        service.listAcuarios(acuarios,adapter);
    }
}

package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.AcuariosRvAdapter;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class AquariumCherckerMain extends AppCompatActivity                                                     {

    private RecyclerView acuarioRecyvlerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        acuarioRecyvlerView = (RecyclerView) findViewById(R.id.acuario_recycler_view);
        acuarioRecyvlerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        List<Acuario> acuarios = new ArrayList<>();
        acuarios.add(new Acuario("Acuario_1","tipo"));

        acuarioRecyvlerView.setAdapter(new AcuariosRvAdapter(acuarios));

    }


    public void abrirGaleria(View view){
        Intent i = new Intent(this, GaleriaAcuario.class);
        startActivity(i);
    }

    public void abrirMisAcuarios(View view){
        Intent i = new Intent(this, MisAcuarios.class);
        startActivity(i);
    }

    public void abrirPeces(View view){
        Intent i = new Intent(this, MisPecesAcuarios.class);
        startActivity(i);
    }

    public void abrirPlantas(View view){
        Intent i = new Intent(this, MisPlantasAcuario.class);
        startActivity(i);
    }


    public void abrirNuevoAcuario(View view){
        Intent i = new Intent(this, NuevoAcuario.class);
        startActivity(i);
    }


    public void abrirRecordatorio (View view){
        Intent i = new Intent (this,ListRecordatorios.class);
        startActivity(i);
    }

    public void abrirHistorial (View view){
        Intent i = new Intent (this,ListHistorial.class);
        startActivity(i);
    }

    public void abrirAyuda (View view){
        Intent i = new Intent (this,Ayuda.class);
        startActivity(i);
    }
}

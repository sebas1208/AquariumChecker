package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ec.edu.epn.aquariumchecker.R;

public class AquariumCherckerMain extends AppCompatActivity                                                     {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
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
        Intent i = new Intent(this, MisPlantas.class);
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

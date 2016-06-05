package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.views.Historial_Ciclado;


public class Historial extends AppCompatActivity {


    private Spinner cmbAcuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        cmbAcuarios = (Spinner)findViewById(R.id.cmbAcuario);


        String[]Acuarios= {"Acuario 1","Acuario 2"};
        ArrayAdapter<String> adaptadorAcuarios =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        Acuarios);

        cmbAcuarios.setAdapter(adaptadorAcuarios);


    }


    public void abrirParametros (View view){
        Intent i = new Intent (this,Historial_Ciclado.class);
        startActivity(i);
    }
}

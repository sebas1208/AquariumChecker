package ec.edu.epn.aquariumchecker.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.vo.Acuario;
import ec.edu.epn.aquariumchecker.vo.Forma;


public class Historial_Ciclado extends AppCompatActivity {

    private Spinner cmbCo2;
    private Spinner cmbIluminacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_ciclado);

        cmbCo2 = (Spinner)findViewById(R.id.cmbCo2);
        cmbIluminacion = (Spinner)findViewById(R.id.cmbIluminacion);

        //combo de co2
        String[]co2= {"Si","No"};
        ArrayAdapter<String> adaptadorCo2 =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        co2);
        cmbCo2.setAdapter(adaptadorCo2);

        //combo de iluminacion
        String[]iluminacion= {"Alta","Media","Baja"};
        ArrayAdapter<String> adaptadorIluminacion =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        iluminacion);

        cmbIluminacion.setAdapter(adaptadorIluminacion);
    }


}

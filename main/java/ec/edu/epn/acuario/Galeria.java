package ec.edu.epn.acuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Galeria extends AppCompatActivity {

    private Spinner cmbAcuariosGaleria;
    private Spinner cmbFechasGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        cmbAcuariosGaleria = (Spinner) findViewById(R.id.cmbAcuarioGaleria);
        cmbFechasGaleria = (Spinner) findViewById(R.id.cmbfechaGaleria);


        String [] acuarios = {"Sra. Benavides", "Sr. Cesto", "Casa", "Trabajo"};
        String [] fechas = {"Fecha de modificacion1", "Fecha de modificacion2", "Fecha de modificacion3", "Fecha de modificacionN"};

        ArrayAdapter <String> adaptadorAcuarios = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,acuarios);
        ArrayAdapter <String> adaptadorFechas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,fechas);

        cmbAcuariosGaleria.setAdapter(adaptadorAcuarios);
        cmbFechasGaleria.setAdapter(adaptadorFechas);

    }

    public void abrir_Fotos(View view){
        Intent i = new Intent(this, Fotos.class);
        startActivity(i);


    }


}

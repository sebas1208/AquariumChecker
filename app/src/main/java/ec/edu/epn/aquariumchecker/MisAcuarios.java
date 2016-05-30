package ec.edu.epn.aquariumchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MisAcuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Mis Acuarios","Entre en mis acuarios");
        setContentView(R.layout.activity_mis_acuarios);

    }

    public void abrir_NuevoAcuario(View view){
        Intent i = new Intent(this , NuevoAcuario.class);
        startActivity(i);
    }

    public void abrir_Galeria ( View view){
        Intent i = new Intent( this, Galeria.class);
        startActivity(i);
    }

    public void abrir_EditarAcuario( View view){
        Intent i = new Intent(this, EditarAcuario.class);
        startActivity(i);
    }

    public void abrir_Resumenes( View view){
        Intent i = new Intent(this, Resumen.class);
        startActivity(i);
    }
}

package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class MisPlantasAcuario extends AppCompatActivity {

    ListView misAcuarios;
    List<Acuario> acuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_plantas_acuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(getApplicationContext());

        SQLiteDatabase db = oh.getReadableDatabase();
        String[] columnas = {AquariumCheckerAppContract.TablaAcuario.COLUMNA_NOMBRE,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_TIPOAGUA,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_FORMA,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_ALTO,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_ANCHO,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_DIAMETRO,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO,
                AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN,
                AquariumCheckerAppContract.TablaAcuario._ID
        };

        Cursor cur = db.query(
                AquariumCheckerAppContract.TablaAcuario.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Acuario acuario = new Acuario(cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_NOMBRE)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_TIPOAGUA)),
                    cur.getString(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_FORMA)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ALTO)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_ANCHO)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_MEDIDAS)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_DIAMETRO)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_PROFUNDIDAD_REDONDO)),
                    cur.getDouble(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario.COLUMNA_VOLUMEN)),
                    cur.getInt(cur.getColumnIndex(AquariumCheckerAppContract.TablaAcuario._ID)));
            acuarios.add(acuario);
        }

        MisAcuariosAdapter adapter = new MisAcuariosAdapter(this, acuarios);
        misAcuarios = (ListView) findViewById(R.id.mis_acuarios_list);
        misAcuarios.setAdapter(adapter);

    }

    public void abrirAcuario(View v) {
        int position = misAcuarios.getPositionForView((LinearLayout)v.getParent());
        Intent i = new Intent(this, MisPlantas.class);
        i.putExtra("acuarioSeleccionado",acuarios.get(position));
        startActivity(i);
    }

    public void abrirNuevoAcuario(View view){
        Intent i = new Intent(this, NuevoAcuario.class);
        startActivity(i);
    }


}

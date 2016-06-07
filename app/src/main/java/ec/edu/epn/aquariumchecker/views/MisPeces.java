package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.MisAcuariosAdapter;
import ec.edu.epn.aquariumchecker.adapters.PecesAdapter;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppContract.TablaPeces;
import ec.edu.epn.aquariumchecker.sqlite.AquariumCheckerAppOpenHelper;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Peces;

public class MisPeces extends AppCompatActivity {

    ListView listapeces;
    List<Peces> peces = new ArrayList<>();

    private AcuarioVO acuarioPeces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peces_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getAcuarioEdit();



        /*List<Peces> peces = new ArrayList<>();
        peces.add(new Peces("Pez 1", "Pes feo", 2, "Foto"));
        PecesAdapter adapter = new PecesAdapter(this,peces);
        ListView misAcuarios = (ListView)findViewById(R.id.mis_peces_list);
        misAcuarios.setAdapter(adapter);*/

        AquariumCheckerAppOpenHelper oh = new AquariumCheckerAppOpenHelper(getApplicationContext());

        SQLiteDatabase db = oh.getReadableDatabase();

        String[] id = {Integer.toString(acuarioPeces.getId())};

        String[] columnas = {TablaPeces.COLUMNA_NOMBRE,
                TablaPeces.COLUMNA_DESCRIPCION,
                TablaPeces.COLUMNA_CANTIDAD,
                TablaPeces.COLUMNA_FOTO,
                TablaPeces._ID,
                TablaPeces.ACUARIO_ID
        };

        Cursor cur = db.query(
                TablaPeces.NOMBRE_TABLA,
                columnas,
                TablaPeces.ACUARIO_ID + " = ?", id,
                null,
                null,
                null
        );

        while (cur.moveToNext()) {
            Peces pez = new Peces(cur.getString(cur.getColumnIndex(TablaPeces.COLUMNA_NOMBRE)),
                    cur.getString(cur.getColumnIndex(TablaPeces.COLUMNA_DESCRIPCION)),
                    cur.getInt(cur.getColumnIndex(TablaPeces.COLUMNA_CANTIDAD)),
                    cur.getString(cur.getColumnIndex(TablaPeces.COLUMNA_FOTO)),
                    cur.getInt(cur.getColumnIndex(TablaPeces._ID)),
                    cur.getInt(cur.getColumnIndex(TablaPeces.ACUARIO_ID)));
            peces.add(pez);
        }

        PecesAdapter adapter = new PecesAdapter(this, peces);
        listapeces = (ListView) findViewById(R.id.mis_peces_list);
        listapeces.setAdapter(adapter);


    }

    private void getAcuarioEdit(){
        acuarioPeces = (AcuarioVO)getIntent().getSerializableExtra("acuarioPeces");
        if(acuarioPeces == null){
            acuarioPeces = new AcuarioVO();
        }
    }

    public void abrirPez(View v) {
        Intent i = new Intent(this, NuevoPez.class);
        startActivity(i);
    }





}

package ec.edu.epn.aquariumchecker.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.services.AcuarioService;
import ec.edu.epn.aquariumchecker.vo.Acuario;

public class AcuarioDetail extends AppCompatActivity {

    private Acuario acuario;
    private Toolbar toolbar;
    private TextView hola;
    private ListView Opciones;
    String[] menuOpciones = {"Galerias", "Plantas", "Peces", "Historiales", "Recordatorio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAcuario();
        initComponents();
    }

    private void getAcuario() {
        acuario = (Acuario) getIntent().getSerializableExtra("varAcuario");
        if (acuario == null) {
            acuario = new Acuario();
        }
    }

    private void initComponents() {
        setContentView(R.layout.activity_acuario_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(acuario.getNombre());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acuario_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_edit:
                editAcuario();
                return true;
            case R.id.action_delete:
                deleteAcuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editAcuario() {
        Intent i = new Intent(this, EditarAcuario.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    private void deleteAcuario() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle(getString(R.string.delete_acuario_title))
                .setMessage(getString(R.string.delete_acuario_detail))
                .setIcon(R.drawable.ic_delete_forever_grey_800_18dp)
                .setPositiveButton(getString(R.string.comfirm), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        AcuarioService service = new AcuarioService();
                        service.eliminarAcuario(acuario);
                        Intent i = new Intent(AcuarioDetail.this, AquariumCherckerMain.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), R.string.delete_acuario_message, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.not_confirm), null)                        //Do nothing on no
                .show();
    }

    public void abrirGalerias(View view) {
        Intent i = new Intent(this, MisGalerias.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    public void abrirPeces(View view) {
        Intent i = new Intent(this, MisPeces.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    public void abrirPlantas(View view) {
        Intent i = new Intent(this, MisPlantas.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    public void abrirRecordatorios(View view) {
        Intent i = new Intent(this, MisRecordatorios.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    public void abrirHistoriales(View view) {
        Intent i = new Intent(this, Historiales.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

}

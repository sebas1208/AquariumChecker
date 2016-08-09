package ec.edu.epn.aquariumchecker.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.FotosAdapter;
import ec.edu.epn.aquariumchecker.services.FotoService;
import ec.edu.epn.aquariumchecker.services.GaleriaService;
import ec.edu.epn.aquariumchecker.vo.*;
import ec.edu.epn.aquariumchecker.vo.Galeria;

public class GaleriaDetail extends AppCompatActivity {

    private ListView listViewFotos;
    private FotosAdapter adapter;
    private List<Foto> fotosList = new ArrayList<>();
    private ec.edu.epn.aquariumchecker.vo.Galeria galeriaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        obtenerGaleriaSeleccionada();
        obtenerListaFotosPorGaleria();
        obtenerBitmapsFotos();
    }

    private void initComponents(){
        setContentView(R.layout.activity_galeria_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewFotos = (ListView) findViewById(R.id.listFotos_fotos);
        adapter = new FotosAdapter(this, fotosList);
        listViewFotos.setAdapter(adapter);
    }

    private void obtenerGaleriaSeleccionada(){
        galeriaSeleccionada = (Galeria) getIntent().getSerializableExtra("varGaleria");
    }

    private void obtenerListaFotosPorGaleria(){
        FotoService fotoService = new FotoService();
        fotoService.listarFotosPorGaleria(galeriaSeleccionada,fotosList, adapter);
    }

    private void obtenerBitmapsFotos(){
        for(Foto foto: fotosList){
            if(foto.getPath()!=null && foto.getPath()!="")
            {
                int targetW = 150;
                int targetH = 150;

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(foto.getPath(), bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;

                Bitmap bitmap = BitmapFactory.decodeFile(foto.getPath(), bmOptions);
                foto.setFoto(bitmap);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void abrirGaleria(View v){
        Acuario acuario = (Acuario) getIntent().getSerializableExtra("varAcuario");
        Intent i = new Intent(this, MisGalerias.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_geleria_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteGaleria();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteGaleria(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle(getString(R.string.delete_acuario_title))
                .setMessage(getString(R.string.delete_acuario_detail))
                .setIcon(R.drawable.ic_delete_forever_grey_800_18dp)
                .setPositiveButton(getString(R.string.comfirm), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        GaleriaService service = new GaleriaService(getApplicationContext());
                        service.removeGaleria(galeriaSeleccionada);
                        Intent i = new Intent(GaleriaDetail.this, MisGalerias.class);
                        i.putExtra("varAcuario", getIntent().getSerializableExtra("varAcuario"));
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), R.string.delete_acuario_message, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.not_confirm), null)                        //Do nothing on no
                .show();
    }
}

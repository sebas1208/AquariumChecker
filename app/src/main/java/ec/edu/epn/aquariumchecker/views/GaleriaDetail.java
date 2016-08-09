package ec.edu.epn.aquariumchecker.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.FotosAdapter;
import ec.edu.epn.aquariumchecker.services.FotoService;
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
        setContentView(R.layout.fotos_activity);
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
        Intent i = new Intent(this, ec.edu.epn.aquariumchecker.views.Galeria.class);
        i.putExtra("varAcuario", acuario);
        startActivity(i);
    }

}

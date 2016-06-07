package ec.edu.epn.aquariumchecker.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.adapters.FotosAdapter;
import ec.edu.epn.aquariumchecker.services.FotoService;
import ec.edu.epn.aquariumchecker.vo.*;
import ec.edu.epn.aquariumchecker.vo.Galeria;

public class Fotos extends AppCompatActivity {

    private ListView listViewFotos;
    private FotosAdapter adapter;
    private List<Foto> fotosList = new ArrayList<>();
    private ec.edu.epn.aquariumchecker.vo.Galeria galeriaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        getGaleriaSeleccionada();
        obtenerListaFotosPorGaleria();
        obtenerBitmapsFotos();
    }

    private void initComponents(){
        setContentView(R.layout.fotos_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewFotos = (ListView) findViewById(R.id.listFotos_fotos);
        adapter = new FotosAdapter(this, fotosList);
        listViewFotos.setAdapter(adapter);
    }

    private void getGaleriaSeleccionada(){
        galeriaSeleccionada = (Galeria) getIntent().getSerializableExtra("galeriaSeleccionada");
        if(galeriaSeleccionada == null){
            galeriaSeleccionada = new Galeria();
        }
    }

    private void obtenerListaFotosPorGaleria(){
        FotoService fotoService = new FotoService(getApplicationContext());
        fotosList.addAll(fotoService.listaFotosPorGaleria(galeriaSeleccionada));
    }

    private void obtenerBitmapsFotos(){
        for(Foto foto: fotosList){
            if(foto.getPath()!=null && foto.getPath()!="")
            {
                int targetW = 150;
                int targetH = 150;

                // Get the dimensions of the bitmap
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(foto.getPath(), bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                // Determine how much to scale down the image
                int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;

                Bitmap bitmap = BitmapFactory.decodeFile(foto.getPath(), bmOptions);
                foto.setFoto(bitmap);
            }
        }
        adapter.notifyDataSetChanged();
    }

}

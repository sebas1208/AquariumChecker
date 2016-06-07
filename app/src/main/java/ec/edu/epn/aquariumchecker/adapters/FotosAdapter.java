package ec.edu.epn.aquariumchecker.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.Foto;

/**
 * Created by sebas on 7/6/2016.
 */
public class FotosAdapter<T> extends ArrayAdapter<T> {
    List<Foto> datos;

    public FotosAdapter(Context context, List<T> objects){
        super(context, 0,objects);

        datos = (List<Foto>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if(item == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.foto_item_list,null);
        }
        ImageView foto = (ImageView) item.findViewById(R.id.foto_acuario);
        int targetW = 150;
        int targetH = 150;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(datos.get(position).getPath(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(datos.get(position).getPath(), bmOptions);
        datos.get(position).setFoto(bitmap);
        foto.setImageBitmap(bitmap);

        return item;
    }
}

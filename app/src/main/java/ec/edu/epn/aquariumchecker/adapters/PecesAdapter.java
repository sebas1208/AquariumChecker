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
import ec.edu.epn.aquariumchecker.vo.Peces;

/**
 * Created by sebastian on 05/06/16.
 */
public class PecesAdapter<T> extends ArrayAdapter<T> {

    List<Peces> datos;

    public PecesAdapter(Context context, List<T> objects) {
        super(context, 0, objects);

        datos = (List<Peces>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.plantas_peces_list_item, null);
        }
        TextView lblNombre = (TextView) item.findViewById(R.id.nombre_peces);
        lblNombre.setText(datos.get(position).getNombre());

        TextView lblCantidad = (TextView) item.findViewById(R.id.cantidad_peces);
        lblCantidad.setText(String.format(getContext().getString(R.string.cantidad_peces_plantas),
                datos.get(position).getCantidad()));

        TextView lblDescripcion = (TextView) item.findViewById(R.id.descripcion_peces);
        lblDescripcion.setText(datos.get(position).getDescripcion());

        ImageView fotoPez = (ImageView) item.findViewById(R.id.foto_pez_list);

        int targetW = 150;
        int targetH = 150;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(datos.get(position).getFotoURL(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(datos.get(position).getFotoURL(), bmOptions);
        fotoPez.setImageBitmap(bitmap);

        return item;
    }
}

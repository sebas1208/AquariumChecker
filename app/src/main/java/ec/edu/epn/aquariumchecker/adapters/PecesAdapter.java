package ec.edu.epn.aquariumchecker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        return item;
    }
}

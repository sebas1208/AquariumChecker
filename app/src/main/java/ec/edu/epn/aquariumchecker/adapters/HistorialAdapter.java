package ec.edu.epn.aquariumchecker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.Historiales;

/**
 * Created by angel on 6/5/2016.
 */
public class HistorialAdapter<T> extends ArrayAdapter<T> {

    List<Historiales> datos;

    public HistorialAdapter(Context context, List<T> objects){
        super(context, 0,objects);

        datos = (List<Historiales>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if(item == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.historial_list_item,null);
        }
        TextView lblNombre = (TextView) item.findViewById(R.id.historiales_nombre_historiales);
        lblNombre.setText(new SimpleDateFormat("yyyy-MM-dd").format(datos.get(position).getFecha()));



        TextView lblDescripcion = (TextView) item.findViewById(R.id.historial_descripcion);
        lblDescripcion.setText("Observacion: "+ datos.get(position).getObservaciones());

        return item;
    }
}

package ec.edu.epn.aquariumchecker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by sebas on 7/6/2016.
 */
public class GaleriaAdapter<T> extends ArrayAdapter<T> {
    List<Galeria> datos;

    public GaleriaAdapter(Context context, List<T> objects){
        super(context, 0,objects);

        datos = (List<Galeria>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if(item == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.galeria_list_item,null);
        }
        TextView fecha = (TextView) item.findViewById(R.id.fecha_galeria);
        String date = new SimpleDateFormat("dd/M/yyyy hh:mm").format(datos.get(position).getFecha());
        fecha.setText(date);

        TextView lblDescripcion = (TextView) item.findViewById(R.id.galeria_observaciones);
        lblDescripcion.setText(datos.get(position).getObservaciones());

        return item;
    }

}

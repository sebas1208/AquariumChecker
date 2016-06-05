package ec.edu.epn.aquariumchecker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ec.edu.epn.aquariumchecker.R;

import ec.edu.epn.aquariumchecker.vo.Recordatorio;

/**
 * Created by angel on 6/5/2016.
 */
public class RecordatoriosAdapter<T> extends ArrayAdapter<T> {

    List<Recordatorio> datos;

    public RecordatoriosAdapter(Context context, List<T> objects){
        super(context, 0,objects);

        datos = (List<Recordatorio>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if(item == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.recordatorio_list_item,null);
        }
        TextView lblNombre = (TextView) item.findViewById(R.id.mis_recordatorios_nombre_recordatorio);
        lblNombre.setText(datos.get(position).getAcuario()+ ",Tipo:  "+datos.get(position).getTipoCambio());



        TextView lblDescripcion = (TextView) item.findViewById(R.id.mis_recordatorios_descripcion);
        lblDescripcion.setText("Fecha: "+datos.get(position).getFecha()+",Hora: "+ datos.get(position).getHora());

        return item;
    }

}

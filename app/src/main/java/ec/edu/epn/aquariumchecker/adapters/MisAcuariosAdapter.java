package ec.edu.epn.aquariumchecker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;


/**
 * Created by sebastian on 28/05/16.
 */
public class MisAcuariosAdapter<T> extends ArrayAdapter<T> {

    List<AcuarioVO> datos;

    public MisAcuariosAdapter(Context context, List<T> objects){
        super(context, 0,objects);

        datos = (List<AcuarioVO>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if(item == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.acuarios_list_item,null);
        }
        TextView lblNombre = (TextView) item.findViewById(R.id.mis_acuarios_nombre_acuario);
        lblNombre.setText(datos.get(position).getNombre());

        TextView lblDescripcion = (TextView) item.findViewById(R.id.mis_acuarios_descripcion);
        lblDescripcion.setText(String.format(getContext().getString(R.string.descripcion_mis_acuarios)
                ,datos.get(position).getTipo_agua(),datos.get(position).getVolumen()));

        return item;
    }
}

package ec.edu.epn.aquariumchecker.adaptador;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.AcuarioVO;

/**
 * Created by natyd on 28/5/2016.
 */
public class AcuarioAdapter extends ArrayAdapter {

    List <AcuarioVO> listaAcuarios;

    public AcuarioAdapter(Context context, List <AcuarioVO> objects) {
        super(context, 0, objects);
        listaAcuarios = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflador = LayoutInflater.from(getContext());
            convertView = inflador.inflate(R.layout.item_agua,null);
        }

        TextView nombreAcuario = (TextView)convertView.findViewById(R.id.lblNombreAcuario);
        TextView tipoAgua = (TextView) convertView.findViewById(R.id.lblTipoAgua);
        TextView volumen = (TextView) convertView.findViewById(R.id.lblVolumen);

        AcuarioVO acuario = (AcuarioVO)listaAcuarios.get(position);
        nombreAcuario.setText(acuario.getNombreAcuario());
        tipoAgua.setText(acuario.getTipoAgua());
        volumen.setText(String.valueOf(acuario.getVolumen()));

        return convertView;
    }
}

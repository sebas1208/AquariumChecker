package ec.edu.epn.aquariumchecker.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by sebastian on 08/08/16.
 */
public class GaleriaRvAdapter extends RecyclerView.Adapter<GaleriaRvAdapter.GaleriaViewHolder> {

    private List<Galeria> datos;

    public GaleriaRvAdapter(List<Galeria> galerias)  {
        this.datos = galerias;
    }

    public static class GaleriaViewHolder extends RecyclerView.ViewHolder {
        TextView galeriaTitle;
        TextView observaciones;
        ImageView galeria_image;

        public GaleriaViewHolder(View itemView) {
            super(itemView);
            galeriaTitle =  (TextView) itemView.findViewById(R.id.galeria_title);
            observaciones =  (TextView) itemView.findViewById(R.id.galeria_observaciones);
            galeria_image = (ImageView) itemView.findViewById(R.id.galeria_image);
        }
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public GaleriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.galeria_list_item, parent, false);
        return new GaleriaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GaleriaViewHolder holder, int position) {
        holder.galeriaTitle.setText(new SimpleDateFormat("dd/M/yyyy hh:mm").format(datos.get(position).getFecha()));
        holder.observaciones.setText(datos.get(position).getObservaciones());
        holder.galeria_image.setImageResource(R.drawable.logo_galeria);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

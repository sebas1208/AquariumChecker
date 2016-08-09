package ec.edu.epn.aquariumchecker.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.Acuario;

/**
 * Created by sebastian on 07/08/16.
 */
public class AcuariosRvAdapter extends RecyclerView.Adapter<AcuariosRvAdapter.AcuarioViewHolder> {
    private List<Acuario> acuarios;

    public AcuariosRvAdapter(List<Acuario> acuarios) {
        this.acuarios = acuarios;
    }

    public static class AcuarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView acuarioTitle;
        TextView acuarioDetail;
        ImageView acuarioImage;

        public AcuarioViewHolder(View itemView) {
            super(itemView);
            acuarioTitle =  (TextView) itemView.findViewById(R.id.acuario_title);
            acuarioDetail =  (TextView) itemView.findViewById(R.id.acuario_detail);
            acuarioImage = (ImageView) itemView.findViewById(R.id.acuario_image);
        }

        @Override
        public void onClick(View view) {
            int position  =   getAdapterPosition();
            Log.w("", "Selected"+position);
        }
    }

    @Override
    public int getItemCount() {
        return acuarios.size();
    }

    @Override
    public AcuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.acuarios_list_item, parent, false);
        AcuarioViewHolder avh = new AcuarioViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(AcuarioViewHolder holder, int position) {
        holder.acuarioTitle.setText(acuarios.get(position).getNombre());
        holder.acuarioDetail.setText(acuarios.get(position).getForma());
        holder.acuarioImage.setImageResource(R.drawable.fondo_pez);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

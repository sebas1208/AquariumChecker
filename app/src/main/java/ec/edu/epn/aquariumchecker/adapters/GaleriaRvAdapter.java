package ec.edu.epn.aquariumchecker.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ec.edu.epn.aquariumchecker.R;
import ec.edu.epn.aquariumchecker.vo.Galeria;

/**
 * Created by sebastian on 08/08/16.
 */
public class GaleriaRvAdapter {

    private List<Galeria> listGaleria;

    public GaleriaRvAdapter(List<Galeria> galerias) {
        this.listGaleria = galerias;
    }

    public static class GaleriaViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView acuarioTitle;
        TextView acuarioDetail;
        ImageView acuarioImage;

        public GaleriaViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.acuario_recycler_view);
            acuarioTitle =  (TextView) itemView.findViewById(R.id.acuario_title);
            acuarioDetail =  (TextView) itemView.findViewById(R.id.acuario_detail);
            acuarioImage = (ImageView) itemView.findViewById(R.id.acuario_image);
        }
    }
}

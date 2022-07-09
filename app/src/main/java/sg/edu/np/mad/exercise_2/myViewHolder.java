package sg.edu.np.mad.exercise_2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {
    public TextView txt;
    public TextView txt2;
    public ImageView img;
    public CardView cardView;

    public myViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        txt = itemView.findViewById(R.id.nameText);
        txt2 = itemView.findViewById(R.id.descText);
        img = itemView.findViewById(R.id.imageView4);
        cardView = itemView.findViewById(R.id.cardView3);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewInterface != null){
                    //get position of itemView to pass in onItemClick
                    int pos = getAdapterPosition();

                    //make sure itemView position is valid
                    if(pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}

package sg.edu.np.mad.exercise_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdaptor extends RecyclerView.Adapter<myViewHolder> {

    ArrayList<User> data;
    private final RecyclerViewInterface recyclerViewInterface;

    public myAdaptor(ArrayList<User> input, RecyclerViewInterface recyclerViewInterface) {
        data = input;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new myViewHolder(item, recyclerViewInterface);
    }

    public void onBindViewHolder(myViewHolder holder, int position){
        User user = data.get(position);
        holder.txt.setText(user.name);
        holder.txt2.setText(user.description);

        if(user.name.endsWith("7")){
            holder.cardView.setVisibility(View.VISIBLE);
        }
        else{
            holder.cardView.setVisibility(View.GONE);
        }
    }
    public int getItemCount(){
        return data.size();
    }


}


package developer.shivam.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by shivam on 30/11/16.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private Context context;
    private List<Data> my_data;

    public CustomAdapter(Context context, List<Data> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.iname.setText(my_data.get(position).getName());
        Log.d("nm",my_data.get(position).getName());
        String priceval=String.valueOf(my_data.get(position).getPrice());
        Log.d("pr", priceval+","+my_data.get(position).getImage_link());
        holder.iprice.setText("Price = Rs. "+priceval+"/-");


        holder.idesc.setText("~> "+my_data.get(position).getDescription());
        holder.iurl.setImageResource(my_data.get(position).getImageId());
        //holder.iurl.setBackgroundResource(my_data.get(position).getImageId());
        //Glide.with(context).load(dm.getImage_link()).centerCrop().into(holder.iurl);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        TextView iname, iprice, idesc;
        ImageView iurl;

        public ViewHolder(View itemView) {
            super(itemView);
            iname = (TextView) itemView.findViewById(R.id.name);
            iprice = (TextView) itemView.findViewById(R.id.price);
            Log.d("abc", "exec");
            idesc = (TextView) itemView.findViewById(R.id.desc);
            iurl = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}

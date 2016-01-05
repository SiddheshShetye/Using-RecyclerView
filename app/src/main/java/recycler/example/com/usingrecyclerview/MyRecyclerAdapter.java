package recycler.example.com.usingrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by siddheshs2 on 04-01-2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{

    private List<String> mList;
    private Context mContext;

    public MyRecyclerAdapter(List<String> list,Context context){
        mList = list;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvText.setText(mList.get(position));
        holder.mposition = position;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvText;
        ImageButton ibtnDel;
        int mposition;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
            ibtnDel = (ImageButton) itemView.findViewById(R.id.ibtnDel);
            ibtnDel.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mList.remove(mposition);
            notifyDataSetChanged();
        }
    }

}

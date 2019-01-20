package coml.bawei.dell.lianxi1.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.bean.RigthBeana;

public class RigthAdpter extends RecyclerView.Adapter<RigthAdpter.ViewHolder> {
    private  List<RigthBeana.DataBean> mlist;
    private Context mContext;

    public RigthAdpter(Context mContext) {
        this.mContext = mContext;
        mlist = new ArrayList<>();
    }
    public void setMlist(List<RigthBeana.DataBean> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.rigth_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
          viewHolder.title.setText(mlist.get(i).getName());
          RigthlitterAdpter rigthlitterAdpter =  new RigthlitterAdpter(mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        rigthlitterAdpter.setmList(mlist.get(i).getList());
        viewHolder.recyclerView.setAdapter(rigthlitterAdpter);
        viewHolder.recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rigth_big_title)
        TextView title;
        @BindView(R.id.right_item_recycle)
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

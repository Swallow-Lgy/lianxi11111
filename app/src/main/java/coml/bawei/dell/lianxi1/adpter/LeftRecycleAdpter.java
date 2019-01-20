package coml.bawei.dell.lianxi1.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.bean.LeftBean;

public class LeftRecycleAdpter extends RecyclerView.Adapter<LeftRecycleAdpter.ViewHolder> {
    private List<LeftBean.DataBean> mList;
    private Context mContext;

    public LeftRecycleAdpter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<LeftBean.DataBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext,R.layout.left_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            viewHolder.title.setText(mList.get(i).getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick!=null){
                        onClick.onClickLisenter(mList.get(i).getCid());
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_item_title)
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    onClick onClick;
    public void setOnClickLisenter(onClick click){
        this.onClick = click;
    }

    public interface onClick{
         void onClickLisenter(int cid);
    }
}

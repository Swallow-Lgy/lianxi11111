package coml.bawei.dell.lianxi1.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.activity.GoodsActivity;
import coml.bawei.dell.lianxi1.bean.MessageBean;
import coml.bawei.dell.lianxi1.bean.RigthBeana;

public class RigthlitterAdpter extends RecyclerView.Adapter<RigthlitterAdpter.ViewHolder> {
    private List<RigthBeana.DataBean.ListBean> mList;
    private Context mContext;

    public RigthlitterAdpter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<RigthBeana.DataBean.ListBean> mList)
    {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.right_xaio_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
           viewHolder.image.setImageURI(mList.get(i).getIcon());
           viewHolder.title.setText(mList.get(i).getName());
           viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  /* Intent intent = new Intent(mContext,GoodsActivity.class);
                   intent.putExtra("cid",mList.get(i).getPscid());*/
                   /*mContext.startActivity(intent);*/
                   EventBus.getDefault().postSticky(new MessageBean("goodsname",mList.get(i).getPcid()));
               }
           });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.xiao_item_image)
        SimpleDraweeView image;
        @BindView(R.id.xaio_item_text)
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}

package coml.bawei.dell.lianxi1.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.activity.DailteActivity;
import coml.bawei.dell.lianxi1.bean.GoodsBean;

public class GoodsAdpter extends RecyclerView.Adapter<GoodsAdpter.ViewHolder> {
    private List<GoodsBean.DataBean> mList;
    private Context mContext;

    public GoodsAdpter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<GoodsBean.DataBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.goods_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
          viewHolder.price.setText(mList.get(i).getPrice()+"");
          viewHolder.name.setText(mList.get(i).getTitle());
          String images = mList.get(i).getImages();
          String[] split = images.split("\\|");
          viewHolder.image.setImageURI(split[0]);
          viewHolder.itemView.setOnClickListener(new View.OnClickListener()
          {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(mContext,DailteActivity.class);
                  intent.putExtra("url",mList.get(i).getDetailUrl());
                  intent.putExtra("id",mList.get(i).getPscid());
                  mContext.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        SimpleDraweeView image;
        @BindView(R.id.goods_price)
        TextView price;
        @BindView(R.id.goods_name)
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

package coml.bawei.dell.lianxi1.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.bean.ShopCarBean;

public class ShopCarGoodsAdpter extends RecyclerView.Adapter<ShopCarGoodsAdpter.ViewHolder> {
    private List<ShopCarBean.DataBean.ListBean> mList;
    private Context mContext;

    public ShopCarGoodsAdpter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<ShopCarBean.DataBean.ListBean> mList)
    {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.shopcar_goods_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.checkBox.setChecked(mList.get(i).isCheck());
        viewHolder.name.setText(mList.get(i).getTitle());
        viewHolder.price.setText(mList.get(i).getPrice()+"");
        String images = mList.get(i).getImages();
        String[] split = images.split("\\|");
        viewHolder.image.setImageURI(split[0]);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mList.get(i).setCheck(isChecked);
                    if (onClick!=null){
                    onClick.OnClickLisenter();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    //如果商家选中商品
    public void selectAllCheck(boolean b)
    {
       for (ShopCarBean.DataBean.ListBean bean:mList){
           bean.setCheck(b);
       }
       notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
         @BindView(R.id.image_item)
        SimpleDraweeView image;
         @BindView(R.id.name_item)
        TextView name;
         @BindView(R.id.check_item)
        CheckBox checkBox;
         @BindView(R.id.price_item)
         TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    onClick onClick;
    public void setOnClickLisenter(onClick onClick){
        this.onClick = onClick;
    }
    public interface onClick{
        void OnClickLisenter();
    }
}

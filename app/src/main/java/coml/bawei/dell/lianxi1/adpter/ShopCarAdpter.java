package coml.bawei.dell.lianxi1.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.bean.GoodsBean;
import coml.bawei.dell.lianxi1.bean.ShopCarBean;

public class ShopCarAdpter extends RecyclerView.Adapter<ShopCarAdpter.ViewHolder> {
    private List<ShopCarBean.DataBean> mList;
    private Context mContext;

    public ShopCarAdpter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<ShopCarBean.DataBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.shopcar_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
         viewHolder.title.setText(mList.get(i).getSellerName());
         viewHolder.checkBox.setChecked(mList.get(i).isCheck());
         final ShopCarGoodsAdpter carGoodsAdpter = new ShopCarGoodsAdpter(mContext);
         carGoodsAdpter.setmList(mList.get(i).getList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        viewHolder.recyclerView.setAdapter(carGoodsAdpter);
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                    mList.get(i).setCheck(viewHolder.checkBox.isChecked());
                   //调用商品的
                    carGoodsAdpter.selectAllCheck(viewHolder.checkBox.isChecked());
            }
        });
        carGoodsAdpter.setOnClickLisenter(new ShopCarGoodsAdpter.onClick() {
            @Override
            public void OnClickLisenter() {
                if (onCheckOncClick!=null){
                    onCheckOncClick.setOnCliclk(mList);
                }
                List<ShopCarBean.DataBean.ListBean> list = mList.get(i).getList();
                boolean selectAll=true;
                for (ShopCarBean.DataBean.ListBean listBean:list){
                    if (!listBean.isCheck()){
                        selectAll=false;
                    }
                    viewHolder.checkBox.setChecked(selectAll);
                    mList.get(i).setCheck(selectAll);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shopcar_check)
        CheckBox checkBox;
        @BindView(R.id.shopcar_title)
        TextView title;
        @BindView(R.id.shopcar_recycle)
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    onCheckOncClick onCheckOncClick;
    public void setOnCheckClickLisnter(onCheckOncClick checkClickLisnter){
        this.onCheckOncClick = checkClickLisnter;
    }
    public interface onCheckOncClick{
        void setOnCliclk(List<ShopCarBean.DataBean> list);
    }
}

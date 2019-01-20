package coml.bawei.dell.lianxi1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.adpter.ShopCarAdpter;
import coml.bawei.dell.lianxi1.bean.ShopCarBean;
import coml.bawei.dell.lianxi1.presenter.IPresenterImpl;
import coml.bawei.dell.lianxi1.view.IView;

public class ShopCarActivity extends AppCompatActivity implements View.OnClickListener,IView {
    @BindView(R.id.shopcarrecycle)
    RecyclerView recyclerView;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.check_all)
    CheckBox checkall;
    private String shopcarUrl="product/getCarts";
    private IPresenterImpl iPresenter;
    private ShopCarAdpter carAdpter;
    private List<ShopCarBean.DataBean> data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);
        iPresenter = new IPresenterImpl(this);
        //加载布局
        initLayout();
    }
   //加载布局
    private void initLayout()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        carAdpter = new ShopCarAdpter(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(carAdpter);
        getData();
        //计算
        total();
    }
     private void getData()
     {
         Map<String,String> map = new HashMap<>();
         map.put("uid",23421+"");
         iPresenter.requestDataPost(shopcarUrl,map,ShopCarBean.class);
     }
     //计算价格
    public void total(){
        carAdpter.setOnCheckClickLisnter(new ShopCarAdpter.onCheckOncClick()
        {
            @Override
            public void setOnCliclk(List<ShopCarBean.DataBean> list) {
                double tatal=0;
                for (int i=0;i<list.size();i++){
                    List<ShopCarBean.DataBean.ListBean> list1 = list.get(i).getList();
                    for (int j=0;j<list1.size();j++){
                       if (list1.get(j).isCheck()){
                           tatal+=list1.get(j).getPrice()*list1.get(j).getNum();
                       }
                    }
                }
                sum.setText(tatal+"");
            }
        });
    }
    //全选框的状态
    @OnClick(R.id.check_all)
    public void checkAllOnClick(){
            selectAll(checkall.isChecked());
            carAdpter.notifyDataSetChanged();
    }
    //全选框的计算
    public void selectAll(boolean b){
            double sumall =0;
           for (int i=0;i<data1.size();i++){
               ShopCarBean.DataBean dataBean = data1.get(i);
               dataBean.setCheck(b);
               List<ShopCarBean.DataBean.ListBean> list = data1.get(i).getList();
             for (int j=0;j<list.size();j++){
                 list.get(j).setCheck(b);
                 sumall = sumall+list.get(j).getNum()*list.get(j).getPrice();
             }
           }
           if (b){
               sum.setText(sumall+"");
           }
           else {
               sum.setText(0+"");
           }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object data) {
        if (data instanceof ShopCarBean){
            ShopCarBean shopCarBean = (ShopCarBean) data;
            data1 = shopCarBean.getData();
            carAdpter.setmList(data1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.des();
    }
}

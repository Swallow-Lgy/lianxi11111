package coml.bawei.dell.lianxi1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.adpter.GoodsAdpter;
import coml.bawei.dell.lianxi1.bean.GoodsBean;
import coml.bawei.dell.lianxi1.bean.MessageBean;
import coml.bawei.dell.lianxi1.presenter.IPresenterImpl;
import coml.bawei.dell.lianxi1.view.IView;

public class GoodsActivity extends AppCompatActivity implements View.OnClickListener,IView {
     @BindView(R.id.goodsrecycle)
     RecyclerView goodsrecycle;
    private int pscid;
    private String searchUrl="product/getProducts";
    private IPresenterImpl iPresenter;
    private GoodsAdpter goodsAdpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        /*Intent intent = getIntent();
        pscid = intent.getIntExtra("cid",0);*/
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        iPresenter = new IPresenterImpl(this);
        Toast.makeText(this,pscid+"",Toast.LENGTH_SHORT).show();
        initLayout();
    }
   public void initLayout(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        goodsrecycle.setLayoutManager(linearLayoutManager);
        goodsAdpter = new GoodsAdpter(this);
        goodsrecycle.setAdapter(goodsAdpter);
        getData();
    }
    public void getData()
    {
        Map<String,String> map = new HashMap<>();
        map.put("pscid",pscid+"");
        iPresenter.requestDataPost(searchUrl,map,GoodsBean.class);
    }

   @Subscribe (threadMode = ThreadMode.MAIN,sticky = true)
  public void getGoodsName(MessageBean messageBean){
       if(messageBean.getFalg().equals("goodsname")){
          pscid = (int) messageBean.getMessage();

       }
   }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object data) {
       if (data instanceof GoodsBean){
           GoodsBean goodsBean = (GoodsBean) data;
           goodsAdpter.setmList(goodsBean.getData());
       }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.des();
        EventBus.getDefault().unregister(this);
    }




}

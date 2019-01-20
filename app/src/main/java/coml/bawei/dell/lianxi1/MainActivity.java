package coml.bawei.dell.lianxi1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import coml.bawei.dell.lianxi1.activity.GoodsActivity;
import coml.bawei.dell.lianxi1.adpter.LeftRecycleAdpter;
import coml.bawei.dell.lianxi1.adpter.RigthAdpter;
import coml.bawei.dell.lianxi1.bean.LeftBean;
import coml.bawei.dell.lianxi1.bean.MessageBean;
import coml.bawei.dell.lianxi1.bean.RigthBeana;
import coml.bawei.dell.lianxi1.presenter.IPresenterImpl;
import coml.bawei.dell.lianxi1.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    @BindView(R.id.leftrecycle)
    RecyclerView leftrecylce;
    @BindView(R.id.rigthrecycle)
    RecyclerView rigthrecycle;
    private IPresenterImpl iPresenter;
    private String urlleft="product/getCatagory";
    private LeftRecycleAdpter leftadpter;
    private RigthAdpter rigthAdpter;
    private String rigthUrl="product/getProductCatagory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       EventBus.getDefault().register(this);
        //绑定
        initPresenter();
        //左边布局加载
        intiLayoyt();
        //左边点击事件
        leftOnClick();

    }
    //左边点击事件
    public void leftOnClick(){
        leftadpter.setOnClickLisenter(new LeftRecycleAdpter.onClick() {
            @Override
            public void onClickLisenter(int cid) {
                getRigthData(cid);
                initRightLayout();
            }
        });
    }
    //加载布局
    public void intiLayoyt()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        leftrecylce.setLayoutManager(linearLayoutManager);
        leftadpter = new LeftRecycleAdpter(this);
        leftrecylce.setAdapter(leftadpter);
        getLeftData();
    }
    //右边加载怒局
    public void initRightLayout(){
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       rigthAdpter = new RigthAdpter(this);
       rigthrecycle.setLayoutManager(linearLayoutManager);
       rigthrecycle.setAdapter(rigthAdpter);

    }
    //请求数据
    public void getLeftData(){
        iPresenter.requestDataGet(urlleft,LeftBean.class);
    }
    //左边请求数据
    public void getRigthData(int cid){
        Map<String,String> map = new HashMap<>();
        map.put("cid",cid+"");
        iPresenter.requestDataPost(rigthUrl,map,RigthBeana.class);
    }

    @Override
    public void onClick(View v) {

    }
   @Subscribe (threadMode = ThreadMode.MAIN)
    public void tiao(MessageBean messageBean){
        if (messageBean.getFalg().equals("goodsname")){
            Intent intent = new Intent(MainActivity.this,GoodsActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void success(Object data) {
         if (data instanceof LeftBean){
             LeftBean leftBean = (LeftBean) data;
             leftadpter.setmList(leftBean.getData());
         }
         if (data instanceof RigthBeana){
             RigthBeana rigthBeana = (RigthBeana) data;
             rigthAdpter.setMlist(rigthBeana.getData());
         }
    }
    //绑定
    public void initPresenter(){
        iPresenter = new IPresenterImpl(this);
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.des();
        EventBus.getDefault().unregister(this);
    }
}

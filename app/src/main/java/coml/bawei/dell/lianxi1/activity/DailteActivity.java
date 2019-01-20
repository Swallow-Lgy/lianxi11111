package coml.bawei.dell.lianxi1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coml.bawei.dell.lianxi1.R;
import coml.bawei.dell.lianxi1.bean.AddCarBean;
import coml.bawei.dell.lianxi1.bean.GoodsBean;
import coml.bawei.dell.lianxi1.presenter.IPresenterImpl;
import coml.bawei.dell.lianxi1.view.IView;
import retrofit2.http.Url;

public class DailteActivity extends AppCompatActivity implements View.OnClickListener,IView {

      @BindView(R.id.web)
      WebView webView;
      @BindView(R.id.addcar)
    Button addcar;
    @BindView(R.id.gocar)
    Button gocar;
    private String url;
    private int id;
    private String carUrl="product/addCart";
    private IPresenterImpl iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailte);
        ButterKnife.bind(this);
        iPresenter = new IPresenterImpl(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        id = intent.getIntExtra("id", 0);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
   @OnClick(R.id.addcar)
   public void setAddcarOnClick(){
       Toast.makeText(DailteActivity.this,"1111",Toast.LENGTH_SHORT).show();
       Map<String,String> map = new HashMap<>();
       map.put("uid",23421+"");
       map.put("pid",id+"");
       iPresenter.requestDataPost(carUrl,map,AddCarBean.class);
   }
   @OnClick(R.id.gocar)
   public void goCarOnClick(){
        Intent intent = new Intent(this,ShopCarActivity.class);
        startActivity(intent);
   }
    @Override
    public void onClick(View v) {


    }

    @Override
    public void success(Object data) {
           if (data instanceof AddCarBean){
               AddCarBean addCarBean = (AddCarBean) data;
               if (addCarBean.getCode().equals("0")){
                   Toast.makeText(DailteActivity.this,addCarBean.getMsg(),Toast.LENGTH_SHORT).show();
               }
           }
    }
}

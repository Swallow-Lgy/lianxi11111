package coml.bawei.dell.lianxi1.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager {
    private static  RetrofitManager mRetrofitManager;
    private BaseApi baseApi;
    private String BASE_URL="http://www.zhaoapi.cn/";
    public static  synchronized  RetrofitManager getIstance(){
        if (mRetrofitManager==null){
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }
    private RetrofitManager(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true);
        builder.writeTimeout(15,TimeUnit.SECONDS);
        builder.readTimeout(15,TimeUnit.SECONDS);
        builder.connectTimeout(15,TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
        baseApi = retrofit.create(BaseApi.class);
    }
    public RetrofitManager post(String url, Map<String,String>map,HttpLisenter lisenter){
        if (map==null){
            map = new HashMap<>();
        }
        baseApi.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(lisenter));
        return mRetrofitManager;
    }

    public RetrofitManager get(String url,HttpLisenter lisenter){

        baseApi.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(lisenter));
        return mRetrofitManager;
    }
    public Observer getObserver(final HttpLisenter lisenter){
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               if (lisenter!=null){
                   lisenter.faile(e.getMessage());
               }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    if (lisenter!=null){
                        lisenter.success(result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (lisenter!=null){
                        lisenter.faile(e.getMessage());
                    }
                }
            }


        };
        return observer;
    }
    public interface  HttpLisenter{
        void success(String data);
        void faile(String error);
    }
}

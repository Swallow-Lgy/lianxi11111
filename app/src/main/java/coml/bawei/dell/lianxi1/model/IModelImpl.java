package coml.bawei.dell.lianxi1.model;

import com.google.gson.Gson;

import java.util.Map;

import coml.bawei.dell.lianxi1.callback.MyCallBack;
import coml.bawei.dell.lianxi1.util.RetrofitManager;

public class IModelImpl implements IModel {
    @Override
    public void requestDataPost(String url, Map<String, String> map, final Class clazz, final MyCallBack callBack) {
        RetrofitManager.getIstance().post(url, map, new RetrofitManager.HttpLisenter() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                Object o = gson.fromJson(data, clazz);
                callBack.setData(o);
            }

            @Override
            public void faile(String error) {
                 callBack.setData(error);
            }
        });
    }

    @Override
    public void requestDataGet(String url, final Class clazz, final MyCallBack callBack) {
           RetrofitManager.getIstance().get(url, new RetrofitManager.HttpLisenter() {
               @Override
               public void success(String data) {
                   Gson gson = new Gson();
                   Object o = gson.fromJson(data, clazz);
                   callBack.setData(o);
               }

               @Override
               public void faile(String error) {
                    callBack.setData(error);
               }
           });
    }
}

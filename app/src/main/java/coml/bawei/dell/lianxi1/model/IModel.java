package coml.bawei.dell.lianxi1.model;

import java.util.Map;

import coml.bawei.dell.lianxi1.callback.MyCallBack;

public interface IModel {
    void requestDataPost(String url, Map<String,String>map,Class clazz,MyCallBack callBack);
    void requestDataGet(String url,Class clazz,MyCallBack callBack);
}

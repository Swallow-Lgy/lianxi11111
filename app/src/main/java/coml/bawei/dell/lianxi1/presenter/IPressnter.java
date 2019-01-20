package coml.bawei.dell.lianxi1.presenter;

import java.util.Map;

public interface IPressnter {
    void requestDataPost(String url, Map<String,String>map,Class clazz);
    void requestDataGet(String url,Class clazz);
}

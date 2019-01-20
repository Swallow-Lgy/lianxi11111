package coml.bawei.dell.lianxi1.presenter;

import java.util.Map;

import coml.bawei.dell.lianxi1.callback.MyCallBack;
import coml.bawei.dell.lianxi1.model.IModelImpl;
import coml.bawei.dell.lianxi1.view.IView;

public class IPresenterImpl implements IPressnter {
    private IView iView;
    private IModelImpl iModel;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }

    @Override
    public void requestDataPost(String url, Map<String, String> map, Class clazz) {
             iModel.requestDataPost(url, map, clazz, new MyCallBack() {
                 @Override
                 public void setData(Object data) {
                     iView.success(data);
                 }
             });
    }

    @Override
    public void requestDataGet(String url, Class clazz) {
         iModel.requestDataGet(url, clazz, new MyCallBack() {
             @Override
             public void setData(Object data) {
                 iView.success(data);
             }
         });
    }
    public void des(){
        if (iModel!=null){
            iModel =null;
        }
        if (iView!=null){
            iView=null;
        }
    }
}

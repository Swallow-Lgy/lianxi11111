package coml.bawei.dell.lianxi1.util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface BaseApi {
    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> map);
    @GET
    Observable<ResponseBody> get(@Url String url);
}

package daiw.com.wanandroiddemo.network;

import daiw.com.wanandroiddemo.model.RequestRegisterEntity;
import daiw.com.wanandroiddemo.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/8/12  下午8:53
 *
 *         ***************************
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    Call<String> sendLoginRequest(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Call<RequestRegisterEntity> sendRegisterRequest(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);

}

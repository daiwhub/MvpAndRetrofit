package daiw.com.wanandroiddemo.model;

import android.text.TextUtils;

import org.json.JSONObject;

import daiw.com.wanandroiddemo.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/8/12  下午8:41
 *
 *         ***************************
 */

public class ModelLogin implements IModelLogin {

    private User user;

    public ModelLogin() {
    }

    public ModelLogin(User user) {
        this.user = user;
    }

    @Override
    public void onLoginCall(final IModelLoginListener listener) {

            //实现登录
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.wanandroid.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);
            Call<String> call = apiService.sendLoginRequest(user.getUserName(),user.getPassWorld());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        String result = response.body();
                        try {
                            if(!TextUtils.isEmpty(result)) {
                                JSONObject object = new JSONObject(result);
                                if(0 == object.getInt("errorCode")){
                                    listener.onLoginResult("onSuccess");
                                    return;
                                }else {
                                    String errorMsg = object.getString("errorMsg");
                                    listener.onLoginResult(errorMsg);
                                    return;
                                }
                            }
                            listener.onLoginResult("onFailure");
                        }catch (Exception e){
                            e.printStackTrace();
                            listener.onLoginResult("onFailure");
                        }
                    }else {
                        listener.onLoginResult("onFailure");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    listener.onLoginResult("onFailure");
                }
            });
    }
}

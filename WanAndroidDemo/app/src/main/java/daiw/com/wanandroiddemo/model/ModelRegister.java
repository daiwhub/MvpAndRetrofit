package daiw.com.wanandroiddemo.model;

import daiw.com.wanandroiddemo.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/8/12  下午9:08
 *
 *         ***************************
 */

public class ModelRegister implements IModelRegister {

    private User user;

    public ModelRegister(User user) {
        this.user = user;
    }

    @Override
    public void toRegisterCall(final IModelRegisterListener listener) {
        //请求网络，注册
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<RequestRegisterEntity> call = apiService.sendRegisterRequest(user.getUserName(),user.getPassWorld(),user.getRepassword());
        call.enqueue(new Callback<RequestRegisterEntity>() {
            @Override
            public void onResponse(Call<RequestRegisterEntity> call, Response<RequestRegisterEntity> response) {
                if (response.isSuccessful()) {
                    RequestRegisterEntity entity = response.body();
                    if (0 == entity.getErrorCode()) {
                        listener.toRegisterResult("onSuccess");
                        return;
                    }else {
                        listener.toRegisterResult(entity.getErrorMsg());
                        return;
                    }
                }
                listener.toRegisterResult("onFailure");
            }

            @Override
            public void onFailure(Call<RequestRegisterEntity> call, Throwable t) {
                listener.toRegisterResult("onFailure");
            }
        });
    }
}

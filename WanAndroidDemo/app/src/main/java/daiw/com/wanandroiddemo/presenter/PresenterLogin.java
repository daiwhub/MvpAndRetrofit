package daiw.com.wanandroiddemo.presenter;

import java.lang.ref.WeakReference;

import daiw.com.wanandroiddemo.model.IModelLogin;
import daiw.com.wanandroiddemo.model.ModelLogin;
import daiw.com.wanandroiddemo.model.User;
import daiw.com.wanandroiddemo.view.IViewLogin;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/8/12  下午8:44
 *
 *         ***************************
 */

public class PresenterLogin<T extends IViewLogin> {

    private WeakReference<T> IView;

    public PresenterLogin(T view){
        this.IView = new WeakReference<T>(view);
    }

    private IModelLogin iModelLogin;

    public void fetch (User user){
        if(iModelLogin == null){
            iModelLogin = new ModelLogin(user);
        }
        iModelLogin.onLoginCall(new IModelLogin.IModelLoginListener() {
            @Override
            public void onLoginResult(String result) {
                if(IView != null){
                    IView.get().toLogin(result);
                }
            }
        });

    }
}

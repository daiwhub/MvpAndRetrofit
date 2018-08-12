package daiw.com.wanandroiddemo.presenter;

import java.lang.ref.WeakReference;

import daiw.com.wanandroiddemo.model.IModelRegister;
import daiw.com.wanandroiddemo.model.ModelRegister;
import daiw.com.wanandroiddemo.model.User;
import daiw.com.wanandroiddemo.view.IViewRegister;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/8/12  下午9:29
 *
 *         ***************************
 */

public class PresenterRegister<T extends IViewRegister> {

    private WeakReference<T> IView;

    public PresenterRegister(T view){
        this.IView = new WeakReference<T>(view);
    }

    private IModelRegister iModelRegister;

    public void fetch(User user){
        if(iModelRegister == null){
            iModelRegister = new ModelRegister(user);
        }
        if(IView != null){
            iModelRegister.toRegisterCall(new IModelRegister.IModelRegisterListener() {
                @Override
                public void toRegisterResult(String result) {
                    IView.get().toRegister(result);
                }
            });
        }
    }

}

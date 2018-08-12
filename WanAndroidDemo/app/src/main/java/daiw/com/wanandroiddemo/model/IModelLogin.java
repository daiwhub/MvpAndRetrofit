package daiw.com.wanandroiddemo.model;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/8/12  下午8:38
 *
 *         ***************************
 */

public interface IModelLogin {

    void onLoginCall(IModelLoginListener lestener);

    public interface IModelLoginListener {
        void onLoginResult(String result);
    }

}

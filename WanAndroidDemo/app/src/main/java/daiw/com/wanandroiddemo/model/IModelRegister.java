package daiw.com.wanandroiddemo.model;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/8/12  下午9:06
 *
 *         ***************************
 */

public interface IModelRegister {

    void toRegisterCall (IModelRegisterListener listener);

    interface IModelRegisterListener {
        void toRegisterResult(String result);
    }
}

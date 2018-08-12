package daiw.com.wanandroiddemo.model;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/8/12  下午8:57
 *
 *         ***************************
 */

public class User {

    private String userName;
    private String passWorld;
    private String repassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWorld() {
        return passWorld;
    }

    public void setPassWorld(String passWorld) {
        this.passWorld = passWorld;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}

package daiw.com.wanandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import daiw.com.wanandroiddemo.model.User;
import daiw.com.wanandroiddemo.presenter.PresenterLogin;
import daiw.com.wanandroiddemo.view.IViewLogin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IViewLogin {

    private EditText username_edt;
    private EditText passworld_edt;
    private Button login_btn;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        username_edt = (EditText) findViewById(R.id.username_edt);
        passworld_edt = (EditText) findViewById(R.id.passworld_edt);
        login_btn = (Button) findViewById(R.id.login_btn);

        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                submit();
                if (user != null) {
                    PresenterLogin presenterLogin = new PresenterLogin(this);
                    presenterLogin.fetch(user);
                }
                break;
        }
    }

    private void submit() {
        // validate
        String userName = username_edt.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String passWorld = passworld_edt.getText().toString().trim();
        if (TextUtils.isEmpty(passWorld)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        user = new User();
        user.setUserName(userName);
        user.setPassWorld(passWorld);
    }

    @Override
    public void toLogin(String result) {
        if (!TextUtils.isEmpty(result)) {
            if ("onSuccess".equals(result)) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }
}

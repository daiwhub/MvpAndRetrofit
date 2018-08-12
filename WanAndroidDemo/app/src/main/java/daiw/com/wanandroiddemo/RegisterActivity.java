package daiw.com.wanandroiddemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import daiw.com.wanandroiddemo.model.User;
import daiw.com.wanandroiddemo.presenter.PresenterRegister;
import daiw.com.wanandroiddemo.view.IViewRegister;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IViewRegister {


    private User user;

    private EditText username_edt;
    private EditText passworl_edt;
    private EditText repassword_edt;
    private Button register_btn;
    private Button skip_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                submit();
                if (user != null) {
                    PresenterRegister presenterRegister = new PresenterRegister(this);
                    presenterRegister.fetch(user);
                }
                break;
            case R.id.skip_btn:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void toRegister(String result) {
        if (!TextUtils.isEmpty(result)) {
            if ("onSuccess".equals(result)) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return;
            }
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        username_edt = (EditText) findViewById(R.id.username_edt);
        passworl_edt = (EditText) findViewById(R.id.passworl_edt);
        repassword_edt = (EditText) findViewById(R.id.repassword_edt);
        register_btn = (Button) findViewById(R.id.register_btn);
        skip_btn = (Button) findViewById(R.id.skip_btn);

        register_btn.setOnClickListener(this);
        skip_btn.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String userName = username_edt.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String passWorld = passworl_edt.getText().toString().trim();
        if (TextUtils.isEmpty(passWorld)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String repassWorld = repassword_edt.getText().toString().trim();
        if (TextUtils.isEmpty(repassWorld)) {
            Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        user = new User();
        user.setUserName(userName);
        user.setPassWorld(passWorld);
        user.setRepassword(repassWorld);
    }
}

package org.easydarwin.easyplayer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.esaydarwin.rtsp.player.R;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;

    Button bt1;
    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登陆");

        et1 = findViewById(R.id.lg_et_lid);
        et2 = findViewById(R.id.lg_et_pwd);

        bt1 = findViewById(R.id.lg_bt_lg);
        bt2 = findViewById(R.id.lg_bt_rg);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lid = et1.getText().toString();
                String pwd = et2.getText().toString();
                Map m = new HashMap();
                m.put("lid", lid);
                m.put("pwd", pwd);

                PostUtil.Login(m, LoginHandler, LoginActivity.this);

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.setClassName( getApplicationContext(), "org.easydarwin.easyplayer.RegistActivity" );
                startActivity( in );

            }
        });
    }


    Handler LoginHandler = new Handler() {
        String alert = "";

        @Override
        public String toString() {
            return alert;
        }

        @Override
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            if (Boolean.FALSE.toString().equals(data.getString("result"))) {
                alert=data.getString("cause");
            }

            String jsonString = data.getString("value");
            try {
                JSONObject json = new JSONObject(jsonString);
                if ("Error".equals(json.getString("excuteResult"))) {
                    alert=json.getString("message");
                } else {
                    LoginUser user = LoginUser.getCurrentUser();
                    user.isLogin = true;
                    user.LoginToken = json.getString("userLoginToken");
                    JSONObject ext = (JSONObject) json.get("extResult");
                    user.Name = ext.getString("Name");
                    user.LID = ext.getString("LID");
                    user.UID = Integer.parseInt(ext.getString("UID"));

                    Intent in = new Intent();
                    in.setClassName( getApplicationContext(), "org.easydarwin.easyplayer.PlaylistActivity" );
                    startActivity( in );
                }
            } catch (Exception ex) {
                alert="返回报文出错:" + ex.getMessage();
            }
        }
    };


}

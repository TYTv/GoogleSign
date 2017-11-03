package felix.com.googlesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import felix.com.googlesign.Account.Info;
import felix.com.googlesign.Account.Sign;

import static felix.com.googlesign.Account.Sign.RC_SIGN_IN;
import static felix.com.googlesign.Account.Sign.gSign;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                super.run();

                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            showInfo();

                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }

    public void showInfo() {

        StringBuilder sb = new StringBuilder();
        sb.append(Info.gId);
        sb.append("\n");
        sb.append(Info.gEmail);
        sb.append("\n");
        sb.append(Info.gDisplayName);
        sb.append("\n");
        sb.append(Info.gGivenName);
        sb.append("\n");
        sb.append(Info.gFamilyName);
        sb.append("\n");
        sb.append(Info.gPhotoUrl);
        sb.append("\n");
        sb.append(Info.gAccount);
        sb.append("\n");
        sb.append(Info.gGrantedScopes);
        sb.append("\n");
        sb.append(Info.gIdToken);
        sb.append("\n");
        sb.append(Info.gServerAuthCode);
        sb.append("\n");

        TextView tv = (TextView) findViewById(R.id.textViewInfo);
        tv.setText(sb);

        ImageView iv = (ImageView) findViewById(R.id.imageViewIcon);
        iv.setImageBitmap(Info.gPhotoUrlBitmap);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_SIGN_IN:   //取得登入資訊
                gSign.result(data);     //更新資訊
                break;
        }

    }

    public void onClickLogIn(View view) {

        if (gSign == null) {
            gSign = new Sign(this);
        }
        gSign.signIn();

    }

    public void onClickLogOut(View view) {

        if (gSign == null) {
            gSign = new Sign(this);
        }
        gSign.signOut();

    }
}

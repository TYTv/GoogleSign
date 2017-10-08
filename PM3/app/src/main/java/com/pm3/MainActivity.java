package com.pm3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class MainActivity extends AppCompatActivity
//        implements GoogleApiClient.OnConnectionFailedListener
//        ,View.OnClickListener
{

    private TextView gName;
    private TextView gInfo;
    private ImageView gIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gName = (TextView) findViewById(R.id.TextViewName);
        gInfo = (TextView) findViewById(R.id.TextViewInfo);
        gIcon = (ImageView) findViewById(R.id.ImageViewIcon);

        new Sign(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Sign.RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            Log.d(Sign.TAG, "SignInResult:" + result.isSuccess());
            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.
                GoogleSignInAccount acct = result.getSignInAccount();

                gName.setText(acct.getDisplayName());
                new GetUrlImg(this, gIcon).execute(acct.getPhotoUrl());


                StringBuilder sb = new StringBuilder();
                sb.append("Email -> " + acct.getEmail());
                sb.append("\n");
                sb.append("DisplayName -> " + acct.getDisplayName());
                sb.append("\n");
                sb.append("GivenName -> " + acct.getGivenName());
                sb.append("\n");
                sb.append("FamilyName -> " + acct.getFamilyName());
                sb.append("\n");
                sb.append("Id -> " + acct.getId());
                sb.append("\n");
                gInfo.setText(sb);


            }

        }

    }


}

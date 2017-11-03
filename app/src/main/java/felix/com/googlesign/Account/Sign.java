package felix.com.googlesign.Account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static felix.com.googlesign.Account.Info.NO_SIGN;

/**
 * Created by Felix on 2017/11/3.
 */

public final class Sign {

    public static Sign gSign = null;

    public static final int RC_SIGN_IN = 578;
    private GoogleApiClient mGoogleApiClient = null;
    private Context ctx = null;

    public Sign(FragmentActivity fact) {

        this.ctx = fact;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(fact)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .enableAutoManage(fact, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Log.d("TAG", connectionResult.getErrorMessage());

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }


    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        ((Activity)ctx).startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        if (mGoogleApiClient.isConnected() == true) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        }
        logOut();
    }

    public boolean result(Intent data) {

        GoogleSignInResult gResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

        if (gResult.isSuccess() == true) {
            logIn(gResult);
            return true;
        } else {
            logOut();
            return false;
        }

    }

    private void logIn(GoogleSignInResult result) {
        // Signed in successfully, show authenticated UI.
        GoogleSignInAccount acct = result.getSignInAccount();

        Info.gId = acct.getId();
        Info.gEmail = acct.getEmail();
        Info.gDisplayName = acct.getDisplayName();
        Info.gGivenName = acct.getGivenName();
        Info.gFamilyName = acct.getFamilyName();
        Info.gPhotoUrl = acct.getPhotoUrl();
        Info.gAccount = acct.getAccount();
        Info.gGrantedScopes = acct.getGrantedScopes();
        Info.gIdToken = acct.getIdToken();
        Info.gServerAuthCode = acct.getServerAuthCode();

        Info.gDisplayNameNick = Info.gDisplayName;
        getPhoto();

    }

    public void logOut() {
        Info.gId = NO_SIGN;
        Info.gEmail = NO_SIGN;
        Info.gDisplayName = NO_SIGN;
        Info.gGivenName = NO_SIGN;
        Info.gFamilyName = NO_SIGN;
        Info.gPhotoUrl = null;
        Info.gAccount = null;
        Info.gGrantedScopes = null;
        Info.gIdToken = NO_SIGN;
        Info.gServerAuthCode = NO_SIGN;

        Info.gDisplayNameNick = NO_SIGN;
        Info.gPhotoUrlBitmap = null;
    }

    private void getPhoto() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Info.gPhotoUrlBitmap = Picasso.with(ctx).load(Info.gPhotoUrl).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

}

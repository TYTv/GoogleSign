package felix.com.googlesign.Account;

import android.accounts.Account;
import android.graphics.Bitmap;
import android.net.Uri;

import com.google.android.gms.common.api.Scope;

import java.util.Set;

/**
 * Created by Felix on 2017/10/8.
 */

public final class Info {

    public static String NO_SIGN = "guest";

    public static String gId = NO_SIGN;
    public static String gEmail = NO_SIGN;
    public static String gDisplayName = NO_SIGN;
    public static String gGivenName = NO_SIGN;
    public static String gFamilyName = NO_SIGN;
    public static Uri gPhotoUrl = null;
    public static Account gAccount = null;
    public static Set<Scope> gGrantedScopes = null;
    public static String gIdToken = NO_SIGN;
    public static String gServerAuthCode = NO_SIGN;

    public static String gDisplayNameNick = NO_SIGN;
    public static Bitmap gPhotoUrlBitmap = null;

}

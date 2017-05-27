package com.example.chie.notifitest0429;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.example.chie.notifitest0429.MainActivity.flag;
import static com.example.chie.notifitest0429.MainActivity.uid;

/**
 * Created by chie on 2017/04/29.
 */

public class MyFirebaseInstanceIdservice extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIdservice";
    public static String token;

    @Override
    public void onTokenRefresh() {
        //アプリ初起動時にFCMトークンを生成する
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "RefreshedToken = " + token);

        //TODO
        //生成されたトークンをmain_activityのtoken_viewに指定する

        //トークンの値をサーバーへ送信
        submit(token);
    }

    private void submit(String token) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.d(TAG, "getInstance");

        // 2017/5/27　変更部分
        // 生成されたトークンを,fcmTokensの子として登録
        // キー：トークン値
        // 値：uid (MainActivity内で取得。ログインしたアカウントに対応するのもの)
        DatabaseReference refToken = database.getReference("/fcmTokens");
        Log.d(TAG, "getReference");
        refToken.child(token).setValue(uid);
        Log.d(TAG, "setValue");

    }

}

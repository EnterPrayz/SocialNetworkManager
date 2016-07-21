package com.enterprayz.social.facebook;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.enterprayz.social.core.LoginListener;
import com.enterprayz.social.core.SocialNetwork;
import com.enterprayz.social.core.beans.NetworkTag;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by hacker on 14.07.16.
 */
public class FacebookSocialNetwork extends SocialNetwork {
    private LoginListener listener;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Override
    public NetworkTag getTag() {
        return NetworkTag.FACEBOOK;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFingerPrint();
    }

    @Override
    public void getAccessToken(LoginListener listener) {
        this.listener = listener;
        initlogin();
        loginManager.logInWithReadPermissions(getFragment(), Arrays.asList("email", "user_likes"));
    }

    private void initlogin() {
        FacebookSdk.sdkInitialize(getFragment().getContext());
        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                listener.onGetAccessToken(NetworkTag.FACEBOOK, loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                listener.onGetError(error);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            loginManager.logOut();
        }
    }

    private void showFingerPrint() {
        try {
            PackageInfo info = getFragment().getActivity().getPackageManager().getPackageInfo(
                    getFragment().getActivity().getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("FacebookHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
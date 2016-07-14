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
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hacker on 14.07.16.
 */
public class FacebookSocialNetwork extends SocialNetwork {
    private LoginListener listener;
    private CallbackManager callbackManager;
    private LoginButton login;

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
        login.performClick();
    }

    private void initlogin() {
        FacebookSdk.sdkInitialize(getFragment().getContext());
        login = new LoginButton(getFragment().getContext());
        login.setReadPermissions("email");
        login.setFragment(getFragment());
        callbackManager = CallbackManager.Factory.create();
        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
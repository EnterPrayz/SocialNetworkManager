package com.enterprayz.social.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;


import com.enterprayz.social.core.beans.NetworkTag;

import java.io.Serializable;

/**
 * Created by hacker on 14.07.16.
 */
public abstract class SocialNetwork implements Serializable {
    private Fragment fragment;

    public abstract NetworkTag getTag();

    public SocialNetwork() {

    }

    @CallSuper
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    protected Fragment getFragment() {
        return fragment;
    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public void onCreate(Bundle savedInstanceState) {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }

    public abstract void getAccessToken(LoginListener listener);

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}

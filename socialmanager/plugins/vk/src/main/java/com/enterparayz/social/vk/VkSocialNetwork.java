package com.enterparayz.social.vk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.enterprayz.social.core.LoginListener;
import com.enterprayz.social.core.SocialNetwork;
import com.enterprayz.social.core.beans.NetworkTag;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

/**
 * Created by hacker on 14.07.16.
 */
public class VkSocialNetwork extends SocialNetwork {
    private LoginListener loginListener;
    private final String[] scopes = new String[]{
            VKScope.FRIENDS,
            VKScope.WALL,
            VKScope.PHOTOS,
            VKScope.NOHTTPS
    };

    @Override
    public NetworkTag getTag() {
        return NetworkTag.VKONTAKTE;
    }

    @Override
    public void getAccessToken(LoginListener listener) {
        this.loginListener = listener;
        VKSdk.login(getFragment().getActivity(), scopes);
    }

    private void showVkHash(Activity activity) {
        String[] fingerprints = VKUtil.getCertificateFingerprint(activity, activity.getPackageName());
        Log.d("VkHash", Arrays.toString(fingerprints));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showVkHash(getFragment().getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, vkLoginCallback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private VKCallback<VKAccessToken> vkLoginCallback = new VKCallback<VKAccessToken>() {
        @Override
        public void onResult(VKAccessToken res) {
            if (loginListener != null)
                loginListener.onGetAccessToken(NetworkTag.VKONTAKTE, res.accessToken);
        }

        @Override
        public void onError(VKError error) {
            if (loginListener != null)
                loginListener.onGetError(error.httpError);
        }
    };


}

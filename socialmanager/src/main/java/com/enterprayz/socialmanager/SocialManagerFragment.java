package com.enterprayz.socialmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.enterprayz.social.core.SocialNetwork;
import com.enterprayz.social.core.beans.NetworkTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hacker on 14.07.16.
 */
public class SocialManagerFragment extends Fragment implements ISocialManager {
    public static final String TAG = SocialManagerFragment.class.getSimpleName();
    private Map<NetworkTag, SocialNetwork> socialNetworksMap = new HashMap<>();
    private OnInitializeListener initializeListener;


    public void setNetworks(ArrayList<SocialNetwork> socialNetworks) {
        for (SocialNetwork item : socialNetworks) {
            item.setFragment(this);
            socialNetworksMap.put(item.getTag(), item);
        }
    }

    public void setIniListener(OnInitializeListener initializeListener) {
        this.initializeListener = initializeListener;
    }

    @Override
    public SocialNetwork getSocialNetwork(NetworkTag tag) throws RuntimeException {
        if (!socialNetworksMap.containsKey(tag)) {
            throw new RuntimeException("Social network with tag = " + tag + " not found");
        }
        return socialNetworksMap.get(tag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initializeListener.onStart(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        for (SocialNetwork socialNetwork : socialNetworksMap.values()) {
            socialNetwork.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        for (SocialNetwork socialNetwork : socialNetworksMap.values()) {
            socialNetwork.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (SocialNetwork socialNetwork : socialNetworksMap.values()) {
            socialNetwork.onResume();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        for (SocialNetwork socialNetwork : socialNetworksMap.values()) {
            socialNetwork.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        for (SocialNetwork socialNetwork : socialNetworksMap.values()) {
            socialNetwork.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (SocialNetwork socialNetwork : socialNetworksMap.values()) {
            socialNetwork.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (SocialNetwork socialNetwork : socialNetworksMap.values()) {
            socialNetwork.onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (SocialNetwork socialNetwork : socialNetworksMap.values()) {
            socialNetwork.onActivityResult(requestCode, resultCode, data);
        }
    }
}

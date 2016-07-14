package com.enterprayz.socialmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.enterprayz.socialmanager.beans.NetworkTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hacker on 14.07.16.
 */
public class SocialManagerFragment extends Fragment {
    private static final String BUNDLE_NEWTWORKS_KEY = "networks";
    public static final String TAG = SocialManagerFragment.class.getSimpleName();
    private Map<NetworkTag, SocialNetwork> socialNetworksMap = new HashMap<>();

    public static SocialManagerFragment getInstance(ArrayList<SocialNetwork> socialNetworks) {
        SocialManagerFragment fragment = new SocialManagerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_NEWTWORKS_KEY, socialNetworks);
        fragment.setArguments(bundle);
        return fragment;
    }

    public SocialNetwork getSocialNetwork(NetworkTag tag) throws RuntimeException {
        if (!socialNetworksMap.containsKey(tag)) {
            throw new RuntimeException("Social network with tag = " + tag + " not found");
        }
        return socialNetworksMap.get(tag);
    }

    private void prepare(Bundle bundle) {
        ArrayList<SocialNetwork> list = (ArrayList<SocialNetwork>) bundle.getSerializable(BUNDLE_NEWTWORKS_KEY);
        for (SocialNetwork item : list) {
            socialNetworksMap.put(item.getTag(), item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepare(getArguments());
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

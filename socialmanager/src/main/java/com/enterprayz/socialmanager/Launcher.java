package com.enterprayz.socialmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.enterprayz.social.core.SocialNetwork;

import java.util.ArrayList;

/**
 * Created by hacker on 14.07.16.
 */
public class Launcher {
    private FragmentManager fragmentManager;
    private ArrayList<SocialNetwork> networks = new ArrayList<>();

    private Launcher(FragmentManager fragment, ArrayList<SocialNetwork> networks) {
        this.fragmentManager = fragment;
        this.networks = networks;
    }

    private SocialManagerFragment ini(OnInitializeListener initializeListener) {
        Fragment social = fragmentManager.findFragmentByTag(SocialManagerFragment.TAG);
        if (social == null) {
            SocialManagerFragment fragment = new SocialManagerFragment();
            fragment.setNetworks(networks);
            fragment.setIniListener(initializeListener);

            fragmentManager.beginTransaction()
                    .add(fragment, SocialManagerFragment.TAG)
                    .commitAllowingStateLoss();
            social = fragment;
        }
        return (SocialManagerFragment) social;
    }


    public static class Builder {
        private FragmentManager fragment;
        private ArrayList<SocialNetwork> networks = new ArrayList<>();

        public Builder(FragmentManager fragment) {
            this.fragment = fragment;
        }

        public static Builder ini(FragmentManager fragment) {
            return new Builder(fragment);
        }

        public Builder addNetwork(SocialNetwork socialNetwork) {
            networks.add(socialNetwork);
            return this;
        }

        public ISocialManager create(OnInitializeListener initializeListener) {
            return new Launcher(fragment, networks).ini(initializeListener);
        }
    }
}

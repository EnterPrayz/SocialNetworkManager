package com.enterprayz.socialmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.enterprayz.social.core.SocialNetwork;

import java.util.ArrayList;

/**
 * Created by hacker on 14.07.16.
 */
public class Launcher {
    private FragmentManager fragment;
    private ArrayList<SocialNetwork> networks = new ArrayList<>();


    private Launcher(FragmentManager fragment, ArrayList<SocialNetwork> networks) {
        this.fragment = fragment;
        this.networks = networks;
        ini();
    }


    private void ini() {
        Fragment social = fragment.findFragmentByTag(SocialManagerFragment.TAG);
        if (social == null) {
            fragment.beginTransaction()
                    .add(SocialManagerFragment.getInstance(networks), SocialManagerFragment.TAG)
                    .commitAllowingStateLoss();
        }
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

        public Launcher create() {
            return new Launcher(fragment, networks);
        }
    }
}

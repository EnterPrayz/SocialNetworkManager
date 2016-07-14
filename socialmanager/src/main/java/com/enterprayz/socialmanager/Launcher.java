package com.enterprayz.socialmanager;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hacker on 14.07.16.
 */
public class Launcher {
    private Fragment fragment;
    private ArrayList<SocialNetwork> networks = new ArrayList<>();

    private Launcher(Fragment fragment, ArrayList<SocialNetwork> networks) {
        this.fragment = fragment;
        this.networks = networks;
    }


    private void ini() {
        Fragment social = fragment.getFragmentManager().findFragmentByTag(SocialManagerFragment.TAG);
        if (social == null) {
            fragment.getChildFragmentManager().beginTransaction()
                    .add(SocialManagerFragment.getInstance(networks), SocialManagerFragment.TAG)
                    .commitAllowingStateLoss();
        }
    }

    public static class Builder {
        private Fragment fragment;
        private ArrayList<SocialNetwork> networks = new ArrayList<>();

        public Builder(Fragment fragment) {
            this.fragment = fragment;
        }

        public static Builder ini(Fragment fragment) {
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

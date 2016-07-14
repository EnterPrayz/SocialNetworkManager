package com.enterprayz.socialmanager;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hacker on 14.07.16.
 */
public class Launcher {
    private Fragment fragment;
    private List<SocialNetwork> networks = new ArrayList<>();
    private Listener listener;

    private Launcher(Fragment fragment, List<SocialNetwork> networks, Listener listener) {
        this.fragment = fragment;
        this.networks = networks;
        this.listener = listener;
    }


    private void ini() {
        Fragment social = fragment.getFragmentManager().findFragmentByTag(SocialManagerFragment.TAG);
        if (social == null) {
            fragment.getChildFragmentManager().beginTransaction()
                    .add(new SocialManagerFragment(), SocialManagerFragment.TAG)
                    .commitAllowingStateLoss();

            social = fragment.getChildFragmentManager().findFragmentByTag(SocialManagerFragment.TAG);
        }



    }


    public static class Builder {
        private Fragment fragment;
        private List<SocialNetwork> networks = new ArrayList<>();
        private Listener listener;

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

        public Builder setListener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public Launcher create() {
            return new Launcher(fragment, networks, listener);
        }
    }
}

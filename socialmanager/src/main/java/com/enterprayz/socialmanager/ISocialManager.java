package com.enterprayz.socialmanager;

import android.content.Intent;

import com.enterprayz.social.core.SocialNetwork;
import com.enterprayz.social.core.beans.NetworkTag;

/**
 * Created by hacker on 14.07.16.
 */
public interface ISocialManager {

    SocialNetwork getSocialNetwork(NetworkTag tag);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}

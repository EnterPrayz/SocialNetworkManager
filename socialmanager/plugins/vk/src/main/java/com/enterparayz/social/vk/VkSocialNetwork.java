package com.enterparayz.social.vk;

import com.enterprayz.social.core.LoginListener;
import com.enterprayz.social.core.SocialNetwork;
import com.enterprayz.social.core.beans.NetworkTag;

/**
 * Created by hacker on 14.07.16.
 */
public class VkSocialNetwork extends SocialNetwork {
    @Override
    public NetworkTag getTag() {
        return NetworkTag.VKONTAKTE;
    }

    @Override
    public void getAccessToken(LoginListener listener) {

    }
}

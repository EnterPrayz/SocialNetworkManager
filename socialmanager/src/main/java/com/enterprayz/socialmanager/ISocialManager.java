package com.enterprayz.socialmanager;

import com.enterprayz.social.core.SocialNetwork;
import com.enterprayz.social.core.beans.NetworkTag;

/**
 * Created by hacker on 14.07.16.
 */
public interface ISocialManager {
    SocialNetwork getSocialNetwork(NetworkTag tag);
}

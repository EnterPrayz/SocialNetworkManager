package com.enterprayz.social.core;


import com.enterprayz.social.core.beans.NetworkTag;

/**
 * Created by hacker on 14.07.16.
 */
public interface LoginListener {
    void onGetAccessToken(NetworkTag networkTag, String token);

    void onGetError(Throwable error);
}

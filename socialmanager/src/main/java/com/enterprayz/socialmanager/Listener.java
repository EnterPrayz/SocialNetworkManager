package com.enterprayz.socialmanager;

import com.enterprayz.socialmanager.beans.NetworkTag;

/**
 * Created by hacker on 14.07.16.
 */
public interface Listener {
    void onGetAccessToken(NetworkTag networkTag, String token);

    void onGetError(Throwable error);
}

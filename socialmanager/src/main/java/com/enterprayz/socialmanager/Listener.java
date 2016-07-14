package com.enterprayz.socialmanager;

/**
 * Created by hacker on 14.07.16.
 */
public interface Listener {
    void onGetAccessToken(String token);

    void onGetError(Throwable error);
}

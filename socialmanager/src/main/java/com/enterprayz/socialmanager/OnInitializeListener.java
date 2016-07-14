package com.enterprayz.socialmanager;

import java.io.Serializable;

/**
 * Created by hacker on 14.07.16.
 */
public interface OnInitializeListener extends Serializable{
    void onStart(ISocialManager socialManager);
}

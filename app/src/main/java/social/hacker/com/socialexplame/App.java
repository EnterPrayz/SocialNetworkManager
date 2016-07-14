package social.hacker.com.socialexplame;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by hacker on 14.07.16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}

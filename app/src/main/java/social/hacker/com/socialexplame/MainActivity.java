package social.hacker.com.socialexplame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.enterparayz.social.vk.VkSocialNetwork;
import com.enterprayz.social.core.LoginListener;
import com.enterprayz.social.core.beans.NetworkTag;
import com.enterprayz.social.facebook.FacebookSocialNetwork;
import com.enterprayz.socialexplame.GoogleSocialNetwork;
import com.enterprayz.socialmanager.ISocialManager;
import com.enterprayz.socialmanager.Launcher;
import com.enterprayz.socialmanager.OnInitializeListener;

public class MainActivity extends AppCompatActivity {
    private View btnVk;
    private View btnFb, btnGoogle;


    private VkSocialNetwork vkSocialNetwork;
    private FacebookSocialNetwork facebookSocialNetwork;
    private GoogleSocialNetwork googleSocialNetwork;
    private ISocialManager socialManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniUi();
        setListeners();
        iniSocialManager();
    }

    private void iniSocialManager() {
        Launcher.Builder
                .ini(getSupportFragmentManager())
                .addNetwork(new FacebookSocialNetwork())
                .addNetwork(new GoogleSocialNetwork())
                .addNetwork(new VkSocialNetwork())
                .create(new OnInitializeListener() {
                    @Override
                    public void onStart(ISocialManager socialManager) {
                        MainActivity.this.socialManager = socialManager;
                        MainActivity.this.vkSocialNetwork = (VkSocialNetwork) socialManager.getSocialNetwork(NetworkTag.VKONTAKTE);
                        MainActivity.this.googleSocialNetwork = (GoogleSocialNetwork) socialManager.getSocialNetwork(NetworkTag.GOOGLE);
                        MainActivity.this.facebookSocialNetwork = (FacebookSocialNetwork) socialManager.getSocialNetwork(NetworkTag.FACEBOOK);

                    }
                });
    }


    private void iniUi() {
        btnVk = findViewById(R.id.btn_login_vk);
        btnFb = findViewById(R.id.btn_login_fb);
        btnGoogle = findViewById(R.id.btn_login_google);
    }

    private void setListeners() {
        btnVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vkSocialNetwork != null)
                    vkSocialNetwork.getAccessToken(new LoginListener() {
                        @Override
                        public void onGetAccessToken(NetworkTag networkTag, String token) {
                            Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onGetError(Throwable error) {

                        }
                    });

            }
        });
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (facebookSocialNetwork != null)
                    facebookSocialNetwork.getAccessToken(new LoginListener() {
                        @Override
                        public void onGetAccessToken(NetworkTag networkTag, String token) {
                            Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onGetError(Throwable error) {

                        }
                    });
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleSocialNetwork != null)
                    googleSocialNetwork.getAccessToken(new LoginListener() {
                        @Override
                        public void onGetAccessToken(NetworkTag networkTag, String token) {
                            Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onGetError(Throwable error) {

                        }
                    });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        socialManager.onActivityResult(requestCode, resultCode, data);
    }
}

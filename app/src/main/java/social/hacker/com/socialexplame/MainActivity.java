package social.hacker.com.socialexplame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private View btnVk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniUi();
        setListeners();
    }

    private void iniUi() {
        btnVk = findViewById(R.id.btn_login_vk);
    }

    private void setListeners() {
        btnVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

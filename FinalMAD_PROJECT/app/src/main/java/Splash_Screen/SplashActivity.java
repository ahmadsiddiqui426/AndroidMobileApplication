package Splash_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalmad_project.R;

import Login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    // Duration of wait in milliseconds (e.g., 3000ms = 3 seconds)
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // New Handler to start the LoginActivity and close this Splash-Screen after the set time.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent that will start the LoginActivity.
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish(); // Close the SplashActivity
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

package Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import Dialog.DialogActivity;
import Forms.FormsActivity;
import com.example.finalmad_project.R;

import GetAPI.GetAPIActivity;
//import Notification.NotificationActivity;
import Notification.MainActivity2;
import PostAPI.PostAPIActivity;
import RoomDatabase.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4,button5,button6,button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize buttons
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5); // Make sure this matches the ID in your layout file
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        // Set click listener for the Calculator button
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Calculator.CalculatorActivity.class);
            startActivity(intent);
        });

        // Set click listener for the Forms button
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FormsActivity.class);
            startActivity(intent);
        });

        // Set click listener for the GET API button
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, GetAPIActivity.class);
            startActivity(intent);
        });

        // Set click listener for the POST API button
        button4.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PostAPIActivity.class);
            startActivity(intent);
        });

         //Set click listener for the Notification Manager button
        button5.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("HomeActivity", "Error in starting NotificationActivity", e);
            }
        });

        button6.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        button7.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DialogActivity.class);
            startActivity(intent);
        });

    }
}

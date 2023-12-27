package Signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.finalmad_project.R;
import androidx.appcompat.app.AppCompatActivity;
import RoomDb.UserRepository;
import RoomDb.User;

public class SignupActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, usernameEditText, passwordEditText;
    private Button saveButton, backToLoginButton;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize the UserRepository
        userRepository = new UserRepository(this);

        // Initialize EditTexts
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Initialize Buttons
        saveButton = findViewById(R.id.saveButton);
        backToLoginButton = findViewById(R.id.backToLoginButton);

        // Set onClickListener for the Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the input data
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate the input data
                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a new User object
                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setEmail(email);
                    newUser.setUsername(username);
                    newUser.setPassword(password); // In a real app, you should hash the password

                    // Insert the new user into the database
                    userRepository.insert(newUser);

                    // Notify the user
                    Toast.makeText(SignupActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();

                    // Optionally, navigate to the Login activity
                    // Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    // startActivity(intent);
                    // finish();
                }
            }
        });

        // Set onClickListener for the Back to Login button
        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to return to the previous one
                finish();
            }
        });
    }
}

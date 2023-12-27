package Forms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalmad_project.R;
import java.util.ArrayList;

public class FormsActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private Button saveButton;
    private Button showDataButton;
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private ArrayList<DataItem> dataItems = new ArrayList<>();
    private boolean dataSaved = false; // A flag to track whether data is saved

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        saveButton = findViewById(R.id.saveButton);
        showDataButton = findViewById(R.id.showDataButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new DataAdapter(dataItems);
        recyclerView.setAdapter(dataAdapter);

        // Save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty()) {
                    DataItem dataItem = new DataItem(name, email);
                    dataItems.add(dataItem);

                    // Clear the EditText fields
                    nameEditText.setText("");
                    emailEditText.setText("");

                    // Update the flag
                    dataSaved = true;

                    Toast.makeText(FormsActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormsActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Show Data in RecyclerView button click listener
        showDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataSaved) {
                    dataAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(FormsActivity.this, "No data to display. Save data first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

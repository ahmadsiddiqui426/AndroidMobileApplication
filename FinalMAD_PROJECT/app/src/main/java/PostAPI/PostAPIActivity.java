package PostAPI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.finalmad_project.R;
public class PostAPIActivity extends AppCompatActivity {

    private EditText txtName, txtJob;
    private TextView lblOutput;
    private Button btnPostData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postapi);

        txtName = findViewById(R.id.txtName);
        txtJob = findViewById(R.id.txtJob);
        lblOutput = findViewById(R.id.lblOutput);
        btnPostData = findViewById(R.id.btnPostData);

        btnPostData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Data From TextBox
                String strName = txtName.getText().toString();
                String strJob = txtJob.getText().toString();

                if (TextUtils.isEmpty(strName)) {
                    Toast.makeText(PostAPIActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(strJob)) {
                    Toast.makeText(PostAPIActivity.this, "Please Enter Job", Toast.LENGTH_SHORT).show();
                } else {
                    // Make the POST request using Retrofit
                    Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                    Call<Model> call = methods.getUserData(strName, strJob);

                    call.enqueue(new Callback<Model>() {
                        @Override
                        public void onResponse(Call<Model> call, Response<Model> response) {
                            if (response.isSuccessful()) {
                                // Print the response for debugging
                                Log.d("Response", "onResponse: " + response.body());

                                // Update UI with the response data
                                String strOutput = "Id : " + response.body().getId() + "\n";
                                strOutput += "Name : " + response.body().getName() + "\n";
                                strOutput += "Job : " + response.body().getJob() + "\n";
                                strOutput += "Created At : " + response.body().getCreatedAt();
                                lblOutput.setText(strOutput);
                            } else {
                                // Handle unsuccessful response
                                Log.e("ResponseError", "onResponse: " + response.message());
                                Toast.makeText(PostAPIActivity.this, "Unsuccessful response", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Model> call, Throwable t) {
                            // Print the error for debugging
                            Log.e("RequestError", "onFailure: " + t.getMessage());

                            // Show a Toast message or handle the error as needed
                            Toast.makeText(PostAPIActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}

package RoomDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.finalmad_project.R;
import RoomDatabase.Users;
import RoomDatabase.UserDatabase;
import RoomDatabase.UserDao;


import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener {

    EditText nameEd,emailEd;
    Button insertBtn;

    RecyclerView myrecycler;
    private UserDatabase userDatabase;
    private UserDao userDao;

    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomdatabase);

        userDatabase=UserDatabase.getINSTANCE(this);
        userDao=userDatabase.getDao();

        nameEd=findViewById(R.id.name);
        emailEd=findViewById(R.id.email);
        insertBtn=findViewById(R.id.insert);
        myrecycler =findViewById(R.id.usersRecycler);

        userAdapter=new UserAdapter(this,this);
        myrecycler.setAdapter(userAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=nameEd.getText().toString();
                String email=emailEd.getText().toString();

                Users users=new Users(0,name,email);
                userAdapter.addUser(users);
                userDao.insert(users);
                nameEd.setText("");
                emailEd.setText("");
                Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void fetchData(){
        List<Users> usersList = userDao.getAllUsers();
        for(int i=0;i<usersList.size();i++){
            Users users = usersList.get(i);
            userAdapter.addUser(users);
        }
    }

    @Override
    public void onDelete(int id, int pos) {
        userDao.delete(id);
        userAdapter.removeUser(pos);
    }
}
package sg.edu.np.mad.exercise_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main Activity";
    String value;
    String dValue;
    Boolean fValue;
    Integer pValue;
    ArrayList<User> users = ListActivity.usersList;
    DBHandler dbHandler = new DBHandler(this, null, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = getIntent();

        value = myIntent.getStringExtra("Name");
        dValue = myIntent.getStringExtra("Description");
        fValue = myIntent.getBooleanExtra("Followed", false);
        pValue = myIntent.getIntExtra("Position", 0);

        TextView userName = findViewById(R.id.textView2);
        userName.setText(value);
        TextView desc = findViewById(R.id.textView);
        desc.setText(dValue);
        Button myFollowButton = findViewById(R.id.myFollowButton);
        if (fValue == false){
            myFollowButton.setText("Follow");
        }
        else{
            myFollowButton.setText("Unfollow");
        }
        myFollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(users.get(pValue).isFollowed() == false){
                    myFollowButton.setText("Unfollow");
                    users.get(pValue).setFollowed(true);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_LONG).show();
                }
                else{
                    myFollowButton.setText("Follow");
                    users.get(pValue).setFollowed(false);
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_LONG).show();
                }
                dbHandler.updateUser(users.get(pValue));
            }
        });
        Button myMessageButton = findViewById(R.id.myMessageButton);
        myMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(myIntent);
            }
        });
    }
}
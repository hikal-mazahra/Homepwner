package android.bignerdranch.majorprojectassignment1;

import android.bignerdranch.majorprojectassignment1.R;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        if (savedInstanceState == null) {
            ItemFragment fragment = new ItemFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
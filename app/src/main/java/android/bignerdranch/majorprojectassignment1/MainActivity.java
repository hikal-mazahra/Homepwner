package android.bignerdranch.majorprojectassignment1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment); // Not fragment_item.xml

        if (savedInstanceState == null) {
            // Create a new fragment and transaction to display it
            ItemFragment itemFragment = new ItemFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, itemFragment)
                    .commit();
        }
    }
}
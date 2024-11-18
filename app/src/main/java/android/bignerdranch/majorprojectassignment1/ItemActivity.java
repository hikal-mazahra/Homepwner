package android.bignerdranch.majorprojectassignment1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ItemActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ItemFragment();
    }
}
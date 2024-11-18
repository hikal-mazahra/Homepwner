package android.bignerdranch.majorprojectassignment1;

import androidx.fragment.app.Fragment;

public class ItemListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new ItemListFragment();
    }
}

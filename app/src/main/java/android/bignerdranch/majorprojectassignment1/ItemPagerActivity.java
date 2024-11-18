package android.bignerdranch.majorprojectassignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;
import java.util.UUID;

public class ItemPagerActivity extends AppCompatActivity {
    private static final String EXTRA_ITEM_ID = "android.bignerdranch.majorprojectassignment1.item_id";
    private ViewPager2 mViewPager;
    private List<Item> mItems;

    public static Intent newIntent(Context packageContext, UUID itemId){
        // Convert UUID to String to pass through Intent
        Intent intent = new Intent(packageContext, ItemPagerActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId.toString());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_pager);

        // Retrieve the UUID from the Intent and convert it back to UUID
        String itemIdString = getIntent().getStringExtra(EXTRA_ITEM_ID);
        UUID itemId = itemIdString != null ? UUID.fromString(itemIdString) : null;

        mViewPager = findViewById(R.id.item_view_pager);
        mItems = ItemLab.get(this).getmItems();

        // Use this instead of fragmentManager in the FragmentStateAdapter constructor
        mViewPager.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public Fragment createFragment(int position) {
                Item item = mItems.get(position);
                return ItemFragment.newInstance(item.getId());
            }

            @Override
            public int getItemCount() {
                return mItems.size();
            }
        });

        // Find and set the current item
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).getId().equals(itemId)) {
                mViewPager.setCurrentItem(i, false); // false for no smooth scroll
                break;
            }
        }
    }
}
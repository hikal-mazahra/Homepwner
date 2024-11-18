    package android.bignerdranch.majorprojectassignment1;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;

    import java.util.UUID;

    public class ItemActivity extends SingleFragmentActivity {
        private static final String EXTRA_ITEM_ID = "android.bignerdranch.majorprojectassignment1.item_id";
       public static Intent newIntent(Context packageContext, UUID itemId){
           Intent intent = new Intent(packageContext, ItemActivity.class);
           intent.putExtra(EXTRA_ITEM_ID, itemId.toString());
           return intent;
       }
        @Override
        protected Fragment createFragment() {

            UUID itemId = (UUID) getIntent().getSerializableExtra(EXTRA_ITEM_ID);
            return ItemFragment.newInstance(itemId);
        }
    }
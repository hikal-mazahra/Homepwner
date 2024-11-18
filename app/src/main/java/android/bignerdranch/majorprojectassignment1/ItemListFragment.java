package android.bignerdranch.majorprojectassignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemListFragment extends Fragment {
    private RecyclerView mItemRecyclerView;
    private ItemAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mItemRecyclerView = (RecyclerView) view
                .findViewById(R.id.item_recycler_view);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    public void updateUI(){
        ItemLab itemLab = ItemLab.get(getActivity());
        List<Item> items = itemLab.getmItems();
        mAdapter = new ItemAdapter(items);
        mItemRecyclerView.setAdapter(mAdapter);
    }
    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNameTextView;
        private TextView mValueTextView;
        private Item mItem;
        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_item, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.item_name);
            mValueTextView = (TextView) itemView.findViewById(R.id.item_value);
        }

        public void bind(Item item){
            mItem = item;
            mNameTextView.setText(mItem.getName());
            double value = mItem.getValue();
            mValueTextView.setText(String.format("%.2f", value));
        }
        @Override
        public void onClick(View view){
            Toast.makeText(getActivity(), mItem.getName() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private List<Item> mItems;
        public ItemAdapter(List<Item> items){
            mItems = items;
        }
        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ItemHolder(layoutInflater, parent);

        }
        @Override
        public void onBindViewHolder(ItemHolder holder, int position){
            Item item = mItems.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount(){
            return mItems.size();
        }

    }

}

package android.bignerdranch.majorprojectassignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ItemFragment extends androidx.fragment.app.Fragment
{
    private static final String ARG_ITEM_ID = "item_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;


    private Item mItem;
    private EditText mNameField;
    private EditText mSerialField;
    private EditText mValueField;
    private TextView mDateTextView;
    private DecimalFormat decimalFormat;
    private Button mDateButton;


    public static ItemFragment newInstance(UUID itemID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM_ID, itemID);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID itemId = (UUID) getArguments().getSerializable(ARG_ITEM_ID);
        mItem = ItemLab.get(getActivity()).getItem(itemId);
    }
    @Override
    public void onPause(){
        super.onPause();
        ItemLab.get(getActivity())
                .updateItem(mItem);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item, container, false);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(mItem.getName());
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mNameField = (EditText)v.findViewById(R.id.item_name);
        mSerialField = v.findViewById(R.id.item_serial);
        mValueField = v.findViewById(R.id.item_value);
        mDateTextView = v.findViewById(R.id.item_date);
        decimalFormat = new DecimalFormat("0.00");
        //here i am populating the fields with the current item information





        mNameField.setText(mItem.getName());
        mSerialField.setText(String.valueOf(mItem.getSerial()));
        mValueField.setText(decimalFormat.format(mItem.getValue()));
        mNameField = (EditText)v.findViewById(R.id.item_name);
        mNameField.setText(mItem.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mItem.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        mSerialField = (EditText)v.findViewById(R.id.item_serial);
        mSerialField.setText(mItem.getSerial());
        mSerialField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    // Generate a new serial only if the field is empty
                    mItem.generateSerial();
                    mSerialField.setText(mItem.getSerial());
                } else {
                    // Update mItem with the current serial directly if not empty
                    mSerialField.setText(mItem.getSerial());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mValueField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    try {
                        double value = Double.parseDouble(s.toString());
                        mItem.setValue(value);
                        if (!s.toString().equals(decimalFormat.format(value))) {
                            // Only update text if the formatted string is different from what's already there
                            mValueField.setText(decimalFormat.format(value));
                            mValueField.setSelection(mValueField.getText().length());
                        }
                    } catch (NumberFormatException e) {
                        // Log the error for debugging if needed
                        Log.e("ItemFragment", "Error parsing value: " + s, e);
                        // Optionally, you might want to keep the last valid value or set to 0.0
                        mItem.setValue(0.0);
                    }
                } else {
                    // If the field is empty, reset the item value
                    mItem.setValue(0.0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mDateButton = (Button) v.findViewById(R.id.item_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mItem.getmDate());
                dialog.setTargetFragment(ItemFragment.this, REQUEST_DATE);

                dialog.show(manager, DIALOG_DATE);
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mItem.setmDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mItem.getmDate().toString());
    }

    //getting the date with the correct format

}

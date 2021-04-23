package com.mindorks.example.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.mindorks.example.ui.activity.DatabaseHandler;
import com.mindorks.example.R;

public class AddItemFragment extends Fragment {
    Button add_values_button;
    EditText item_name;
    EditText item_quantity;
    EditText item_price;
    EditText item_expiry_date;


    public AddItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final DatabaseHandler db = new DatabaseHandler(getContext());


        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_add_item, container, false);

        add_values_button = (Button) root.findViewById(R.id.add_values_button);
        item_name = (EditText) root.findViewById(R.id.item_name);
        item_quantity = (EditText) root.findViewById(R.id.item_quantity);
        item_price = (EditText) root.findViewById(R.id.item_price);
        item_expiry_date = (EditText) root.findViewById(R.id.item_expiry_date);

        final String name_value = item_name.getText().toString();
        final String quantity_value = item_quantity.getText().toString();
        final String price_value = item_price.getText().toString();
        final String expiry_value = item_expiry_date.getText().toString();



        add_values_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((" ".equals(name_value)) || (" ".equals(quantity_value)) || (" ".equals(price_value))|| (" ".equals(expiry_value))){
                    Toast.makeText(getContext(), "Please check your input",Toast.LENGTH_SHORT).show();
                }else{
                    db.addValues(name_value,quantity_value,price_value,expiry_value);
                    Toast.makeText(getContext(), name_value +"added successfully", Toast.LENGTH_LONG).show();
                    item_name.setText(" ");
                    item_price.setText(" ");
                    item_quantity.setText(" ");
                    item_expiry_date.setText(" ");

                }
            }
        });

        return root;
    }
}
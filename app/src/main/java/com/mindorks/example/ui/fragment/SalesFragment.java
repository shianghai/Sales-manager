package com.mindorks.example.ui.fragment;



import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.io.StringReader;
import java.util.ArrayList;
import com.mindorks.example.R;

public class SalesFragment extends Fragment {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    String id_string = "R.id.";
    View root;
    RelativeLayout rL;
    Boolean displayNextItems = true;
    int groupCount = 0;
    ArrayList<String[]> widget_groups = new ArrayList<String[]>();
    String[] widgets_values;
    String searchItemTextViewText = "Item Name";
    String quantityTextViewText = "Quantity";
    String addNextItemButtonText = "ADD ITEM";
    String calculateButtonText = "CALCULATE";
    RelativeLayout.LayoutParams addItemButtonParams;
    RelativeLayout.LayoutParams searchItemTextViewParams;
    RelativeLayout.LayoutParams itemNameEditTextParams;
    RelativeLayout.LayoutParams calculateButtonParams;
    RelativeLayout.LayoutParams quantityTextViewParams;
    RelativeLayout.LayoutParams quantityEditTextParams;
    RelativeLayout.LayoutParams lineDividerParams;

    int searchTextViewId = 101;
    int itemNameEDitTextId = 201;
    int quantityTextViewId = 301;
    int quantityEditTextId = 401;
    int addItemsBtnId = 501;
    int calculateBtnId = 601;
    int lineDividerId = 701;





    String TAG = "D";


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sales, container, false);
        this.root = root;
        linearLayout = (LinearLayout) root.findViewById(R.id.linearLayout);




        rL = (RelativeLayout) root.findViewById(R.id.relativeLayout);
        setUpLayoutParams(groupCount);

        //reassign ids to the views so that, subsequent views can be created programmatically with sequential ids
        TextView searchTextView = (TextView) root.findViewById(R.id.search_item_textview);
        searchTextView.setId(searchTextViewId);
        searchTextView.setText(searchItemTextViewText);
        searchTextView.setTextSize(18);
        //searchTextView.setLayoutParams(searchItemTextViewParams);

        EditText itemNameEditText = (EditText) root.findViewById(R.id.item_name_edittext1);
        itemNameEditText.setId(itemNameEDitTextId);
        itemNameEditText.setLayoutParams(itemNameEditTextParams);


        TextView quantityTextView = (TextView) root.findViewById(R.id.quantity_textview);
        quantityTextView.setId(quantityTextViewId);
        quantityTextView.setText(quantityTextViewText);
        quantityTextView.setTextSize(18);
        quantityTextView.setLayoutParams(quantityTextViewParams);



        EditText quantityEditText = (EditText) root.findViewById(R.id.quantity_edittext1);
        quantityEditText.setId(quantityEditTextId);
        quantityEditText.setLayoutParams(quantityEditTextParams);


        final Button addItemsBtn = (Button) root.findViewById(R.id.addItemBtn1);
        addItemsBtn.setId(addItemsBtnId);
        addItemsBtn.setText(addNextItemButtonText);
        addItemsBtn.setLayoutParams(addItemButtonParams);

        Button calculateBtn = (Button) root.findViewById(R.id.calculateBtn1);
        calculateBtn.setId(calculateBtnId);
        calculateBtn.setText(calculateButtonText);
        calculateBtn.setLayoutParams(calculateButtonParams);


        View lineDivider = (View) root.findViewById(R.id.line_divider1);
        lineDivider.setId(lineDividerId);
        lineDivider.setLayoutParams(lineDividerParams);


        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = calculateTotal();
            }
        });


        addItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout relativeLayout = new RelativeLayout(getContext());
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                groupCount +=1;
                relativeLayout.setId(groupCount);
                relativeLayout.setLayoutParams(params);

                setUpLayoutParams(groupCount);

                displayNextGroup(relativeLayout);


                addItemsBtn.setClickable(false);



            }
        });
        return root;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
    }

    @Override
    public void onResume() {
        super.onResume();
        for (int i = 1; i < 6; i++){
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(params);
        displayNextGroup(relativeLayout);
    }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void displayNextGroup(RelativeLayout relativeLayout){
        String item_name;
        String quantity;
        EditText item_name_editText;
        EditText quantity_editText;

        widgets_values = new String[2];



        while(displayNextItems){

            item_name_editText = (EditText) root.findViewById(itemNameEDitTextId);
            item_name = item_name_editText.getText().toString();

            quantity_editText = (EditText) root.findViewById(quantityEditTextId);
            quantity = quantity_editText.getText().toString();


            widgets_values[0] = item_name;
            widgets_values[1] = quantity;


            widget_groups.add(widgets_values);
            Log.i(TAG, "assigned current values");

            searchTextViewId+=groupCount;
            itemNameEDitTextId +=groupCount;
            quantityTextViewId +=groupCount;
            quantityEditTextId += groupCount;
            addItemsBtnId += groupCount;
            calculateBtnId += groupCount;
            lineDividerId += groupCount;


            TextView searchItemTextView = new TextView(this.getContext());
            searchItemTextView.setText(searchItemTextViewText);
            searchItemTextView.setTextSize(18);
            searchItemTextView.setId(searchTextViewId);


            EditText item_name_editText_temp = new EditText(this.getContext());
            item_name_editText_temp.setId(itemNameEDitTextId);
            //itemNameEditTextParams.addRule(RelativeLayout.BELOW, 10);

            TextView quantityTextView = new TextView(this.getContext());
            quantityTextView.setText(quantityTextViewText);
            quantityTextView.setId(quantityTextViewId);
            quantityTextView.setTextSize(18);


            EditText quantity_editText_temp = new EditText(this.getContext());
            quantity_editText_temp.setId(quantityEditTextId);
            //quantityEditTextParams.addRule(RelativeLayout.BELOW, 123);


            final Button add_next_item_button = new Button(this.getContext());
            add_next_item_button.setText(addNextItemButtonText);
            add_next_item_button.setId(addItemsBtnId);
            //addItemButtonParams.addRule(RelativeLayout.BELOW, Integer.parseInt(id_string + "quantity_edittext" + j));


            Button calculate_button = new Button(this.getContext());
            calculate_button.setText(calculateButtonText);
            calculate_button.setId(calculateBtnId);
            //calculateButtonParams.addRule(RelativeLayout.BELOW, Integer.parseInt(id_string + "quantity_edittext" + j));


            View line_divider = new View(this.getContext());
            lineDividerParams.topMargin = 10;
            line_divider.setBackgroundColor(getResources().getColor(R.color.dark_gray));
            line_divider.setId(lineDividerId);
            //lineDividerParams.addRule(RelativeLayout.BELOW, Integer.parseInt(id_string + "addItemBtn" +j));

            searchItemTextView.setLayoutParams(searchItemTextViewParams);
            item_name_editText_temp.setLayoutParams(itemNameEditTextParams);
            quantityTextView.setLayoutParams(quantityTextViewParams);
            quantity_editText_temp.setLayoutParams(quantityEditTextParams);
            add_next_item_button.setLayoutParams(addItemButtonParams);
            calculate_button.setLayoutParams(calculateButtonParams);
            line_divider.setLayoutParams(lineDividerParams);

            relativeLayout.addView(searchItemTextView);
            relativeLayout.addView(item_name_editText_temp);
            relativeLayout.addView(quantityTextView);
            relativeLayout.addView(quantity_editText_temp);
            relativeLayout.addView(add_next_item_button);
            relativeLayout.addView(calculate_button);
            relativeLayout.addView(line_divider);

            linearLayout.addView(relativeLayout);
            Log.i(TAG, "line divider id = " + lineDividerId);


            add_next_item_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout relativeLayout = new RelativeLayout(getContext());
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                    groupCount +=1;

                    relativeLayout.setLayoutParams(params);

                    setUpLayoutParams(groupCount);
                    displayNextItems = true;

                    displayNextGroup(relativeLayout);
                    add_next_item_button.setClickable(false);



                }
            });

            calculate_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int total = calculateTotal();
                }
            });

            Log.i(TAG, "created next group widgets");
            displayNextItems = false;
        }

    }

    public int calculateTotal(){


        return 0;
    }

    public void setUpLayoutParams(int groupCount){
        addItemButtonParams = new RelativeLayout.LayoutParams(
                250, 90
        );
        addItemButtonParams.addRule(RelativeLayout.BELOW, quantityEditTextId +groupCount );
        addItemButtonParams.bottomMargin = 5;



        searchItemTextViewParams = new RelativeLayout.LayoutParams(
                500, 50
        );
        //searchItemTextViewParams.addRule(RelativeLayout.ALIGN_PARENT_START +groupCount);
        //searchItemTextViewParams.rightMargin = 52;



        itemNameEditTextParams = new RelativeLayout.LayoutParams(
                500, RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        itemNameEditTextParams.addRule(RelativeLayout.BELOW, searchTextViewId +groupCount);



        calculateButtonParams = new RelativeLayout.LayoutParams(
              250  , 90
        );
        calculateButtonParams.addRule(RelativeLayout.BELOW, quantityEditTextId+groupCount);
        calculateButtonParams.bottomMargin = 5;
        calculateButtonParams.addRule(RelativeLayout.END_OF, addItemsBtnId+groupCount);
        calculateButtonParams.leftMargin = 6;


        quantityTextViewParams = new RelativeLayout.LayoutParams(
                500, 50
        );
        quantityTextViewParams.addRule(RelativeLayout.BELOW, itemNameEDitTextId+groupCount);


        quantityEditTextParams = new RelativeLayout.LayoutParams(
                500, RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        quantityEditTextParams.addRule(RelativeLayout.BELOW, quantityTextViewId+groupCount);


        lineDividerParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, 1
        );
        lineDividerParams.addRule(RelativeLayout.BELOW, addItemsBtnId+groupCount);
        lineDividerParams.setMargins(0,5,0,5);
    }


}
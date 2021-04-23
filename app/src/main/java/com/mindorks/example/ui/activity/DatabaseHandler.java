package com.mindorks.example.ui.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "salesManager";
    private static final String TABLE_NAME = "values_table";
    private static final String KEY_ID = "id";
    private static final String KEY_ITEM_NAME = "Item_name";
    private static final String KEY_ITEM_PRICE = "item_price";
    private static final String KEY_QUANTITY = "item_quantity";
    private static final String TABLE_SALES = "sales";
    private static final String KEY_SALE_ITEM_NAME = "name";
    private static final String KEY_SALE_ITEM_QUANTITY = "quantity";
    private static final String KEY_SALE_WIDGETS_GROUP_COUNT = "quantity";
    private static final String KEY_EXPIRY_DATE = "expiry_date";




    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument passed is CursorFactory instance
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LAYOUTS_TABLE =  "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_ITEM_NAME + " TEXT,"
                + KEY_ITEM_PRICE + " TEXT,"
                + KEY_QUANTITY + " TEXT,"
                + KEY_EXPIRY_DATE + " TEXT" +")";
        db.execSQL(CREATE_LAYOUTS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addValues(String name, String quantity, String price, String expiry_date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_NAME, name);
        values.put(KEY_QUANTITY, quantity);
        values.put(KEY_ITEM_PRICE, price);
        values.put(KEY_EXPIRY_DATE, expiry_date);

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

   public void createSalesTable(SQLiteDatabase db){
        String CREATE_VALUES_TABLE =  "CREATE TABLE " + TABLE_SALES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SALE_ITEM_NAME + " TEXT,"
                + KEY_SALE_ITEM_QUANTITY + " TEXT"
                + KEY_SALE_WIDGETS_GROUP_COUNT
                + ")";
        db.execSQL(CREATE_VALUES_TABLE);
    }
    public void addSaleValues(String name, String quantity, String groupCount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SALE_ITEM_NAME, name);
        values.put(KEY_SALE_ITEM_QUANTITY, quantity);
        values.put(KEY_SALE_WIDGETS_GROUP_COUNT, groupCount);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // code to get the price of an item
     int getPrice(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ITEM_PRICE,},
                KEY_SALE_ITEM_NAME + "=?",
                new String[] { name }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        int price = Integer.parseInt(cursor.getString(0));

        // return contact
        return price;
    }


}

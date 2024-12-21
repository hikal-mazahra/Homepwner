package android.bignerdranch.majorprojectassignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ItemLab {
    private static ItemLab sItemLab;
    private List<Item> mItems;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    long time = System.currentTimeMillis();
    Date date = new Date(time);
    public static ItemLab get(Context context) {
        if (sItemLab == null) {
            sItemLab = new ItemLab(context);
        }
        return sItemLab;
    }
    private ItemLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ItemBaseHelper(mContext)
                .getWritableDatabase();
//        mItems = new ArrayList<>();
        Item Rusty = new Item();
        Rusty.setValue(94);
        Rusty.setName("Rusty Bear");
        Rusty.setSerial(Rusty.generateSerial());
        Rusty.setmDate(date);
        mItems.add(Rusty);


        Item Fluffy = new Item();
        Fluffy.setValue(59);
        Fluffy.setName("Fluffy Bear");
        Fluffy.setSerial(Fluffy.generateSerial());
        Fluffy.setmDate(date);
        mItems.add(Fluffy);

        Item Spork = new Item();
        Spork.setValue(66);
        Spork.setName("Fluffy Spork");
        Spork.setSerial(Spork.generateSerial());
        Spork.setmDate(date);
        mItems.add(Spork);


        Item Mac = new Item();
        Mac.setValue(72);
        Mac.setName("Shiny Mac");
        Mac.setSerial(Mac.generateSerial());
        Mac.setmDate(date);
        mItems.add(Mac);


        Item Fluffy2 = new Item();
        Fluffy2.setValue(40);
        Fluffy2.setName("Fluffy Bear");
        Fluffy2.setSerial(Fluffy2.generateSerial());
        Fluffy2.setmDate(date);
        mItems.add(Fluffy2);
    }
    public void addItem (Item i) {
     //   mItems.add(i);
        ContentValues values = getContentValues(i);
        mDatabase.insert(ItemDbSchema.ItemTable.NAME, null, values);
    }
    public List<Item> getmItems() {
       // return mItems;
        return new ArrayList<>();
    }

    public Item getItem(UUID id) {
     /*   for (Item item : mItems) {
            if (item.getId().equals(id)) {
                return item;
            }
        }*/
        ItemCursorWrapper cursor = queryItems(
                ItemDbSchema.ItemTable.Cols.UUID + " = ?", new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getItem();
        } finally {
            cursor.close();
        }
    }
    public List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        ItemCursorWrapper cursor = queryItems(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items.add(cursor.getItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }
    public void updateItem(Item item){
        String uuidString = item.getId().toString();
        ContentValues values = getContentValues(item);
        mDatabase.update(ItemDbSchema.ItemTable.NAME, values, ItemDbSchema.ItemTable.Cols.UUID + " = ?", new String[] { uuidString });

    }

    private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(ItemDbSchema.ItemTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new ItemCursorWrapper(cursor);
    }




    private static ContentValues getContentValues(Item item){
        ContentValues values = new ContentValues();
        values.put(ItemDbSchema.ItemTable.Cols.UUID, item.getId().toString());
        values.put(ItemDbSchema.ItemTable.Cols.ITEMNAME, item.getName());
        values.put(ItemDbSchema.ItemTable.Cols.SERIAL, item.getmDate().getTime());
        values.put(ItemDbSchema.ItemTable.Cols.VALUE, String.valueOf(item.getValue()));
        return values;
    }
}
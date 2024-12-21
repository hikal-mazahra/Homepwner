package android.bignerdranch.majorprojectassignment1;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class ItemCursorWrapper extends CursorWrapper {
    public ItemCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Item getItem(){
        String uuidString = getString(getColumnIndex(ItemDbSchema.ItemTable.Cols.UUID));
        String itemName = getString(getColumnIndex(ItemDbSchema.ItemTable.Cols.ITEMNAME));
        long date = getLong(getColumnIndex(ItemDbSchema.ItemTable.Cols.DATE));
        double value = getDouble(getColumnIndex(ItemDbSchema.ItemTable.Cols.VALUE));
        String serial = getString(getColumnIndex(ItemDbSchema.ItemTable.Cols.SERIAL));
        Item item = new Item(UUID.fromString(uuidString));
        item.setName(itemName);
        item.setmDate(new Date(date));
        item.setSerial(serial);
        item.setValue(value);

        return item;
    }
}

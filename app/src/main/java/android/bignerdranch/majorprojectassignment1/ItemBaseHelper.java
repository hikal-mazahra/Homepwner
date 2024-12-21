package android.bignerdranch.majorprojectassignment1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "itemBase.db";
    public ItemBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + ItemDbSchema.ItemTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ItemDbSchema.ItemTable.Cols.UUID + ", " +
                ItemDbSchema.ItemTable.Cols.ITEMNAME + ", " +
                ItemDbSchema.ItemTable.Cols.DATE + ", " +
                ItemDbSchema.ItemTable.Cols.VALUE + ", " +
                ItemDbSchema.ItemTable.Cols.SERIAL + ", " +
                ItemDbSchema.ItemTable.Cols.OWNER +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}

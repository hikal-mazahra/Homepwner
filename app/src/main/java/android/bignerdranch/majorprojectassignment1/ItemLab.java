package android.bignerdranch.majorprojectassignment1;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ItemLab {
    private static ItemLab sItemLab;
    private List<Item> mItems;
    long time = System.currentTimeMillis();
    Date date = new Date(time);
    public static ItemLab get(Context context) {
        if (sItemLab == null) {
            sItemLab = new ItemLab(context);
        }
        return sItemLab;
    }
    private ItemLab(Context context) {
        mItems = new ArrayList<>();
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

    public List<Item> getmItems() {
        return mItems;
    }

    public Item getItem(UUID id) {
        for (Item item : mItems) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
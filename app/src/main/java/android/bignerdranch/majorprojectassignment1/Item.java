package android.bignerdranch.majorprojectassignment1;

import java.util.Date;

public class Item
{
    private String name;
    private int Serial;
    private double Value;
    private Date mDate;

    public Item()
    {
        mDate = new Date();
    }

    //getters and setters for fields
    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public int getSerial() {
        return Serial;
    }

    public void setSerial(int serial) {
        Serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

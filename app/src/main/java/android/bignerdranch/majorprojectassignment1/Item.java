package android.bignerdranch.majorprojectassignment1;

import java.util.Date;
import java.util.Random;

public class Item
{
    private String name;
    private String Serial;
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

    public String getSerial() {
        return Serial;
    }

    public String generateSerial() {
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

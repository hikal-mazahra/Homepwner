package android.bignerdranch.majorprojectassignment1;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Item
{
    private UUID mId;

    private String name;
    private String Serial;
    private double Value;
    private Date mDate;

    public Item()
    {
        mId = UUID.randomUUID();

        mDate = new Date();
    }

    //getters and setters for fields
    public double getValue() {
        return Value;
    }
    public UUID getId() {
        return mId;
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
    public void setSerial(String serial) {
        this.Serial = serial;
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

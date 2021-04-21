package fr.rader.bane.nbt.tags;

import fr.rader.bane.utils.DataWriter;

public class TagIntArray extends TagBase {

    public static final byte TAG_ID = 11;

    private int[] value;

    public TagIntArray(int[] value) {
        setID(TAG_ID);
        setValue(value);
    }

    public TagIntArray(String name, int[] value) {
        setID(TAG_ID);
        setName(name);
        setValue(value);
    }

    public int[] getValue() {
        return this.value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    @Override
    public void write(DataWriter writer) {
        if(getName() != null) {
            writer.writeByte(TAG_ID);
            writer.writeShort(getName().length());
            writer.writeString(getName());
        }

        writer.writeInt(value.length);
        writer.writeIntArray(value);
    }
}

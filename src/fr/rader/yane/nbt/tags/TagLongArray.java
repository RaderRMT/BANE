package fr.rader.yane.nbt.tags;

import fr.rader.yane.utils.DataWriter;

public class TagLongArray extends TagBase {

    public static final byte TAG_ID = 12;

    private long[] value;

    public TagLongArray(long[] value) {
        setID(TAG_ID);
        setValue(value);
    }

    public TagLongArray(String name, long[] value) {
        setID(TAG_ID);
        setName(name);
        setValue(value);
    }

    public long[] getValue() {
        return this.value;
    }

    public void setValue(long[] value) {
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
        writer.writeLongArray(value);
    }
}

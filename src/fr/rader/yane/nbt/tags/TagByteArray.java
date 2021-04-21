package fr.rader.yane.nbt.tags;

import fr.rader.yane.utils.DataWriter;

public class TagByteArray extends TagBase {

    public static final byte TAG_ID = 7;

    private byte[] value;

    public TagByteArray(byte[] value) {
        setID(TAG_ID);
        setValue(value);
    }

    public TagByteArray(String name, byte[] value) {
        setID(TAG_ID);
        setName(name);
        setValue(value);
    }

    public byte[] getValue() {
        return this.value;
    }

    public void setValue(byte[] value) {
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
        writer.writeByteArray(value);
    }
}

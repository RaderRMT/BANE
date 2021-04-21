package fr.rader.yane.nbt.tags;

import fr.rader.yane.utils.DataReader;
import fr.rader.yane.utils.DataWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagCompound extends TagBase {

    public static final byte TAG_ID = 10;

    private final List<TagBase> tags;

    public TagCompound() {
        setID(TAG_ID);

        this.tags = new ArrayList<>();
    }

    public TagCompound(String name) {
        setID(TAG_ID);
        setName(name);

        this.tags = new ArrayList<>();
    }

    public TagCompound(String name, DataReader reader) {
        setID(TAG_ID);
        setName(name);

        this.tags = new ArrayList<>();

        try {
            readCompound(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCompound(DataReader reader) throws IOException {
        while(true) {
            int tagID = reader.readByte();
            if(tagID == 0) return;

            String tagName = reader.readString(reader.readShort());

            switch(tagID) {
                case 1:
                    add(new TagByte(tagName, reader.readByte()));
                    break;
                case 2:
                    add(new TagShort(tagName, reader.readShort()));
                    break;
                case 3:
                    add(new TagInt(tagName, reader.readInt()));
                    break;
                case 4:
                    add(new TagLong(tagName, reader.readLong()));
                    break;
                case 5:
                    add(new TagFloat(tagName, reader.readFloat()));
                    break;
                case 6:
                    add(new TagDouble(tagName, reader.readDouble()));
                    break;
                case 7:
                    add(new TagByteArray(tagName, reader.readFollowingBytes(reader.readInt())));
                    break;
                case 8:
                    add(new TagString(tagName, reader.readString(reader.readShort())));
                    break;
                case 9:
                    add(new TagList<>(tagName, reader));
                    break;
                case 10:
                    add(new TagCompound(tagName, reader));
                    break;
                case 11:
                    add(new TagIntArray(tagName, reader.readIntArray(reader.readInt())));
                    break;
                case 12:
                    add(new TagLongArray(tagName, reader.readLongArray(reader.readInt())));
                    break;
                default:
                    throw new IllegalStateException("Unexpected tag: " + Integer.toHexString(tagID));
            }
        }
    }

    @Override
    public void write(DataWriter writer) {
        if(getName() != null) {
            writer.writeByte(TAG_ID);
            writer.writeShort(getName().length());
            writer.writeString(getName());
        }

        for(TagBase tag : tags) {
            tag.write(writer);
        }

        writer.writeByte(0);
    }

    public void add(TagBase nbt) {
        if(nbt.getName() == null) throw new IllegalArgumentException("NBT Tag must have a name");

        tags.add(nbt);
    }

    public void remove(int index) {
        tags.remove(index);
    }

    public void remove(TagBase nbt) {
        tags.remove(nbt);
    }

    public void replace(int index, TagBase nbt) {
        if(nbt.getName() == null) throw new IllegalArgumentException("NBT Tag must have a name");

        tags.set(index, nbt);
    }

    public TagBase get(int index) {
        return tags.get(index);
    }

    public TagBase get(String name) {
        for(TagBase tag : tags) {
            if(tag.getName().equals(name)) return tag;
        }

        return null;
    }

    public List<TagBase> getTags() {
        return tags;
    }
}

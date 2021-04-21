package fr.rader.yane.nbt.tags;

import fr.rader.yane.utils.DataWriter;

public abstract class TagBase {

    private byte tagID;

    public void setID(byte id) {
        this.tagID = id;
    }

    public byte getTagID() {
        return tagID;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void write(DataWriter writer);

    public TagByte getAsTagByte() {
        if(!(this instanceof TagByte))
            throw new ClassCastException("Cannot cast to TagByte, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagByte) this;
    }

    public TagShort getAsTagShort() {
        if(!(this instanceof TagShort))
            throw new ClassCastException("Cannot cast to TagShort, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagShort) this;
    }

    public TagInt getAsTagInt() {
        if(!(this instanceof TagInt))
            throw new ClassCastException("Cannot cast to TagInt, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagInt) this;
    }

    public TagLong getAsTagLong() {
        if(!(this instanceof TagLong))
            throw new ClassCastException("Cannot cast to TagLong, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagLong) this;
    }

    public TagFloat getAsTagFloat() {
        if(!(this instanceof TagFloat))
            throw new ClassCastException("Cannot cast to TagFloat, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagFloat) this;
    }

    public TagDouble getAsTagDouble() {
        if(!(this instanceof TagDouble))
            throw new ClassCastException("Cannot cast to TagDouble, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagDouble) this;
    }

    public TagByteArray getAsTagByteArray() {
        if(!(this instanceof TagByteArray))
            throw new ClassCastException("Cannot cast to TagByteArray, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagByteArray) this;
    }

    public TagString getAsTagString() {
        if(!(this instanceof TagString))
            throw new ClassCastException("Cannot cast to TagString, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagString) this;
    }

    public TagCompound getAsTagCompound() {
        if(!(this instanceof TagCompound))
            throw new ClassCastException("Cannot cast to TagCompound, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagCompound) this;
    }

    public TagIntArray getAsTagIntArray() {
        if(!(this instanceof TagIntArray))
            throw new ClassCastException("Cannot cast to TagIntArray, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagIntArray) this;
    }

    public TagLongArray getAsTagLongArray() {
        if(!(this instanceof TagLongArray))
            throw new ClassCastException("Cannot cast to TagLongArray, please use #getAs" + this.getClass().getSimpleName() + "() instead.");

        return (TagLongArray) this;
    }

    public int getAsByte() {
        return getAsTagByte().getValue();
    }

    public int getAsShort() {
        return getAsTagShort().getValue();
    }

    public int getAsInt() {
        return getAsTagInt().getValue();
    }

    public long getAsLong() {
        return getAsTagLong().getValue();
    }

    public float getAsFloat() {
        return getAsTagFloat().getValue();
    }

    public double getAsDouble() {
        return getAsTagDouble().getValue();
    }

    public byte[] getAsByteArray() {
        return getAsTagByteArray().getValue();
    }

    public String getAsString() {
        return getAsTagString().getValue();
    }

    public int[] getAsIntArray() {
        return getAsTagIntArray().getValue();
    }

    public long[] getAsLongArray() {
        return getAsTagLongArray().getValue();
    }

    public TagList<TagByte> getAsByteList() {
        @SuppressWarnings("unchecked")
        TagList<TagByte> list = ((TagList<TagByte>) this);

        if(list.getChildrenID() != TagByte.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagByte> because the childen ID does not match the TagByte ID");

        return list;
    }

    public TagList<TagShort> getAsShortList() {
        @SuppressWarnings("unchecked")
        TagList<TagShort> list = ((TagList<TagShort>) this);

        if(list.getChildrenID() != TagShort.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagShort> because the childen ID does not match the TagShort ID");

        return list;
    }

    public TagList<TagInt> getAsIntList() {
        @SuppressWarnings("unchecked")
        TagList<TagInt> list = ((TagList<TagInt>) this);

        if(list.getChildrenID() != TagInt.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagInt> because the childen ID does not match the TagInt ID");

        return list;
    }

    public TagList<TagLong> getAsLongList() {
        @SuppressWarnings("unchecked")
        TagList<TagLong> list = ((TagList<TagLong>) this);

        if(list.getChildrenID() != TagLong.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagLong> because the childen ID does not match the TagLong ID");

        return list;
    }

    public TagList<TagFloat> getAsFloatList() {
        @SuppressWarnings("unchecked")
        TagList<TagFloat> list = ((TagList<TagFloat>) this);

        if(list.getChildrenID() != TagFloat.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagFloat> because the childen ID does not match the TagFloat ID");

        return list;
    }

    public TagList<TagDouble> getAsDoubleList() {
        @SuppressWarnings("unchecked")
        TagList<TagDouble> list = ((TagList<TagDouble>) this);

        if(list.getChildrenID() != TagDouble.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagDouble> because the childen ID does not match the TagDouble ID");

        return list;
    }

    public TagList<TagByteArray> getAsByteArrayList() {
        @SuppressWarnings("unchecked")
        TagList<TagByteArray> list = ((TagList<TagByteArray>) this);

        if(list.getChildrenID() != TagByteArray.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagByteArray> because the childen ID does not match the TagByteArray ID");

        return list;
    }

    public TagList<TagString> getAsStringList() {
        @SuppressWarnings("unchecked")
        TagList<TagString> list = ((TagList<TagString>) this);

        if(list.getChildrenID() != TagString.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagString> because the childen ID does not match the TagString ID");

        return list;
    }

    public TagList<TagList<?>> getAsListList() {
        @SuppressWarnings("unchecked")
        TagList<TagList<?>> list = ((TagList<TagList<?>>) this);

        if(list.getChildrenID() != TagList.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagList<?>> because the childen ID does not match the TagList ID");

        return list;
    }

    public TagList<TagCompound> getAsCompoundList() {
        @SuppressWarnings("unchecked")
        TagList<TagCompound> list = ((TagList<TagCompound>) this);

        if(list.getChildrenID() != TagCompound.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagCompound> because the childen ID does not match the TagCompound ID");

        return list;
    }

    public TagList<TagIntArray> getAsIntArrayList() {
        @SuppressWarnings("unchecked")
        TagList<TagIntArray> list = ((TagList<TagIntArray>) this);

        if(list.getChildrenID() != TagIntArray.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagIntArray> because the childen ID does not match the TagIntArray ID");

        return list;
    }

    public TagList<TagLongArray> getAsLongArrayList() {
        @SuppressWarnings("unchecked")
        TagList<TagLongArray> list = ((TagList<TagLongArray>) this);

        if(list.getChildrenID() != TagLongArray.TAG_ID)
            throw new RuntimeException("Cannot cast to TagList<TagLongArray> because the childen ID does not match the TagLongArray ID");

        return list;
    }
}

package fr.rader.yane;

import fr.rader.yane.nbt.tags.*;
import fr.rader.yane.utils.DataWriter;

public class Main {

    public void start() {
        // We create a List of bytes, and add 3 bytes
        TagList<TagByte> bytes = new TagList<>(TagByte.class, "bytes_list");
        bytes.add(new TagByte(1));
        bytes.add(new TagByte(2));
        bytes.add(new TagByte(3));

        // We create a Compound, and add the previously created list and a String
        TagCompound compound = new TagCompound("main_compound");
        compound.add(bytes);
        compound.add(new TagString("Hello", "World!"));

        // We create a DataWriter, so we can get the NBT as a byte array
        DataWriter writer = new DataWriter();
        // we write the compount to the DataWriter
        compound.write(writer);

        // At this point, we can use DataWriter#getData() to get the NBT as a byte array
        // Just gonna print the NBT with a simple for loop
        for(byte value : writer.getData()) {
            System.out.print(String.format("%02X", value) + " ");
        }

        // We should not forget to close the writer's stream
        writer.close();
    }

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        start();
    }
}

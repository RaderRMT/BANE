package fr.rader.bane;

import fr.rader.bane.nbt.editor.NBTEditor;
import fr.rader.bane.nbt.tags.*;
import fr.rader.bane.utils.DataWriter;

import java.util.Arrays;

public class Main {

    public void start() {
        // todo:
        //  create a nbt reader & writer, to handle writing and reading files easier

        // We create a List of bytes, and add 3 bytes
        /*TagList<TagByte> bytes = new TagList<>(TagByte.class, "bytes_list");
        bytes.add(new TagByte(1));
        bytes.add(new TagByte(2));
        bytes.add(new TagByte(3));

        // We create a Compound, and add the previously created list and a String
        TagCompound compound = new TagCompound("main_compound");
        compound.add(bytes);
        compound.add(new TagString("Hello", "World!"));

        TagByteArray test = new TagByteArray("test", new byte[] { 1, 2, 3, 4, 5 });

        compound.add(test);*/

        // We create a DataWriter, so we can get the NBT as a byte array
        /*DataWriter writer = new DataWriter();
        // we write the compound to the DataWriter
        compound.write(writer);

        // At this point, we can use DataWriter#getData() to get the NBT as a byte array
        // Just gonna print the NBT with a simple for loop
        for(byte value : writer.getData()) {
            System.out.print(String.format("%02X", value) + " ");
        }

        // We should not forget to close the writer's stream
        writer.close();*/

        /*NBTEditor editor = new NBTEditor();
        editor.loadNBT(compound);
        editor.invokeEditor();*/

        /*TagByteArray test = new TagByteArray("test");
        test.setValue(null);

        test.add((byte) 1);
        test.add((byte) 1);

        test.replace(2, (byte) 1);*/

        /*TagShort test = new TagShort("test", 15);
        test.getAsUnknownList();*/
    }

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        start();
    }
}

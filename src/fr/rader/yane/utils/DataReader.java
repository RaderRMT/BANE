package fr.rader.yane.utils;

import fr.rader.yane.nbt.tags.TagCompound;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DataReader {

    private InputStream inputStream;

    public DataReader(InputStream inputStream) {
        if(inputStream == null) throw new IllegalArgumentException("InputStream must not be null");

        this.inputStream = inputStream;
    }

    /**
     * Read a file
     * @param file File to read
     */
    public DataReader(File file) {
        if(file == null) throw new IllegalArgumentException("file must not be null");

        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DataReader(byte[] data) {
        if(data == null) throw new IllegalArgumentException("data must not be null");

        inputStream = new ByteArrayInputStream(data);
    }

    /**
     * Close the InputStream after reading the file
     */
    public void close() {
        try {
            if(inputStream != null) inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read a byte
     * @return Byte read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public int readByte() throws IOException {
        if(inputStream.available() == 0) throw new EOFException("Reached end of file!");
        return inputStream.read() & 0xff;
    }

    /**
     * Read a short (2 bytes)
     * @return Short read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public int readShort() throws IOException {
        return (readByte() << 8 | readByte()) & 0xffff;
    }

    /**
     * Read an integer (4 bytes)
     * @return Integer read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public int readInt() throws IOException {
        return readShort() << 16 | readShort();
    }

    /**
     * Read a long (8 bytes)
     * @return Long read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public long readLong() throws IOException {
        return (long) readInt() << 32 | readInt();
    }

    /**
     * Read a float (4 bytes)
     * @return Float read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public float readFloat() throws IOException {
        return ByteBuffer.wrap(readFollowingBytes(4)).order(ByteOrder.BIG_ENDIAN).getFloat();
    }

    /**
     * Read a double (8 bytes)
     * @return Double read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public double readDouble() throws IOException {
        return ByteBuffer.wrap(readFollowingBytes(8)).order(ByteOrder.BIG_ENDIAN).getDouble();
    }

    /**
     * Read a boolean (1 byte)
     * @return Boolean read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public boolean readBoolean() throws IOException {
        return (readByte() & 0x01) == 1;
    }

    /**
     * Read a character (1 byte)
     * @return Character read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public char readChar() throws IOException {
        return (char) readByte();
    }

    /**
     * Read the following <code>length</code> bytes to a byte array
     * @param length Number of bytes to read
     * @return A byte array of size length
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public byte[] readFollowingBytes(int length) throws IOException {
        byte[] out = new byte[length];

        for(int i = 0; i < length; i++) {
            out[i] = (byte) readByte();
        }

        return out;
    }

    /**
     * Read a VarInt (1 - 5 bytes)
     * @return VarInt read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public int readVarInt() throws IOException {
        int numRead = 0;
        int result = 0;
        int read;

        do {
            read = readByte();
            int value = (read & 0x7f);
            result |= value << (7 * numRead);

            numRead++;
            if(numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0x80) != 0);

        return result;
    }

    /**
     * Read a VarLong (1 - 10 bytes)
     * @return VarLong read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public long readVarLong() throws IOException {
        int numRead = 0;
        long result = 0;
        int read;

        do {
            read = readByte();
            long value = (read & 0x7f);
            result |= (value << (7 * numRead));

            numRead++;
            if(numRead > 10) {
                throw new RuntimeException("VarLong is too big");
            }
        } while ((read & 0x80) != 0);

        return result;
    }

    /**
     * Read a string (length is determined by the <code>length</code> param)
     * @param length Length of string to read
     * @return String read
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public String readString(int length) throws IOException {
        String out = "";

        while(length-- > 0) {
            out += readChar();
        }

        return out;
    }

    /**
     * Read a int array (length is determined by the <code>length</code> param)
     * @param length Length of the array to read
     * @return Int array
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public int[] readIntArray(int length) throws IOException {
        int[] out = new int[length];

        for(int i = 0; i < length; i++) {
            out[i] = readInt();
        }

        return out;
    }

    /**
     * Read a long array (length is determined by the <code>length</code> param)
     * @param length Length of the array to read
     * @return Long array
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public long[] readLongArray(int length) throws IOException {
        long[] out = new long[length];

        for(int i = 0; i < length; i++) {
            out[i] = readLong();
        }

        return out;
    }

    /**
     * Read a NBT Compound
     * @return NBTCompound
     * @exception IOException when an I/O error occurs.
     * @exception EOFException when the end of file has been reached.
     */
    public TagCompound readNBT() throws IOException {
        byte firstByte = (byte) readByte();
        if(firstByte == 0) return null;

        return new TagCompound(readString(readShort()), this);
    }

    public int getLength() {
        try {
            return inputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void skip(int i) {
        try {
            inputStream.skip(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

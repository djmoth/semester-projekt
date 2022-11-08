import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * A serialized array of drawing instructions
 */
public class InstructionList
{
    /**
     * Instructions serialized in a binary format
     */
    private byte[] instructions = new byte[1024];
    /**
     * Number of bytes written to instructions array
     */
    private int length;

    private int pathPointsIndex;
    private int pathPointCount;

    public void newPath ()
    {
        pathPointsIndex = length;
        pathPointCount = 0;

        checkCapacity (2);

        length += 2;

        System.out.println ("New path");
    }

    public void endPath ()
    {
        System.out.println ("End of path");

        writeInt32AsUInt16 (pathPointsIndex, pathPointCount);
    }

    /**
     * Add an instruction, in draw-space
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     */
    public void add (int x, int y)
    {
        System.out.println (x + ", " + y);

        checkCapacity (4);

        // Write x & y
        writeInt32AsUInt16 (length, x);
        writeInt32AsUInt16 (length + 2, y);
        length += 4;

        pathPointCount++;
    }

    /**
     * Write the serialized instructions to a stream
     * @param stream Stream to write to
     * @throws IOException Thrown by DataOutputStream
     */
    public void writeToStream (DataOutputStream stream) throws IOException
    {
        System.out.println ("Writing " + length + " bytes to stream...");
        stream.write (instructions, 0, length);
    }

    /**
     * Write a short to an index in the instructions array
     * @param index Index in the instructions array
     * @param val signed 32-bit integer value to write as an unsigned 16-bit integer
     */
    private void writeInt32AsUInt16 (int index, int val)
    {
        instructions[index] = (byte)(val & 0xFF);
        instructions[index + 1] = (byte)((val >> 8) & 0xFF);
    }

    private void checkCapacity (int newData)
    {
        // Resize instructions array if there is not enough space
        if (length + newData > instructions.length)
        {
            // Double the size of the array
            instructions = Arrays.copyOf (instructions, instructions.length * 2);
        }
    }
}

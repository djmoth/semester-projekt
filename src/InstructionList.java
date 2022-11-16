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

    /**
     * Index in the instructions array of where the pathPointCount of the current path is stored
     */
    private int pathPointsIndex;
    /**
     * Number of points in the current path
     */
    private int pathPointCount;

    public void newPath ()
    {
        // Remember at which index this path starts at
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
     * Write an int as a 16-bit unsigned integer to an index in the instructions array.
     * The value is truncated if its value is greater than 65535 or less than 0.
     * The method does not check for overflow.
     * @param index Index in the instructions array
     * @param val signed 32-bit integer value to write as an unsigned 16-bit integer
     */
    private void writeInt32AsUInt16 (int index, int val)
    {
        instructions[index] = (byte)(val & 0xFF);
        instructions[index + 1] = (byte)((val >> 8) & 0xFF);
    }

    /**
     * Check if the instructions array has enough space for newData bytes.
     * If not, the instructions array is doubled in size.
     * @param newData
     */
    private void checkCapacity (int newData)
    {
        // Resize instructions array if there is not enough space
        if (length + newData > instructions.length)
        {
            // Calculate the new size, which is either double the current size
            // or the current size + newData, whichever is greatest.
            int newSize = Math.max (instructions.length * 2, instructions.length + newData);
            // Resize of the array by making a copy with a length of newSize
            instructions = Arrays.copyOf (instructions, newSize);
        }
    }
}

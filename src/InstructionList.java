import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class InstructionList
{
    private byte[] instructions = new byte[1024];
    private int length;

    public void add (int x, int y, boolean draw)
    {
        System.out.println ("(" + x + ", " + y + ")" + draw);

        if (length + 5 > instructions.length)
        {
            instructions = Arrays.copyOf (instructions, instructions.length * 2);
        }

        writeShort (length, (short)x);
        writeShort (length + 2, (short)y);
        instructions[length + 4] = (byte)(draw ? 1 : 0);
        length += 5;
    }

    public void writeToStream (DataOutputStream stream) throws IOException
    {
        stream.write (instructions, 0, length);
    }

    private void writeShort (int index, short val)
    {
        instructions[index] = (byte)(val & 0xFF);
        instructions[index + 1] = (byte)((val >> 8) & 0xFF);
    }
}

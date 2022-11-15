import org.apache.batik.parser.PathParser;

import java.io.*;

import org.xml.sax.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * The program reads an SVG image passed as an argument and finds all paths (lines) defined in the file.
 * Points on the paths are serialized in a binary format. It establishes a connection to the PLC, and
 * sends the serialized points over a network connection.
 */
public class Main
{
    /**
     *
     * @param args hostname, port, svg
     */
    public static void main (String[] args)
    {
        InstructionList instructions = new InstructionList ();

        SAXParserFactory saxFactory = SAXParserFactory.newInstance ();

        // Try to read the svg file via SAX (Simple API for XML)
        try
        {
            SAXParser parser = saxFactory.newSAXParser ();
            SvgHandlerSAX handler = new SvgHandlerSAX (instructions);

            // Combine last command-line arguments into a single string using StringBuilder
            StringBuilder filePath = new StringBuilder ();

            for (int i = 2; i < args.length; i++)
            {
                filePath.append (args[i]);

                if (i < args.length - 1)
                    filePath.append (" ");
            }

            // Parse the file
            parser.parse (filePath.toString (), handler);
        } catch (ParserConfigurationException | SAXException | IOException ex)
        {
            ex.printStackTrace ();
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt (args[1]);

        RobotClient client = new RobotClient (hostname, port);

        // Try to connect to the PLC
        if (!client.connect ())
        {
            System.out.println ("Failed to connect");
            return;
        }

        // Try to upload the instructions
        try
        {
            client.uploadInstructions (instructions);
        } catch (IOException ex)
        {
            System.out.println ("Error uploading instructions: " + ex.getMessage ());
            ex.printStackTrace ();
        }

        client.disconnect ();
    }
}
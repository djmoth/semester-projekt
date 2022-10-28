import org.apache.batik.parser.PathParser;

import java.io.*;
import org.xml.sax.*;

import org.apache.batik.parser.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class Main
{
    public static void main (String[] args)
    {
        InstructionList instructions = new InstructionList ();

        SAXParserFactory saxFactory = SAXParserFactory.newInstance ();

        try
        {
            SAXParser parser = saxFactory.newSAXParser ();
            SvgHandlerSAX handler = new SvgHandlerSAX (instructions);
            parser.parse (args[2], handler);
        } catch (ParserConfigurationException | SAXException | IOException ex)
        {
            ex.printStackTrace ();
        }

        String hostname = args[0];
        int port = Integer.parseInt (args[1]);

        RobotClient client = new RobotClient (hostname, port);

        if (!client.connect ())
        {
            return;
        }

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
import org.apache.batik.parser.PathParser;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Finds path elements in the XML structure of an SVG file.
 */
public class SvgHandlerSAX extends DefaultHandler
{
    private int width;
    private int height;
    private PathParser pathParser;
    private LineHandler lineParser;

    public SvgHandlerSAX (InstructionList instructions)
    {
        pathParser = new PathParser ();
        lineParser = new LineHandler (instructions);

        pathParser.setPathHandler (lineParser);
    }

    public int getWidth ()
    {
        return width;
    }

    public int getHeight ()
    {
        return height;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes)
    {
        if (qName.equalsIgnoreCase ("svg"))
        {
            String widthString = attributes.getValue ("width");
            width = parseDimension (widthString);

            String heightString = attributes.getValue ("height");
            height = parseDimension (heightString);
        } else if (qName.equalsIgnoreCase ("path"))
        {
            String pathShape = attributes.getValue ("d");

            pathParser.parse (pathShape);
        }
    }

    private int parseDimension (String value)
    {
        int unitIndex = value.indexOf ("mm");

        String dimensionString = value.substring (0, unitIndex);
        return Integer.parseInt (dimensionString);
    }
}

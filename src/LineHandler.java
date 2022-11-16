import org.apache.batik.parser.*;

/**
 * Handles straight lines passed by a PathParser
 */
public class LineHandler implements PathHandler
{
    private static final float stepsPerMM = 25;
    /**
     * Start drawing 500mm from origin as to not draw inside the sharpener
     */
    private static final float xDrawOffset = 50;

    private InstructionList instructions;

    private float firstX, firstY;
    private boolean hasFirstPoint;
    private float currentX, currentY;

    public LineHandler (InstructionList instructions)
    {
        this.instructions = instructions;
    }

    private void addPoint (float x, float y)
    {
        currentX = x;
        currentY = y;

        if (!hasFirstPoint)
        {
            hasFirstPoint = true;
            firstX = x;
            firstY = y;
        }

        instructions.add ((int)(x * stepsPerMM + xDrawOffset), (int)(y * stepsPerMM));
    }

    /**
     * Start of a new path
     * @param x
     * @param y
     * @throws ParseException
     */
    @Override
    public void linetoAbs (float x, float y) throws ParseException
    {
        addPoint (x, y);
    }

    /**
     * Point on path
     * @param x
     * @param y
     * @throws ParseException
     */
    @Override
    public void movetoAbs (float x, float y) throws ParseException
    {
        addPoint (x, y);
    }

    @Override
    public void startPath () throws ParseException
    {
        currentX = 0;
        currentY = 0;
        hasFirstPoint = false;

        instructions.newPath ();
    }

    @Override
    public void endPath () throws ParseException
    {
        instructions.endPath ();
    }

    @Override
    public void movetoRel (float dx, float dy) throws ParseException
    {
        addPoint (currentX + dx, currentY + dy);
    }

    @Override
    public void closePath () throws ParseException
    {
        if (hasFirstPoint)
            addPoint (firstX, firstY);
    }

    @Override
    public void linetoRel (float dx, float dy) throws ParseException
    {
        addPoint (currentX + dx, currentY + dy);
    }

    @Override
    public void linetoHorizontalRel (float dx) throws ParseException
    {
        addPoint (currentX + dx, currentY);
    }

    @Override
    public void linetoHorizontalAbs (float x) throws ParseException
    {
        addPoint (x, currentY);
    }

    @Override
    public void linetoVerticalRel (float dy) throws ParseException
    {
        addPoint (currentX, currentY + dy);
    }

    @Override
    public void linetoVerticalAbs (float y) throws ParseException
    {
        addPoint (currentX, y);
    }

    @Override
    public void curvetoCubicRel (float v, float v1, float v2, float v3, float v4, float v5) throws ParseException
    {

    }

    @Override
    public void curvetoCubicAbs (float v, float v1, float v2, float v3, float v4, float v5) throws ParseException
    {

    }

    @Override
    public void curvetoCubicSmoothRel (float v, float v1, float v2, float v3) throws ParseException
    {

    }

    @Override
    public void curvetoCubicSmoothAbs (float v, float v1, float v2, float v3) throws ParseException
    {

    }

    @Override
    public void curvetoQuadraticRel (float v, float v1, float v2, float v3) throws ParseException
    {

    }

    @Override
    public void curvetoQuadraticAbs (float v, float v1, float v2, float v3) throws ParseException
    {

    }

    @Override
    public void curvetoQuadraticSmoothRel (float v, float v1) throws ParseException
    {

    }

    @Override
    public void curvetoQuadraticSmoothAbs (float v, float v1) throws ParseException
    {

    }

    @Override
    public void arcRel (float v, float v1, float v2, boolean b, boolean b1, float v3, float v4) throws ParseException
    {

    }

    @Override
    public void arcAbs (float v, float v1, float v2, boolean b, boolean b1, float v3, float v4) throws ParseException
    {

    }
}

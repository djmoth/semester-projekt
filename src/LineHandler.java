import org.apache.batik.parser.*;

/**
 * Handles straight lines passed by a PathParser
 */
public class LineHandler implements PathHandler
{
    private static final float stepsPerMM = 50;

    private InstructionList instructions;
    
    private float currentX, currentY;

    public LineHandler (InstructionList instructions)
    {
        this.instructions = instructions;
    }

    private void addPoint (float x, float y)
    {
        instructions.add ((int)(x * stepsPerMM), (int)(y * stepsPerMM));
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
        currentX = x;
        currentY = y;
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
        currentX = x;
        currentY = y;
        addPoint (x, y);
    }

    @Override
    public void startPath () throws ParseException
    {
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
        currentX += dx;
        currentY += dy;

        addPoint (currentX, currentY);
    }



    @Override
    public void closePath () throws ParseException
    {

    }

    @Override
    public void linetoRel (float dx, float dy) throws ParseException
    {
        currentX += dx;
        currentY += dy;

        addPoint (currentX, currentY);
    }

    @Override
    public void linetoHorizontalRel (float dx) throws ParseException
    {
        currentX += dx;

        addPoint (currentX, currentY);
    }

    @Override
    public void linetoHorizontalAbs (float x) throws ParseException
    {
        currentX = x;

        addPoint (currentX, currentY);
    }

    @Override
    public void linetoVerticalRel (float dy) throws ParseException
    {
        currentY += dy;

        addPoint (currentX, currentY);
    }

    @Override
    public void linetoVerticalAbs (float y) throws ParseException
    {
        currentY = y;

        addPoint (currentX, currentY);
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

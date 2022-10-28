import org.apache.batik.parser.*;

public class LineParser implements PathHandler
{
    private static final float stepsPerMM = 50;

    private InstructionList instructions;

    public LineParser (InstructionList instructions)
    {
        this.instructions = instructions;
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
        instructions.add ((int)(x * stepsPerMM), (int)(y * stepsPerMM), false);
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
        instructions.add ((int)(x * stepsPerMM), (int)(y * stepsPerMM), true);
    }

    @Override
    public void startPath () throws ParseException
    {

    }

    @Override
    public void endPath () throws ParseException
    {

    }

    @Override
    public void movetoRel (float v, float v1) throws ParseException
    {

    }



    @Override
    public void closePath () throws ParseException
    {

    }

    @Override
    public void linetoRel (float v, float v1) throws ParseException
    {

    }

    @Override
    public void linetoHorizontalRel (float v) throws ParseException
    {

    }

    @Override
    public void linetoHorizontalAbs (float v) throws ParseException
    {

    }

    @Override
    public void linetoVerticalRel (float v) throws ParseException
    {

    }

    @Override
    public void linetoVerticalAbs (float v) throws ParseException
    {

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

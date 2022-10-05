public class Main
{
    public static void main (String[] args)
    {
        String hostname = args[0];
        int port = Integer.parseInt (args[1]);

        RobotClient client = new RobotClient (hostname, port);

        if (!client.connect ())
        {
            return;
        }



        client.disconnect ();
    }
}
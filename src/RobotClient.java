/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ancla
 */
public class RobotClient
{
    private String hostname;
    private int port;
    private Socket connection;
    private DataOutputStream out;

    /**
     *
     * @param hostname Hostname of the robot
     * @param port Port of the robot
     */
    public RobotClient (String hostname, int port)
    {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Method which connects to the robot, using the parameters provided to the constructor.
     */
    public boolean connect ()
    {
        try {
            connection = new Socket (hostname, port);
            out = new DataOutputStream (connection.getOutputStream ());

            return true;
        } catch (IOException ex) {
            Logger.getLogger (RobotClient.class.getName ()).log (Level.SEVERE, null, ex);
            System.out.println ("Error connecting to robot: " + ex.getMessage ());
            ex.printStackTrace ();

            return false;
        }
    }

    /**
     * This method is used to determine if a connection has been established to
     * the robot.
     *
     * @return Connection state to see if connection is established (true) or
     * not (false).
     */
    public boolean isConnected () {
        return connection.isConnected ();
    }

    public void uploadInstructions (InstructionList instructions) throws IOException
    {
        if (isConnected ()) {
            instructions.writeToStream (out);
            out.flush ();
        }
    }

    /**
     * Method to close connection to the robot.
     */
    public void disconnect ()
    {
        if (isConnected ()) {
            try {
                connection.close ();
            } catch (IOException ex) {
                Logger.getLogger (RobotClient.class.getName ()).log (Level.SEVERE, null, ex);
            }
        }
    }
}

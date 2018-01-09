package ExecutorsExamp;

import java.io.*;
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        int port = 8088;
        int limitPool = 4;
        Thread t = new Thread(new NetworkService(port, limitPool));
        t.start();
    }
}

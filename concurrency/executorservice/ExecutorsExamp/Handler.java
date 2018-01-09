package ExecutorsExamp;

import java.io.*;
import java.net.*;
import java.util.zip.*;

/**
 * Handler when one socket connected
 */
public class Handler implements Runnable {

    public static void GzipCompress(InputStream is,OutputStream os)
    throws Exception {

        GZIPOutputStream gos = new GZIPOutputStream(os);
        int count;
        byte data[] = new byte[1024];
        while( (count=is.read(data, 0, 1024)) != -1) {
            gos.write(data, 0, 1024);
        }
        gos.finish();
        gos.flush();
        gos.close();
    }

    protected final Socket socket;
    public Handler(Socket socket) {this.socket = socket;}

    /**
     * Just read data from socket and print back to client
     */
    public void run() {
        try{
            BufferedReader rIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

            String reqLine, reqHeader, reqContent;

            // Get the request method 
            String outContent = null;
            reqLine = rIn.readLine();
            if (reqLine!=null) {
                String[] reqArray = reqLine.split("\\s+");
                if(reqArray.length!=3) {
                    System.out.format("Error: `%s` is not avaible header", reqLine);
                    socket.close();
                    return;
                }

                switch(reqArray[0]){
                    case "GET":
                    {
                        String status, reason, contype;
                        status = "200";
                        reason = "OK";
                        contype = "text/html";
                        //output header
                        PrintWriter w = new PrintWriter(socket.getOutputStream(), true);
                        w.println(String.format("HTTP/1.1 %s %s\r\nContent-type:%s\r\nContent-Encoding:gzip\r\n\r\n",
                                status,reason,contype));

                        //output data
                        int count = -1;
                        byte[] data = new byte[1024];
                        BufferedInputStream ins = new BufferedInputStream(new FileInputStream(reqArray[1].substring(1)));
                        GZIPOutputStream gout = new GZIPOutputStream(socket.getOutputStream(), true);
                        while( (count=ins.read(data,0,1024))!=-1)
                        {
                            gout.write(data,0, count);
                        }
                        gout.finish();
                    }
                    break;
                    //case "POST":
                    //post();break;
                    case "HEAD":
                    outContent = head(reqArray[1].substring(1));break;
                    default:
                    break;
                }
            }

            out.println(outContent);
            socket.close();
        }
        catch (IOException ie)
        {
            // need be replaced by logger
            System.out.format("Exception caught when trying to listen on port %d "+
                "or listning for a connection", this.socket.getLocalPort());
            System.out.format("%s", ie.getMessage());
        }

    }

    /**
     * Get method
     */
    public String get(String uri){
        String status, reason, contype;

        if( false) {
            uri = "error403.html";
            status = "403";
            reason = "Resource has gone";
            contype = "text/html";
        }
        else {
            status = "200";
            reason = "OK";
            contype = "text/html";
        }

        //         try{
        //         ByteArrayOutputStream out = new ByteArrayOutputStream();
        //         Handler.GzipCompress(new FileInputStream(uri), out);
        //         
        //         String c = out.toString();
        //         out.flush();
        //         out.close();

        try(
        BufferedReader r = new BufferedReader(new FileReader(uri+".gz"));) {
            StringBuffer c = new StringBuffer();
            String lc = null;
            while( (lc=r.readLine())!=null)
            {
                c.append(lc);
            }
            System.out.println(uri);
            System.out.println(c);

            String header = String.format("HTTP/1.1 %s %s\r\nContent-type:%s\r\nContent-length:%d\r\nContent-Encoding:gzip\r\n",
                    status,reason,contype,c.length());

            return header + "\r\n" + c.toString();
        }
        catch (Exception ie)
        {
            return head(uri)+"\r\n"+"Error happend";
        }

    }

    /**
     * Post method
     */
    //public String post(){
    //}

    /**
     * Head method
     */
    public String head(String uri){
        return "HTTP/1.1 200 OK\r\n" + "Server:Welkin/0.1\r\n" + 
        "Content-Type:text/html:charse=utf8\r\n" +
        "Content-Language:en-US\r\n";
    }

}
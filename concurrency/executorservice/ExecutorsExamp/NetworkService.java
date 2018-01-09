package ExecutorsExamp;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 * Reference:ExecutorService class in Java API Document
 */

public class NetworkService implements Runnable {

    public static void shutdownAndWaitTermination(ExecutorService pool) {
        shutdownAndWaitTermination(pool, 60, TimeUnit.SECONDS);
    }

    public static void shutdownAndWaitTermination(ExecutorService pool, 
    Integer waitTime, TimeUnit unit) {
        pool.shutdown();
        try{
            if(!pool.awaitTermination(waitTime, unit)) {
                pool.shutdownNow();
                if(!pool.awaitTermination(waitTime, unit)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkService(int port, int poolSize) 
    throws IOException {
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run() {
        try{
            for(;;) {
                
                pool.execute(new Handler(serverSocket.accept()));
            }
        } catch (IOException ex) {
            NetworkService.shutdownAndWaitTermination(this.pool);
        }
    }

}
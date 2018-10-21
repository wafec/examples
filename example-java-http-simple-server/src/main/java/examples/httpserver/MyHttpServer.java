package examples.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class MyHttpServer {
    static int PORT = 8888;

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            server.createContext("/test", new MyHandler());
            server.setExecutor(null);
            System.out.println("## STARTING SERVER");
            server.start();
            System.out.println("## WAITING FOR INCOMING REQUESTS");
        } catch (IOException exception) {
            System.err.println(exception);
        }
    }

    public static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("## PATH: " + exchange.getRequestURI());
            String response = "This is the response\n";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}

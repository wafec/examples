package examples.httpserver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyHttpClient {
    public static void main(String[] args) {
        System.out.println("## SENDING BACK AN INCOMING REQUEST");
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://localhost:8888/test/4");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String data = "{\"name\":\"usuário\"}";
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(data);
            wr.close();
            System.out.println("## SENDING ...");
            // response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println("## RESPONSE");
            System.out.println(response);
        } catch (MalformedURLException exception) {
            System.err.println(exception);
        } catch (IOException exception) {
            System.err.println(exception);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}

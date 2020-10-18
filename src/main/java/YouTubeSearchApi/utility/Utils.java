package YouTubeSearchApi.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    /**
     * Get page content from given url.
     *
     * @param url
     * @return Page content in string
     * @throws IOException
     */
    public static String httpRequest(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();

        connection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        connection.connect();
        
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),
                Charset.forName("UTF-8"));

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();

        String line;

        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        bufferedReader.close();
        inputStreamReader.close();

        return stringBuilder.toString();
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

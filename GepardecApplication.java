package gepardecBewerbung;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GepardecApplication {
    public static void main(String[] args) throws IOException {
        //create a httpClient object
        HttpClient httpClient = HttpClientBuilder.create().build();
        //create a String from the Base64 file, else "String to long" error
        String csvBase64 = new String(Files.readAllBytes(Paths.get("C:/Users/teilnehmer/Desktop/csv.txt")));


        try{
            HttpPost request = new HttpPost("https://weckdengeparden-57-services.cloud.itandtel.at/challenge/1/answer");
            StringEntity params = new StringEntity("{\"firstName\": \"Andreas\",\n" +
                    "   \"lastName\": \"Mayer\",\n" +
                    "   \"email\": \"andreas.m4020@gmail.com\",\n" +
                    "   \"challengeId\": \"1\",\n" +
                    "   \"challengeAnswer\": \"50\",\n" +
                    "   \"phone\": \"06606806434\",\n" +
                    "   \"cv\": \""+csvBase64+"\",\n" +
                    "   \"messageToGepardec\": \"Ich hoffe das ich ins Rudel aufgenommen werde.\"}");
            request.addHeader("Content-Type", "application/json");
            request.setEntity(params);
            System.out.println(params);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response);
            System.out.println(response.getEntity().toString());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            System.out.println(result);

        }catch (Exception ex) {
            ex.printStackTrace();

        }finally {
            //deprecated
            httpClient.getConnectionManager().shutdown();
        }
    }

}

package gepardecBewerbung;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GepardecGet {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try{
            HttpGet request = new HttpGet("https://weckdengeparden-57-services.cloud.itandtel.at/challenge/1");

            //add request headers
            request.addHeader("application", "json");
            request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

            CloseableHttpResponse response = httpclient.execute(request);

            try{
                //Get HttpResponse Status
                System.out.println(response.getProtocolVersion());
                System.out.println(response.getStatusLine().getStatusCode());
                System.out.println(response.getStatusLine().getReasonPhrase());
                System.out.println(response.getStatusLine().toString());

                //create an entity
                HttpEntity entity = response.getEntity();
                if(entity != null) {
                    //return it as a string
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
            }finally {
                response.close();
            }
        }finally {
          httpclient.close();
        }
    }
}

package stu.napls;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomTest {
    private static final String SUBMIT_URL = "http://localhost:9000/test/submit";

    private static final int TEST_NUM = 1000;
    private static final String READ_STATEMENT = "SELECT * FROM person";
    private static final String WRITE_STATEMENT = "INSERT INTO person (name, age) VALUES ('Michael', 24)";

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Test
    public void writingTest() throws IOException {
        HttpPost post = new HttpPost(SUBMIT_URL);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("statement", WRITE_STATEMENT));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        long start = System.currentTimeMillis();
        for (int i = 0; i < TEST_NUM; i++) {
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {
                int entity = Integer.parseInt(EntityUtils.toString(response.getEntity()));
                assert entity == 200;
            }
        }

        long end = System.currentTimeMillis();
        long tps = (long) (TEST_NUM / (double) (end - start) * 1000);
        System.out.println(tps);
    }

    @Test
    public void readingTest() throws IOException {
        HttpPost post = new HttpPost(SUBMIT_URL);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("statement", READ_STATEMENT));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        long start = System.currentTimeMillis();
        for (int i = 0; i < TEST_NUM; i++) {
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {
                int entity = Integer.parseInt(EntityUtils.toString(response.getEntity()));
                assert entity == 200;
            }
        }

        long end = System.currentTimeMillis();
        long tps = (long) (TEST_NUM / (double) (end - start) * 1000);
        System.out.println(tps);
    }

}

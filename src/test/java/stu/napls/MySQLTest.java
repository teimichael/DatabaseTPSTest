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
import java.util.ArrayList;
import java.util.List;

public class MySQLTest {

    private static final String WRITE_URL = "http://localhost:9000/test/write";
    private static final String READ_URL = "http://localhost:9000/test/read";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private static final int TEST_NUM = 1000;
    private static final String READ_STATEMENT = "SELECT * FROM person";
    private static final String WRITE_STATEMENT = "INSERT INTO person (name, age) VALUES ('Michael', 24)";

    @Test
    public void writingTest() throws IOException {
        HttpPost post = new HttpPost(WRITE_URL);
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
    public void readingTest() throws IOException{
        HttpPost post = new HttpPost(READ_URL);
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

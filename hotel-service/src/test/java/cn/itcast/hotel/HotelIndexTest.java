package cn.itcast.hotel;

import cn.itcast.hotel.constants.HotelConstants;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * 酒店索引测试
 *
 * @author shenyl
 * @date 2023/05/11
 */
public class HotelIndexTest {
    private RestHighLevelClient client;


    /**
     * 测试初始化
     *
     * @throws IOException ioexception
     */
    @Test
    public void testInit() throws IOException {
        System.out.println(client);
    }

    /**
     * 创建酒店索引
     *
     * @throws IOException ioexception
     */
    @Test
    public void createHotelIndex() throws IOException {
        // 1.创建请求对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        request.source(HotelConstants.MAPPING_NAME, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除酒店索引
     *
     * @throws IOException ioexception
     */
    @Test
    public void deleteHotelIndex() throws IOException {
        // 1.创建请求对象
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    /**
     * 判断是否存在索引
     *
     * @throws IOException ioexception
     */
    @Test
    public void isExistHotelIndex() throws IOException {
        // 1.创建请求对象
        GetIndexRequest request = new GetIndexRequest("hotel");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists?"索引存在":"索引不存在");
    }



    /**
     * 设置
     */
    @Before
    public void setUp(){
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.204.128:9200")
        ));
    }

    /**
     * 拆除
     *
     * @throws IOException ioexception
     */
    @After
    public void tearDown() throws IOException {
        this.client.close();
    }
}

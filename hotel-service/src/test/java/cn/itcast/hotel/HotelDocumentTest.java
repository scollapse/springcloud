package cn.itcast.hotel;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;


/**
 * 酒店文档测试
 *
 * @author shenyl
 * @date 2023/05/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelDocumentTest {
    private RestHighLevelClient client;
    @Autowired
    private IHotelService hotelService;


    /**
     * 添加文档
     *
     * @throws IOException ioexception
     */
    @Test
    public void addDocument() throws IOException {
        Hotel hotel = hotelService.getById(61083);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        IndexRequest indexRequest = new IndexRequest("hotel").id(hotel.getId().toString());
        indexRequest.source(JSON.toJSONString(hotelDoc),XContentType.JSON);
        client.index(indexRequest,RequestOptions.DEFAULT);
    }

    /**
     * 通过id获取文档
     *
     * @throws IOException ioexception
     */
    @Test
    public void getDocumentById() throws IOException {
        GetRequest indexRequest = new GetRequest("hotel").id("61083");
        GetResponse response = client.get(indexRequest, RequestOptions.DEFAULT);
        String source = response.getSourceAsString();
        HotelDoc hotelDoc = JSON.parseObject(source, HotelDoc.class);
        System.out.println(hotelDoc);
    }


    /**
     * 通过id更新文档
     *
     * @throws IOException ioexception
     */
    @Test
    public void updateDocumentById() throws IOException {
        UpdateRequest indexRequest = new UpdateRequest("hotel","61083");
        indexRequest.doc(
                "price","952",
                "starName","四钻"
        );
         client.update(indexRequest, RequestOptions.DEFAULT);
    }


    /**
     * 批量请求
     *
     * @throws IOException ioexception
     */
    @Test
    public void bulkRequest() throws IOException {
        List<Hotel> list = hotelService.list();
        BulkRequest indexRequest = new BulkRequest();
        list.stream().forEach(e -> {
            HotelDoc hotelDoc = new HotelDoc(e);
            indexRequest.add(
                    new IndexRequest("hotel")
                            .id(e.getId().toString())
                            .source(JSON.toJSONString(hotelDoc),XContentType.JSON)
            );
        });
        client.bulk(indexRequest, RequestOptions.DEFAULT);
    }




    /**
     * 删除文档id
     *
     * @throws IOException ioexception
     */
    @Test
    public void deleteDocumentById() throws IOException {
        DeleteRequest indexRequest = new DeleteRequest("hotel","61083");
        client.delete(indexRequest, RequestOptions.DEFAULT);
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

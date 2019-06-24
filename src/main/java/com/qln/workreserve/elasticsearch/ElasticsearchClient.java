package com.qln.workreserve.elasticsearch;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Title: $
 * @Project: csai-api
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/6/21$ 15:17$
 * @Version V2.0
 **/
@Configuration
public class ElasticsearchClient {

    @Bean
    public TransportClient getTransportClient() throws UnknownHostException {
//        Settings settings = Settings.builder().put("cluster.name", "my-application")
//                //目的是为了可以找到集群，嗅探机制开启
//                .put("client.transport.sniff", true)
//                .build();
//        TransportAddress address = new TransportAddress(InetAddress.getByName("192.168.239.130"), 9200);
//        TransportClient client = null;
//        try {
//            client = new PreBuiltTransportClient(settings);
//        } catch (Exception e) {
//            System.out.println("e===" + e);
//        }
//        client.addTransportAddress(address);
//        return client;


        TransportClient transportClient = null;
        try {
            Settings settings = Settings.builder()
                    //集群名称
                    .put("cluster.name", "my-application")
                    //目的是为了可以找到集群，嗅探机制开启
                    .put("client.transport.sniff", true)
                    .build();
            transportClient = new PreBuiltTransportClient(settings);
            TransportAddress firstAddress = new TransportAddress(InetAddress.getByName("192.168.239.130"), Integer.parseInt("9300"));
            transportClient.addTransportAddress(firstAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transportClient;
    }

    // 查询
    @Test
    public void searchESData() throws Exception {
        TransportClient client = getTransportClient();
        GetResponse response = client.prepareGet().execute().actionGet();
        System.out.println(response);
        client.close();
    }

    // 添加
    @Test
    public void addESData() throws Exception {
        TransportClient client = getTransportClient();
        XContentBuilder docment = XContentFactory.jsonBuilder().startObject()
                .field("name", "秦莉娜")
                .field("age", 21)
                .field("sex", "女")
                .endObject();
//        IndexResponse response = client.prepareIndex().setSource(docment).get();
//        System.out.println(response);
//        client.close();





        // 建立文档对象
        /**
         * 参数一blog1：表示索引对象
         * 参数二article：类型
         * 参数三1：建立id
         */
//        IndexResponse response = client.prepareIndex("blog", "sb", "99").setSource(docment).get();
        IndexResponse response = client.prepareIndex("blog8", "user").setSource(docment).get();
        System.out.println(response);
        //释放资源
        client.close();
    }

    // 修改
    @Test
    public void udpateESData() throws Exception {
        TransportClient client = getTransportClient();
        UpdateRequest request = new UpdateRequest();
        request.index("blog").type("user").id("47")
                .doc(XContentFactory.jsonBuilder().startObject()
                        .field("name", "蜘蛛精").endObject());
        UpdateResponse updateResponse = client.update(request).get();
        System.out.println(updateResponse);
    }
    @Test
    private void deleteESData() throws Exception {
        TransportClient client = getTransportClient();
        DeleteRequest request = new DeleteRequest();
        request.index("blog").type("user").id("47");
        DeleteResponse deleteResponse = client.delete(request).get();
        System.out.println(deleteResponse);
    }
}

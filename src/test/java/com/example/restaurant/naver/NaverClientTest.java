package com.example.restaurant.naver;


import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReeq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest() {
        var search = new SearchLocalReeq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);

        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getCategory());
        System.out.println(result);
    }

    @Test
    public  void searchImageTest(){
        var search = new SearchImageReq();
        search.setQuery("갈비집");

        var result = naverClient.searchImageRes(search);

        System.out.println(result);
    }
}

package com.terenceqin.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;

    @Test
    void contextLoads() {
    }

    @Autowired
    String aaa;

    @Test
    public void testnacos(){
        System.out.println(aaa);
    }

    @Test
    public void testUpload() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\HAN\\Downloads\\123.jpg");
        // 参数1位bucket  参数2位最终名�?        ossClient.putObject("gulimall-fermhan","321.jpg",inputStream);
        ossClient.shutdown();
    }

}

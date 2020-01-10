package com.chengang.captchaapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tensorflow.TensorFlow;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author: chengang
 * @date: 2020/1/6
 * @description:
 */
@RestController
public class ThirdCaptchaController {

    @GetMapping("/get")
    public void getThirdCaptcha() throws Exception {
        String url = "https://login.10086.cn/captchazh.htm?type=05&timestamp=1578277097458";
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(TensorFlow.version());
        for (int i = 0; i < 100; i++) {
            ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);
            byte[] body = responseEntity.getBody();
            OutputStream os = new FileOutputStream(new File("C:/Users/Administrator/Desktop/thirdCaptcha/"+"_"+System.currentTimeMillis()+new Random().nextInt(3) +".png"));
            os.write(body);
            os.close();
        }
    }
}

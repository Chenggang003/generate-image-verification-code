package com.chengang.captchaapi;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;


@SpringBootTest
class CaptchaApiApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaApiApplicationTests.class);

    @Test
    void contextLoads() throws Exception {

        for (int i = 0; i < 1000; i++) {
            Captcha specCaptcha = new SpecCaptcha(100, 50);
            specCaptcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
            specCaptcha.setLen(4);
            generateImageCode(specCaptcha);

//        Captcha chineseCaptcha = new ChineseCaptcha(130, 48);
//        generateImageCode(chineseCaptcha);

//        Captcha arithmeticCaptcha = new ArithmeticCaptcha(130, 48);
//        arithmeticCaptcha.setLen(3);

//        arithmeticCaptcha.setFont(new Font("楷体", Font.PLAIN, 28));
//        arithmeticCaptcha.setFont(Captcha.FONT_9);
//        generateImageCode(arithmeticCaptcha);

//        Captcha gifCaptcha = new GifCaptcha(130, 48);
//        generateImageCode(gifCaptcha);
        }

    }

    private void generateImageCode(Captcha captcha) throws FileNotFoundException {
//        logger.info("验证码的的字符：{}", captcha.text());
//        logger.info("验证码的的字符数组：{}", Arrays.toString(captcha.textChar()));
//        logger.info("图片验证码BASE64进行编码：{}", captcha.toBase64());
        OutputStream outputStream = new FileOutputStream(new File("E:/github/cnn_captcha/image/origin/"+captcha.text()+"_"+System.currentTimeMillis()+new Random().nextInt(3) +".png"));
//        OutputStream outputStream = new FileOutputStream(new File("E:/captcha/"+captcha.text()+"_"+System.currentTimeMillis()+new Random().nextInt(3) +".png"));
        captcha.out(outputStream);
    }
}

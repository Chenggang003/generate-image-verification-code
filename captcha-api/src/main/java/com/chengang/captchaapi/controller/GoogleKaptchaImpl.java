package com.chengang.captchaapi.controller;

import com.baomidou.kaptcha.GoogleKaptcha;
import com.baomidou.kaptcha.exception.KaptchaRenderException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_DATE;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author: chengang
 * @date: 2020/1/10
 * @description:
 */
@Component
public class GoogleKaptchaImpl extends GoogleKaptcha {

    private DefaultKaptcha kaptcha;

    @Autowired
    private HttpServletRequest request;

    public GoogleKaptchaImpl(DefaultKaptcha kaptcha) {
        super(kaptcha);
        this.kaptcha = kaptcha;
    }

    @Override
    public String render() {
        String sessionCode = kaptcha.createText();
        try {
            OutputStream outputStream = new FileOutputStream(new File("E:\\github\\cnn_captcha\\image\\origin\\" + sessionCode + "_" + System.currentTimeMillis() + new Random().nextInt(3) + ".jpg"));
            request.getSession().setAttribute(KAPTCHA_SESSION_KEY, sessionCode);
            request.getSession().setAttribute(KAPTCHA_SESSION_DATE, System.currentTimeMillis());
            ImageIO.write(kaptcha.createImage(sessionCode), "jpg", outputStream);
            outputStream.close();
            return sessionCode;
        } catch (IOException e) {
            throw new KaptchaRenderException(e);
        }
    }
}

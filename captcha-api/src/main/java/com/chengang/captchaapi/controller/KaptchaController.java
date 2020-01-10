package com.chengang.captchaapi.controller;

import com.baomidou.kaptcha.GoogleKaptcha;
import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaRenderException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_DATE;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author: chengang
 * @date: 2019/12/27
 * @description:
 */
@RestController
public class KaptchaController {

    @Autowired
    private Kaptcha googleKaptchaImpl;

    @GetMapping("/render")
    public void render() throws Exception {
        for (int i = 0; i < 1000; i++) {
            googleKaptchaImpl.render();
        }
    }

    @PostMapping("/valid")
    public Map<String,Object> validDefaultTime(@RequestParam String code) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            googleKaptchaImpl.validate(code);
            map.put("status","校验成功");
        }catch (KaptchaIncorrectException e){
        }catch (KaptchaNotFoundException e){
        }catch (KaptchaRenderException e){
        }catch (KaptchaTimeoutException e){
        }
        return map;
    }
}

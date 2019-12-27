package com.chengang.captchaapi.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaRenderException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: chengang
 * @date: 2019/12/27
 * @description:
 */
@Controller
public class KaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public Map<String,Object> validDefaultTime(@RequestParam String code) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            kaptcha.validate(code);
            map.put("status","校验成功");
        }catch (KaptchaIncorrectException e){
        }catch (KaptchaNotFoundException e){
        }catch (KaptchaRenderException e){
        }catch (KaptchaTimeoutException e){
        }
        return map;
    }
}

package com.mycode.system.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycode.system.user.domain.User;
import com.mycode.system.user.service.UserService;
import com.mycode.util.JsonResult;
import com.mycode.util.RedisUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;

@CrossOrigin
@Controller
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取图形验证码
     * @param len 返回的图形验证码长度，默认4位数
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getCaptcha.do")
    public JsonResult<Object> getCaptcha(@RequestParam(value = "len",required = false,defaultValue = "4") Integer len) {
        //初始化组件
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, len);
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        //存储到redis，有效时间为3分钟
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisUtil.set(token,specCaptcha.text(),180);
        //封装结果集
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("token",token);
        resultMap.put("image",specCaptcha.toBase64());
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/login.do")
    public JsonResult<Object> login(@RequestParam("userId") String userId
            , @RequestParam("password") String password
            , @RequestParam("token") String token
            , @RequestParam("verityCode") String verityCode, HttpServletRequest request){
        //
/*        boolean hasKey = redisUtil.hasKey(token);
        if (!hasKey) {
            return JsonResult.error(401," 验证码已超时，请重新获取！");
        }else{
            if(!redisUtil.get(token).toString().equals(verityCode)){
                return JsonResult.error(401,"验证码输入有误！");
            }
        }*/
        //
        User user = userService.getUserById(userId);
        try{
            if(user==null){
                return JsonResult.error(401,userId+" 账号未注册！");
            }else{
                if(!user.getUserId().equals(userId) || !user.getPassword().equals(password)){
                    return JsonResult.error(402,"账号或密码错误！");
                }
            }
        } catch (Exception e){
            return JsonResult.error("登录失败");
        }
        request.getSession().setAttribute("user", user);
        return JsonResult.success("登录成功",user);
    }

    @ResponseBody
    @RequestMapping("/logout.do")
    public JsonResult<Object> logout(HttpServletRequest request){
        request.getSession().invalidate();
        return JsonResult.success();
    }
}

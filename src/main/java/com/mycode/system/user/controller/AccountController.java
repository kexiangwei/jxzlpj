package com.mycode.system.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycode.util.StringUtils;
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
     * @param length 返回的图形验证码长度，默认4位数
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getCaptcha.do")
    public JsonResult<Object> getCaptcha(@RequestParam(value = "length",required = false,defaultValue = "4") Integer length
            , HttpServletRequest request) {
        //初始化组件
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, length);
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        //存储到redis
        String token = request.getSession().getId();
        redisUtil.set(token,specCaptcha.text(),300); //验证码有效时间为5分钟
        //封装结果集
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("token",token);
        resultMap.put("image",specCaptcha.toBase64());
        return JsonResult.success(resultMap);
    }

    /**
     * 登入
     * @param token
     * @param imageCode
     * @param userId
     * @param password
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/login.do")
    public JsonResult<Object> login(@RequestParam("token") String token, @RequestParam("imageCode") String imageCode
            , @RequestParam("userId") String userId, @RequestParam("password") String password
            , HttpServletRequest request, HttpServletResponse response){
        //校验图形验证码
        /*boolean hasKey = redisUtil.hasKey(token);
        if (hasKey) {
            if(!redisUtil.get(token).toString().equals(imageCode)){
                return JsonResult.error(400,"验证码输入有误！");
            }
        }else{
            return JsonResult.error(400," 验证码已超时，请重新获取！");
        }*/
        //校验账号密码
        User user = userService.getUserById(userId);
        if(user == null){
            return JsonResult.error(400,userId+"账号或密码错误！");
        }else{
            if(!user.getPassword().equals(password)){
                return JsonResult.error(400,"账号或密码错误！");
            }
        }
        request.getSession().setAttribute("loginUser", user);
        return JsonResult.success("登录成功",user);
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/logout.do")
    public JsonResult<Object> logout(HttpServletRequest request){
        request.getSession().invalidate();
        return JsonResult.success();
    }
}

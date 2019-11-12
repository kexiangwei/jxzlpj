package com.mycode.system.user.controller;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;
import com.mycode.system.user.service.UserService;
import com.mycode.util.JsonResult;
import com.mycode.util.RedisUtil;
import com.wf.captcha.Captcha;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @auther kexiangwei
 * @date 2019/6/24
 */
@CrossOrigin
@Controller
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取图形验证码
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getCaptcha.do")
    public JsonResult<Object> getCaptcha() throws Exception {
        SpecCaptcha specCaptcha = new SpecCaptcha();
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        String code = specCaptcha.text();
        File file = new File("/jxzlpj/captcha/"+code+".jpg");
        boolean bool = specCaptcha.out(new FileOutputStream(file));
        if(!bool){
            return JsonResult.error();
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisUtil.set(token,code,180);
        //封装结果集
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("imgPath","captcha/"+code+".jpg");
        return JsonResult.success(map);
    }

    @ResponseBody
    @RequestMapping("/login.do")
    public JsonResult<Object> login(@RequestParam("userId") String userId, @RequestParam("password") String password
            , @RequestParam("token") String token, @RequestParam("verityCode") String verityCode, HttpServletRequest request){
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

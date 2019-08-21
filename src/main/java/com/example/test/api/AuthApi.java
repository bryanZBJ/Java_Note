package com.example.test.api;

import com.alibaba.fastjson.JSONObject;
import com.example.test.jwt.JWTInfo;
import com.example.test.util.CommonResult;
import com.example.test.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthApi {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 获取token
     *
     * @param jwtUser
     * @return
     */
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public String createAuthenticationToken(@RequestBody JWTInfo jwtUser) {
        try {
            return jwtTokenUtil.generateToken(jwtUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 解析token信息
     *
     * @return
     */
    @RequestMapping(value = "/getUserFromToken", method = RequestMethod.GET)
    public CommonResult<String> getUserFromToken(String token) {
        try {
            JWTInfo jwtInfo = jwtTokenUtil.getInfoFromToken(token);
            if (null != jwtInfo) {
                return CommonResult.success(JSONObject.toJSONString(jwtInfo));
            }
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return CommonResult.fail("token失效");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return CommonResult.fail("获取用户信息失败");
    }

}

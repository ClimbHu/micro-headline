package com.headline.test;


import com.headline.util.JwtHelper;
import org.testng.annotations.Test;

public class TestJwtHelper {

    @Test
    public void testAllMethod() throws InterruptedException {
        String token = JwtHelper.createToken(1L);
        System.out.println("token = " + token);
        Long userId = JwtHelper.getUserId(token);
        System.out.println("userId = " + userId);
        System.out.println("是否过期 = " + JwtHelper.isExpiration(token));
        Thread.sleep(6000);
        System.out.println("是否过期 = " + JwtHelper.isExpiration(token));
    }
}

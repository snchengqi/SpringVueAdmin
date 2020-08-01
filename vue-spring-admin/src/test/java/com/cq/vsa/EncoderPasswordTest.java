package com.cq.vsa;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * @Author: chengqi
 * @Date: Created in 15:50 2019/4/9
 */
public class EncoderPasswordTest {

    @Test
    public void testEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }

    @Test
    public void testUUID() {
        System.out.println(UUID.randomUUID().toString());
    }
}

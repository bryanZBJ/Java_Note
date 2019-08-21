package com.example.test.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTInfo {

    private String username;
    private String userId;
    private String extend;
    private String expire;
}

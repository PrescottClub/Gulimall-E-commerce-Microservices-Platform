package com.terenceqin.gulimall.member.vo;

import lombok.Data;

@Data
public class SocialUser {

    private String accessToken;

    private String remindIn;

    private Long expiresIn;

    private String uid;

    private String isrealname;
}

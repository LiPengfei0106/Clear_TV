package com.lipengfei.tv.global;

public enum ErrorCode{
        JSON_ERROR(0,"Json解析出错"),
        NET_ERROR(1,"网络出错");

        public int code;
        public String msg;
        ErrorCode(int code,String msg){
            this.code = code;
            this.msg = msg;
        }
    }
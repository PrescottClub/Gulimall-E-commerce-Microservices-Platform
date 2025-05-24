package com.atguigu.common.enume;

public enum  OrderStatusEnum {
    CREATE_NEW(0,"待付�?),
    PAYED(1,"已付�?),
    SENDED(2,"已发�?),
    RECIEVED(3,"已完�?),
    CANCLED(4,"已取�?),
    SERVICING(5,"售后�?),
    SERVICED(6,"售后完成");
    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

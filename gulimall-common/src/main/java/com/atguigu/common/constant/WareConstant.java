package com.atguigu.common.constant;

public class WareConstant {

    /** 采购单状态枚�?*/
    public enum  PurchaseStatusEnum{
        CREATED(0,"新建"),ASSIGNED(1,"已分�?),
        RECEIVE(2,"已领�?),FINISH(3,"已完�?),
        HASERROR(4,"有异�?);
        private int code;
        private String msg;

        PurchaseStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }


    /** 采购需求枚�?*/
    public enum  PurchaseDetailStatusEnum{
        CREATED(0,"新建"),ASSIGNED(1,"已分�?),
        BUYING(2,"正在采购"),FINISH(3,"已完�?),
        HASERROR(4,"采购失败");
        private int code;
        private String msg;

        PurchaseDetailStatusEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}

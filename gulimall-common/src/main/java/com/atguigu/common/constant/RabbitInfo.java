package com.atguigu.common.constant;

// 本来想用ConfigurationProperties�?// 但是在使用listener注解时无法注入队列字符串，所以只能用类了
// 类的话就丢失了原来在nacos能动态更新的优点，但为了能方便使用，我们还是在这里定义吧
public class RabbitInfo {

    public static class Order{
        // 其实厂里应该大写，但是我们为了区分，这里也不改了
        public static final String exchange = "order-event-exchange";
        public static final String delayQueue = "order.delay.queue";
        public static final String delayRoutingKey = "order.locked";

        public static final String releaseQueue = "order.release.queue";
        public static final String releaseRoutingKey="order.release";
        // 其他路由key也是跳到releaseQueue
        public static final String baseRoutingKey="order.#";
        public static final int ttl = 900000;
    }
    public static class Stock{
        public static final String exchange="stock-event-exchange";
        public static final String delayQueue="stock.delay.queue";
        public static final String delayRoutingKey="stock.locked";
        public static final String releaseQueue="stock.release.queue";
        public static final String releaseRoutingKey="stock.release.queue";
        public static final String baseRoutingKey="stock.#";
        public static final int ttl = 900000;
    }
    public static class SecKill{
        public static final String exchange="seckill-event-exchange";
        public static final String delayQueue="seckill.delay.queue";
        public static final String delayRoutingKey="seckill.locked";
        public static final String releaseQueue="seckill.release.queue";
        public static final String releaseRoutingKey="seckill.release.queue";
        public static final int ttl = 900000;
    }
}

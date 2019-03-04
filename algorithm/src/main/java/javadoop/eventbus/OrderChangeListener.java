package javadoop.eventbus;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

@Component
@EventBusListener
public class OrderChangeListener {

    @Subscribe
    public void created(OrderCreatedEvent event) {
        long orderId = event.getOrderId();
        long userId = event.getUserId();
        // 订单创建成功后的各种操作，如发短信、发邮件等等。
        // 注意，事件可以被订阅多次，也就是说可以有很多方法监听 OrderCreatedEvent 事件，
        // 所以没必要在一个方法中处理发短信、发邮件、更新库存等
    }

//    @Subscribe
//    public void change(OrderChangeEvent event) {
//        // 处理订单变化后的修改
//        // 如发送提醒、更新物流等
//    }
}

package javadoop.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private EventBusCenter eventBusCenter;

    public void createOrder() {
        // 处理创建订单
        // ...
        // 发送异步事件
        eventBusCenter.postAsync(new OrderCreatedEvent(1L, 1L));
    }
}

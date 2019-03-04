package javadoop.eventbus;

public class OrderCreatedEvent {
    private long orderId;
    private long userId;
    public OrderCreatedEvent(long orderId, long userId) {
        this.setOrderId(orderId);
        this.setUserId(userId);
    }

    public long getOrderId() {
        return orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}

package de.tum.in.ase.eist.ecommerce;

public class ECommerceFacade {
    private AdvertisementController advertisementController;
    private OrderController orderController;
    private ShippingController shippingController;

    public ECommerceFacade() {
        this.advertisementController = new AdvertisementController();
        this.orderController = new OrderController();
        this.shippingController = new ShippingController();
    }

    public void processOrder(Order order) {
        orderController.processOrder(order);
    }

    public void processOrder(Order order, String s) {
        orderController.processOrder(order, s);
    }

    public Order retrieveLatestOrder(int n) {
        return orderController.retrieveLatestOrder(n);
    }

    public void playAdvertisement(int n) {
        advertisementController.playAdvertisement(n);
    }

    public void shipOrder(Order order, String s) {
        order.setShipping(shippingController.createShipping(s));
        shippingController.shipOrder(order);
    }

}

public interface PaymentStrategy {
    void pay(Booking booking);

    boolean isValid(String paymentInfo);

    String getType();
}

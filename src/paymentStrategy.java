public interface paymentStrategy {
    void pay(booking booking);

    boolean isValid(String paymentInfo);

    String getType();
}

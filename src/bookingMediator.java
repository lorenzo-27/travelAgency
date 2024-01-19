public interface bookingMediator {
    booking createBooking(customer customer, flight flight, hotel hotel, paymentStrategy strategy, String bookingStatus);
    void cancelBooking(booking booking);
}

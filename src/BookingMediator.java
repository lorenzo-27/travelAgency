public interface BookingMediator {
    Booking createBooking(Customer customer, Flight flight, Hotel hotel, PaymentStrategy strategy, String bookingStatus);
    void cancelBooking(Booking booking);
}

import java.time.LocalDateTime;

public class Flight {

    private final String id;
    private final String airline;
    private final String departureAirport;
    private final String arrivalAirport;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;
    private final int duration;
    private final int price;
    private int nAvailableSeats;

    public Flight(String id, String airline, String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int duration, int price, int nAvailableSeats) {
        this.id = id;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.duration = duration;
        this.price = price;
        this.nAvailableSeats = nAvailableSeats;
    }

    public String getId() {
        return id;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public int getNAvailableSeats() {
        return nAvailableSeats;
    }

    public void setNAvailableSeats(int nAvailableSeats) {
        this.nAvailableSeats = nAvailableSeats;
    }

    public void decreaseNAvailableSeats() {
        this.nAvailableSeats--;
    }

    public void increaseNAvailableSeats() {
        this.nAvailableSeats++;
    }

}

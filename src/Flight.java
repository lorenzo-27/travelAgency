import java.time.LocalDateTime;

public class Flight {

    private String id;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private int duration;
    private int price;

    public Flight(String id, String airline, String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int duration, int price) {
        this.id = id;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.duration = duration;
        this.price = price;
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

}

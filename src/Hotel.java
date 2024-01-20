public class Hotel {

    private final String id;
    private final String name;
    private final String address;
    private final String city;
    private final String country;
    private final int price;
    private int nAvailableRooms;

    public Hotel(String id, String name, String address, String city, String country, int price, int nAvailableRooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.price = price;
        this.nAvailableRooms = nAvailableRooms;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getPrice() { return price; }

    public int getNAvailableRooms() {
        return nAvailableRooms;
    }

    public void decreaseNAvailableRooms() {
        nAvailableRooms--;
    }

    public void increaseNAvailableRooms() {
        nAvailableRooms++;
    }

    public void setNAvailableRooms(int nAvailableRooms) {
        this.nAvailableRooms = nAvailableRooms;
    }

}

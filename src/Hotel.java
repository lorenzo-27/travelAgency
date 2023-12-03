public class Hotel {

    private String id;
    private String name;
    private String address;
    private String city;
    private String country;
    private int stars;

    public Hotel(String id, String name, String address, String city, String country, int stars) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.stars = stars;
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

    public int getStars() {
        return stars;
    }

}

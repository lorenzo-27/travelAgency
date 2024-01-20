public class customer {

    private String name;
    private String email;
    private String phone;

    private String paymentInfo;

    public customer(String name, String email, String phone, String paymentInfo) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.paymentInfo = paymentInfo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

}

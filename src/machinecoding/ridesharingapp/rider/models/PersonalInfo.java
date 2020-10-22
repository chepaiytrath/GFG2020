package machinecoding.ridesharingapp.rider.models;

public abstract class PersonalInfo {
    private Long id;
    private String name;
    private String phoneNumber;
    private String countryCode;

    public PersonalInfo() {

    }

    public PersonalInfo(Long id, String name, String phoneNumber, String countryCode) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}

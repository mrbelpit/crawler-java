package JobDescription;

public class JobLocation{
  public String type;
  public Address address;

  public JobLocation() {
  }

  public JobLocation(String type, Address address) {
    this.type = type;
    this.address = address;
  }

  @Override
  public String toString() {
    return "JobLocation{" +
           "type='" + type + '|' +
           ", address=" + address.toString() +
           '}';
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}

package JobDescription;

public class Address {
  public String type;
  public String addressLocality;
  public String addressRegion;
  public String postalCode;

  public Address() {
  }

  public Address(String type, String addressLocality, String addressRegion, String postalCode) {
    this.type = type;
    this.addressLocality = addressLocality;
    this.addressRegion = addressRegion;
    this.postalCode = postalCode;
  }

  @Override
  public String toString() {
    return "Address{" +
           "type='" + type + '|' +
           ", addressLocality='" + addressLocality + '|' +
           ", addressRegion='" + addressRegion + '|' +
           ", postalCode='" + postalCode + '|' +
           '}';
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}

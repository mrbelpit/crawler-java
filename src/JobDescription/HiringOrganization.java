package JobDescription;

public class HiringOrganization{
  public String type;
  public String name;

  public HiringOrganization() {
  }

  public HiringOrganization(String type, String name) {
    this.type = type;
    this.name = name;
  }

  @Override
  public String toString() {
    return "HiringOrganization{" +
           "type='" + type + '|' +
           ", name='" + name + '|' +
           '}';
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

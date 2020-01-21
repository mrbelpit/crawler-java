package JobDescription;

import java.util.List;

public class JobDescription {
  public String context;
  public String type;
  public String datePosted;
  public String description;
  public String educationRequirements;
  public List<String> employmentType;
  public List<String> experienceRequirements;
  public JobLocation jobLocation;
  public String occupationalCategory;
  public String skills;
  public String title;
  public HiringOrganization hiringOrganization;
  public String jobBenefits;
  public String validThrough;


  public JobDescription() {
  }

  public JobDescription(String context, String type, String datePosted, String description,
          String educationRequirements, List<String> employmentType,
          List<String> experienceRequirements, JobLocation jobLocation,
          String occupationalCategory, String skills, String title,
          HiringOrganization hiringOrganization,
          String jobBenefits, String validThrough) {
    this.context = context;
    this.type = type;
    this.datePosted = datePosted;
    this.description = description;
    this.educationRequirements = educationRequirements;
    this.employmentType = employmentType;
    this.experienceRequirements = experienceRequirements;
    this.jobLocation = jobLocation;
    this.occupationalCategory = occupationalCategory;
    this.skills = skills;
    this.title = title;
    this.hiringOrganization = hiringOrganization;
    this.jobBenefits = jobBenefits;
    this.validThrough = validThrough;
  }

  @Override
  public String toString() {
    return "JobDescription{" +
           "context='" + context + '|' +
           ", type='" + type + '|' +
           ", datePosted='" + datePosted + '|' +
           ", description='" + description + '|' +
           ", educationRequirements='" + educationRequirements + '|' +
           ", employmentType=" + employmentType.toString() +
           ", experienceRequirements=" + experienceRequirements.toString() +
           ", jobLocation=" + jobLocation.toString() +
           ", occupationalCategory='" + occupationalCategory + '|' +
           ", skills='" + skills + '|' +
           ", title='" + title + '|' +
           ", hiringOrganization=" + hiringOrganization.toString() +
           ", jobBenefits='" + jobBenefits + '|' +
           ", validThrough='" + validThrough + '|' +
           '}';
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDatePosted() {
    return datePosted;
  }

  public void setDatePosted(String datePosted) {
    this.datePosted = datePosted;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEducationRequirements() {
    return educationRequirements;
  }

  public void setEducationRequirements(String educationRequirements) {
    this.educationRequirements = educationRequirements;
  }

  public List<String> getExperienceRequirements() {
    return experienceRequirements;
  }

  public void setExperienceRequirements(List<String> experienceRequirements) {
    this.experienceRequirements = experienceRequirements;
  }

  public String getSkills() {
    return skills;
  }

  public void setSkills(String skills) {
    this.skills = skills;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public HiringOrganization getHiringOrganization() {
    return hiringOrganization;
  }

  public void setHiringOrganization(HiringOrganization hiringOrganization) {
    this.hiringOrganization = hiringOrganization;
  }

  public String getJobBenefits() {
    return jobBenefits;
  }

  public void setJobBenefits(String jobBenefits) {
    this.jobBenefits = jobBenefits;
  }

}

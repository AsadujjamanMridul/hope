package com.example.hope;

public class Registration {

    private String Name, Gender, Age, BloodGroup, Mobile, City, Ability, Bg_City_Ability, Name_Mobile, id;

    public Registration()
    {

    }

    public Registration(String name, String gender, String age, String bloodGroup, String mobile, String city, String ability, String bg_City_Ability, String name_Mobile, String id) {
        Name = name;
        Gender = gender;
        Age = age;
        BloodGroup = bloodGroup;
        Mobile = mobile;
        City = city;
        Ability = ability;
        Bg_City_Ability = bg_City_Ability;
        Name_Mobile = name_Mobile;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAbility() {
        return Ability;
    }

    public void setAbility(String ability) {
        Ability = ability;
    }

    public String getBg_City_Ability() {
        return Bg_City_Ability;
    }

    public void setBg_City_Ability(String bg_City_Ability) {
        Bg_City_Ability = bg_City_Ability;
    }

    public String getName_Mobile() {
        return Name_Mobile;
    }

    public void setName_Mobile(String name_Mobile) {
        Name_Mobile = name_Mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

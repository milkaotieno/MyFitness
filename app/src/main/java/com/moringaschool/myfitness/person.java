package com.moringaschool.myfitness;

public class person {
    // Variable to store data corresponding
    // to firstname keyword in database
    private String firstname;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String lastname;

    // Variable to store data corresponding
    // to age keyword in database
    private Integer age;
    // Variable to store data corresponding
    // to wight keyword in database
    private Integer weight;
    // Variable to store data corresponding
    // to height keyword in database
    private Integer height;
    // Variable to store data corresponding
    // to BMI keyword in database
    private Float bmi;
    // Mandatory empty constructor
    // for use of FirebaseUI
    public person() {}

    // Getter and setter method
    public String getFirstname()
    {
        return firstname;
    }
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    public Integer getAge()
    {
        return age;
    }
    public void setAge(Integer age)
    {
        this.age = age;
    }
    public Float getBmi(){
        return bmi;
    }
    public void  setBmi(Float bmi){
        this.bmi = bmi ;
    }
    public Integer getHeight(){
        return height;
    }
    public void setHeight(Integer height){
        this.height = height;
    }
    public Integer getWeight(){
        return weight;
    }
    public void setWeight(Integer weight){

        this.weight = weight;
    }
}

package com.example.eurekaclientmicro2.responce;

public class EmployeeResponce {
	
	private int id;
    private String name;
    private String email;
    private String age;
    
    // Add AddressResponse Here
    private AddressResponce addressResponse;
  
    public int getId() {
        return id;
    }
  
    public void setId(int id) {
        this.id = id;
    }
  
    public String getName() {
        return name;
    }
  
    public void setName(String name) {
        this.name = name;
    }
  
    public String getEmail() {
        return email;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }
  
    public String getAge() {
        return age;
    }
  
    public void setAge(String age) {
        this.age = age;
    }
  
    public AddressResponce getAddressResponse() {
        return addressResponse;
    }
  
    public void setAddressResponse(AddressResponce addressResponse) {
        this.addressResponse = addressResponse;
    }

}

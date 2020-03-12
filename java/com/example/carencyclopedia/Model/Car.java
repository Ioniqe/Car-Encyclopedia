package com.example.carencyclopedia.Model;

public class Car {
    private String CarClass, Id, Image, Manufacturer, Name, Price, TimeId, Website, Style, Engine, Transmission, Color, InteriorColor, DoorNb, SeatNb, Horsepower, Description, DriveType;
    private String Fuel, FuelTankCapacity, Consumption, Acceleration, CylinderNb, MaxSpeed, Weight;

    public Car() {
    }

    public Car(String carClass, String id, String image, String manufacturer, String name, String price, String timeId,
               String website, String style, String engine, String transmission, String color, String interiorColor,
               String doorNb, String seatNb, String horsepower, String description, String driveType, String fuel,
               String fuelTankCapacity, String consumption, String acceleration, String cylinderNb, String maxSpeed, String weight) {
        CarClass = carClass;
        Id = id;
        Image = image;
        Manufacturer = manufacturer;
        Name = name;
        Price = price;
        TimeId = timeId;
        Website = website;
        Style = style;
        Engine = engine;
        Transmission = transmission;
        Color = color;
        InteriorColor = interiorColor;
        DoorNb = doorNb;
        SeatNb = seatNb;
        Horsepower = horsepower;
        Description = description;
        DriveType = driveType;
        Fuel = fuel;
        FuelTankCapacity = fuelTankCapacity;
        Consumption = consumption;
        Acceleration = acceleration;
        CylinderNb = cylinderNb;
        MaxSpeed = maxSpeed;
        Weight = weight;
    }

    public String getCarClass() {
        return CarClass;
    }

    public void setCarClass(String aClass) {
        CarClass = aClass;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTimeId() {
        return TimeId;
    }

    public void setTimeId(String timeId) {
        TimeId = timeId;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getEngine() {
        return Engine;
    }

    public void setEngine(String engine) {
        Engine = engine;
    }

    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getInteriorColor() {
        return InteriorColor;
    }

    public void setInteriorColor(String interiorColor) {
        InteriorColor = interiorColor;
    }

    public String getDoorNb() {
        return DoorNb;
    }

    public void setDoorNb(String doorNb) {
        DoorNb = doorNb;
    }

    public String getSeatNb() {
        return SeatNb;
    }

    public void setSeatNb(String seatNb) {
        SeatNb = seatNb;
    }

    public String getHorsepower() {
        return Horsepower;
    }

    public void setHorsepower(String horsepower) {
        Horsepower = horsepower;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDriveType() {
        return DriveType;
    }

    public void setDriveType(String driveType) {
        DriveType = driveType;
    }

    public String getFuel() {
        return Fuel;
    }

    public void setFuel(String fuel) {
        Fuel = fuel;
    }

    public String getFuelTankCapacity() {
        return FuelTankCapacity;
    }

    public void setFuelTankCapacity(String fuelTankCapacity) {
        FuelTankCapacity = fuelTankCapacity;
    }

    public String getConsumption() {
        return Consumption;
    }

    public void setConsumption(String consumption) {
        Consumption = consumption;
    }

    public String getAcceleration() {
        return Acceleration;
    }

    public void setAcceleration(String acceleration) {
        Acceleration = acceleration;
    }

    public String getCylinderNb() {
        return CylinderNb;
    }

    public void setCylinderNb(String cylinderNb) {
        CylinderNb = cylinderNb;
    }

    public String getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        MaxSpeed = maxSpeed;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}

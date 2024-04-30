public class Housing {

    // Instance variables
    private String address;
    private int totalRooms;
    private double bedrooms;
    private double bathrooms;
    private boolean hasStudio;
    private int capacity;
    // Constructor to setup all the variables for this when the object is created.
    public Housing(String address, int rooms, double bedrooms, double bathrooms, boolean studio, int capacity) {
        if (address == null || rooms < 1 || capacity < 1 || bedrooms < 0 || bathrooms < 0) {
            throw new ArithmeticException("Housing must have an address or a room");
        }
        this.address = address;
        this.totalRooms = rooms;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.hasStudio = studio;
        this.capacity = capacity;
    }
    
    // Getter method to get the address of the place
    public String getAddress() {
        return this.address;
    }
    
    // Getter method to get the number of rooms
    public int getRooms() {
        return this.totalRooms;
    }
    
    // Getter method to get the number of bedrooms
    public double getBedrooms() {
        return this.bedrooms;
    }
    
    // Getter method to get the number of bathrooms
    public double getBathrooms() {
        return this.bathrooms;
    }
    // Getter method for whether or not the place has a studio or not
    public boolean hasStudio() {
        return this.hasStudio;
    }
    // Getter method to get the capacity of the place
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    // Converts the information to a string
    public String toString() {
        // This below just formats everything to a string for a default place
        return String.format("Address: %s%n Total Rooms: %s%n Bedrooms: %s%n Bathrooms: %s%n %s%s%n %s%s%n",
            this.address, this.totalRooms, this.bedrooms, this.bathrooms,
            "Studio:", this.hasStudio, "Capacity:", this.capacity);
            
    }


}
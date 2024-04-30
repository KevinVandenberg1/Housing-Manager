public class Buyable extends Housing {
    // Instance variables
    private double price;
    private Boolean forSale;
    private String owner;

    // Constructor to setup the class in addition to the super class when this object is created
    public Buyable(String address, double price, boolean forSale, String owner, 
                int rooms, double bedrooms, double bathrooms, boolean studio, int capacity) {
        super(address, rooms, bedrooms, bathrooms, studio, capacity);
        if (price < 0) {
            throw new ArithmeticException("Price isn't a valid input");
        }
        this.price = price;
        this.forSale = forSale;
        if (owner == null) {
            this.owner = "None";
        } else {
            this.owner = owner;
        }
    }
    
    // Getter method to get the status of whether or not its for sale
    public boolean saleStatus() {
        return this.forSale;
    }
    
    // Getter method to get the price of the place
    public double getPrice() {
        return this.price;
    }
    
    // Getter method to get the owner of the place
    public String getOwner() {
        return this.owner;
    }



    // Method that converts all the information to a string
    @Override
    public String toString() {

        String address = getAddress() + ":";
        String status = "    Status: For Sale";
        String price = "    Price: " + getPrice();
        String owner = "    Owner: None";
        if (!saleStatus()) { 
            status = "    Status: Sold";
            owner = "    Owner:" + getOwner();
        }
        String rooms = "    Rooms: " + getRooms();
        String bedrooms = "    Bedrooms: " + getBedrooms();
        String bathrooms = "    Bathrooms: " + getBathrooms();
        String capacity = "    Capacity: " + getCapacity() + " People";

        // Returns everything as one string
        return String.format("%n%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n", 
            address, status, price, rooms, bedrooms, bathrooms, owner, capacity);
    }


}

public class Rentable extends Housing{
    // Instance Variables
    private String landlord;
    private double baseRent;
    private double creditScore;
    private double reliabilityScore;
    private String tenant;
    private boolean forRent;

    // Constructor to setup this object and the superclass
    public Rentable(String landlord, double rent, double credit, String tenant, boolean rentStatus, String address, 
        int rooms, double bedrooms, double bathrooms, boolean hasStudio, int capacity) {
        super(address, rooms, bedrooms, bathrooms, hasStudio, capacity);
        if (credit < 1) {
            throw new ArithmeticException("credit isn't a valid amount");
        }
        this.landlord = landlord;
        this.baseRent = rent;
        this.creditScore = credit;
        this.reliabilityScore = credit;
        this.tenant = tenant;
        this.forRent = rentStatus;
    }


    // Getter method to get the Landlord of the property
    public String getLandlord() {
        return this.landlord;
    }
    
    // Getter method to get the rent of the property
    public double getRent() {
        double rent = calculatePrice();
        return rent;
    }
    // Gettter method to get the tenant of the property
    public String getTenant() {
        return this.tenant;
    }
    
    // getter method to get the rent status of the property
    public boolean rentStatus() {
        return this.forRent;
    }
    
    // Getter method to get the credit of the tenant
    public double getCredit() {
        return this.creditScore;
    }
    

    // Method to add an infraction to the tenant
    public void addInfraction() {
        if (this.reliabilityScore != 0 && this.reliabilityScore != 5) this.reliabilityScore = this.reliabilityScore + 1;
        else if (this.reliabilityScore == 0) System.out.println("Specified address doesnt have a tenant");
        else System.out.println("Specified address already has hit their max infraction count");
    }
    
    // Method to remove an infraction to the tenant
    public void removeInfraction() {
        if (this.reliabilityScore != 0 && this.reliabilityScore != 5) this.reliabilityScore = this.reliabilityScore -1;
        else if (this.reliabilityScore == 0) System.out.println("Specified address doesnt have a tenant");
        else System.out.println("Specified address already has no infractions");
    }
    
    // Method to calculate the new price for the tenant
    public double calculatePrice() {
        double price = this.baseRent + ((reliabilityScore -1) * 100);
        return price;
    }

    // Method below formats everything to a single string and then returns it.
    @Override
    public String toString() {
        String address = getAddress() + ":";
        String status = "   Status: For Rent";
        String rent = "   Rent: " + getRent();
        String tenant = "   Tenant: None";
        String landlord = "   The landlord is: " + getLandlord();

        if (!rentStatus()) {  // Checks the status for the rent
            status = "   Status: Not for Rent";
            tenant = "   Tenant:" + getTenant();
            rent = "   Rent: " + getRent() + "    (" + (getCredit()-1) + " infractions)";
        }
        String rooms = "   Rooms: " + getRooms();
        String bedrooms = "   Bedrooms: " + getBedrooms();
        String bathrooms = "   Bathrooms: " + getBathrooms();
        String capacity = "   Capacity: " + getCapacity() + "    People";


        // Formats the strings to one string and then returns it
        return String.format("%n%s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n %s%n", 
            address, landlord, status, rent, rooms, bedrooms, bathrooms, tenant, capacity);
    }
}

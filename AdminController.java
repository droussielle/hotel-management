public class AdminController {

    private String name;
    Authenticate authentication;
    private Database database;
    private boolean login;

    public AdminController(String name) {
        this.name = name;
        this.login = false;
    }

    // Login
    public void login(String username, String password) {
        login = authentication.checkLogin(username, password);
        if (login) {
            System.out.println("Login successful");
        } else {
            System.out.println("Incorrect password");
        }
    }

    // Logout
    public void logout() {
        login = false;
        System.out.println("Logout successful");
    }

    // Load database
    public void loadDatabase(Database database) {
        this.database = database;
        System.out.println("Database loaded");
    }

    // Get available rooms
    public void getAvailableRoom() {
        if (login) {
            System.out.println("Available rooms:");
            for (Room i : this.database.getRoomDB()) {
                if (i.getStatus() == 1) {
                    System.out.println("Room: " + i.getProperty().getName());
                }
            }
        }
    }

    // Get ordered item
    public void getOrderItem(String name) {
        if (login) {
            database.searchExtra(name);
        }
    }

    // Add an extra
    public void addExtra(Extra extra) {
        if (login) {
            database.addExtra(extra);
        }
    }

    // Remove an extra
    public void removeExtra(Extra extra) {
        if (login) {
            database.removeExtra(extra);
        }
    }

    // Add a room
    public void addRoom(Room room) {
        if (login) {
            database.addRoom(room);
        }
    }

    // Remove a room
    public void removeRoom(Room room) {
        if (login) {
            database.removeRoom(room);
        }
    }

    // Add a customer
    public void addCustomer(Customer customer) {
        if (login) {
            database.addCustomer(customer);
        }
    }

    // Remove a customer
    public void removeCustomer(Customer customer) {
        if (login) {
            database.removeCustomer(customer);
        }
    }

}

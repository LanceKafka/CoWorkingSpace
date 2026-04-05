import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private WorkspaceManager workspace = new WorkspaceManager();

    public static void main(String[] args) {
        new Main().displayMenu();
    }

    public void displayMenu() {
        while (true) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("WELCOME TO CO-WORKER SPACE HUB");
            System.out.println("------------------------------------------------------------------");
            System.out.println("[1] Login (Existing Member)\n[2] Register (New Member)\n[3] Exit");
            System.out.println("------------------------------------------------------------------");
            System.out.print("Selection > ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) handleLogin();
            else if (choice.equals("2")) handleRegistration();
            else if (choice.equals("3")) {
                System.out.println("------------------------------------------------------------------");
                System.out.println("CO-WORKER SPACE HUB  [EXIT]");
                System.out.println("------------------------------------------------------------------");
                System.out.print("Are you sure you want to exit? (Y/N): ");
                if (scanner.nextLine().equalsIgnoreCase("Y")) break;
            }
        }
    }

    private void handleLogin() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("CO-WORKER SPACE HUB [ LOGIN ]");
        System.out.println("------------------------------------------------------------------");
        System.out.print("Member ID : ");
        String id = scanner.nextLine();
        System.out.print("4-digit PIN: "); 
        String pin = scanner.nextLine(); 

        if (workspace.login(id, pin)) {
            System.out.println("[!] Login Successful!"); 
            userDashboard();
        } else {
            System.out.println("[!] Incorrect Credentials"); 
        }
    }

    private void handleRegistration() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("CO-WORKER SPACE HUB [ REGISTRATION ]");
        System.out.println("------------------------------------------------------------------");
        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Create a 4-digit PIN: ");
        String pin = scanner.nextLine();
        workspace.registerMember(name, age, id, pin);
    }

    private void userDashboard() {
        while (workspace.getCurrentUser() != null) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("CO-WORKER SPACE HUB");
            System.out.println("------------------------------------------------------------------");
            System.out.println("1. View Available Room");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel a Booking"); // Corrected order
            System.out.println("4. Exit");
            System.out.println("------------------------------------------------------------------");
            System.out.print("Selection > ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) workspace.displayAvailableSpaces();
            else if (choice.equals("2")) handleBooking();
            else if (choice.equals("3")) handleCancellation(); // Now accessible
            else if (choice.equals("4")) workspace.logout();
        }
    }

    private void handleBooking() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("CO-WORKER SPACE HUB  [BOOK A ROOM]");
        System.out.println("------------------------------------------------------------------");
        System.out.print("Enter Room ID: ");
        String rid = scanner.nextLine();
        System.out.print("Enter Duration (Hours): ");
        int hrs = Integer.parseInt(scanner.nextLine());
        System.out.println("------------------------------------------------------------------");
        System.out.print("Confirm Booking? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            workspace.finalizeBooking(rid, hrs); 
        } else {
            System.out.println("[!] TRANSACTION CANCELLED");
        }
    }

    private void handleCancellation() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("CO-WORKER SPACE HUB [CANCEL BOOKING]");
        System.out.println("------------------------------------------------------------------");
        System.out.print("Enter Room ID to Cancel: ");
        String rid = scanner.nextLine();
        System.out.print("Are you sure you want to cancel this booking? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            workspace.cancelBooking(rid);
        } else {
            System.out.println("[!] CANCELLATION ABORTED.");
        }
    }
}

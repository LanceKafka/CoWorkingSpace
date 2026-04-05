import java.util.*;

public class WorkspaceManager {
    private List<Member> members = new ArrayList<>();
    private List<Space> spaces = new ArrayList<>();
    private Member currentUser;

    public WorkspaceManager() {
        String[] ids = {"3R1", "3R2", "3R3", "2R1", "2R2", "2R3", "1R1", "1R2", "1R3"};
        for (String id : ids) {
            spaces.add(new Space(id, 500.00));
        }
    }

    // 

    public void displayAvailableSpaces() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("CO-WORKER SPACE HUB [VIEW ROOM]");
        System.out.println("RATE: ₱500.00/hour\t[OC] - OCCUPIED");
        System.out.println("------------------------------------------------------------------");
        int count = 0;
        for (Space s : spaces) {
            String status = s.isOccupied() ? " - OC" : "";
            System.out.print("[" + s.getSpaceId() + status + "]\t\t");
            if (++count % 3 == 0) System.out.println("\n");
        }
    }

    public void finalizeBooking(String spaceId, int duration) {
        for (Space s : spaces) {
            if (s.getSpaceId().equalsIgnoreCase(spaceId)) {
                if (s.isOccupied()) {
                    System.out.println("[!] TRANSACTION UNSUCCESSFUL\n[!] This room is already occupied!");
                } else {
                    s.setOccupied(true);
                    System.out.println("[!] TRANSACTION SUCCESSFUL");
                }
                return;
            }
        }
        System.out.println("[!] Room ID not found.");
    }

    // cancelBook
    public void cancelBooking(String spaceId) {
        for (Space s : spaces) {
            if (s.getSpaceId().equalsIgnoreCase(spaceId)) {
                if (s.isOccupied()) {
                    s.setOccupied(false); // Resource unlocking 
                    System.out.println("[!] BOOKING CANCELLED SUCCESSFULLY.");
                    System.out.println("[!] Room " + spaceId + " is now available.");
                } else {
                    System.out.println("[!] This room is not currently booked.");
                }
                return;
            }
        }
        System.out.println("[!] Room ID not found.");
    }

    public boolean login(String user, String pass) {
        for (Member m : members) {
            if (String.valueOf(m.getMemberId()).equals(user) && String.valueOf(m.getPin()).equals(pass)) {
                currentUser = m;
                return true;
            }
        }
        return false;
    }

    public void registerMember(String name, int age, String user, String pass) {
        if (age <= 18) {
            System.out.println("[!] Invalid age input.");
            return;
        }
        String cleanId = user.trim();
        members.add(new Member(Integer.parseInt(cleanId), name, Integer.parseInt(pass), 0.0));
        System.out.println("[!] ACCOUNT CREATED SUCCESSFULLY!");
    }

    public Member getCurrentUser() { return currentUser; }
    public void logout() { currentUser = null; }
}
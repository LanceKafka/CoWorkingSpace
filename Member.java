public class Member {
    private int memberId;
    private String name;
    private int pin;
    private double bal;

    public Member(int memberId, String name, int pin, double bal) {
        this.memberId = memberId;
        this.name = name;
        this.pin = pin;
        this.bal = bal;
    }

    public double getBalance() { 
        return bal; 
        }
    public void setPin(int newPin) { 
        this.pin = newPin; 
        }

    public int getMemberId() { 
        return memberId; 
        }
    public String getName() { 
        return name; 
        }
    public int getPin() { 
        return pin; 
        }

}
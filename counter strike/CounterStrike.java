
// FlyWeight Pattern with example of Counter
// Strike Game
import java.util.Random;
import java.util.HashMap;

// A common interface for all players
interface Player {
    public void assignWeapon(String weapon);

    public void mission();
}

// Terrorist must have weapon and mission
class Terrorist implements Player {
    // Intrinsic Attribute
    private final String TASK;

    // Extrinsic Attribute
    private String weapon;

    public Terrorist() {
        TASK = "PLANT A BOMB";
    }

    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void mission() {
        System.out.println("Terrorist with weapon "
                + weapon + "|" + " Task is " + TASK);
    }
}

class CounterTerrorist implements Player {
    // Intrinsic Attribute
    private final String TASK;

    // Extrinsic Attribute
    private String weapon;

    public CounterTerrorist() {
        TASK = "DIFFUSE BOMB";
    }

    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void mission() {
        System.out.println("Counter Terrorist with weapon "
                + weapon + "|" + " Task is " + TASK);
    }
}

class PlayerFactory {
    private static HashMap<String, Player> hm = new HashMap<String, Player>();

    // Method to get a player
    public static Player getPlayer(String type) {
        Player p = null;
        if (hm.containsKey(type))
            p = hm.get(type);
        else {

            switch (type) {
                case "Terrorist":
                    System.out.println("Terrorist Created");
                    p = new Terrorist();
                    break;
                case "CounterTerrorist":
                    System.out.println("Counter Terrorist Created");
                    p = new CounterTerrorist();
                    break;
                default:
                    System.out.println("Unreachable code!");
            }

            // Once created insert it into the HashMap
            hm.put(type, p);
        }
        return p;
    }
}

public class CounterStrike {
    private static String[] playerType = { "Terrorist", "CounterTerrorist" };
    private static String[] weapons = { "AK-47", "Maverick", "Gut Knife", "Desert Eagle" };

    // Driver code
    public static void main(String args[]) {

        for (int i = 0; i < 10; i++) {

            Player p = PlayerFactory.getPlayer(getRandPlayerType());
            p.assignWeapon(getRandWeapon());
            p.mission();
        }
    }

    // Utility methods to get a random player type and
    // weapon
    public static String getRandPlayerType() {
        Random r = new Random();

        // Will return an integer between [0,2)
        int randInt = r.nextInt(playerType.length);

        // return the player stored at index 'randInt'
        return playerType[randInt];
    }

    public static String getRandWeapon() {
        Random r = new Random();

        int randInt = r.nextInt(weapons.length);

        return weapons[randInt];
    }
}
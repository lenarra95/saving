import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class GameProgress implements Serializable {
    public static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress (int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString () {
        return "GameProgress:" +
                "\nhealth = " + health +
                "\nweapons = " + weapons +
                "\nlevel = " + lvl +
                "\ndistance = " + distance;
    }

    public void saveGame (String nameFile) {
        byte[] saveFileText = this.toString().getBytes();
        try (BufferedOutputStream bufis = new BufferedOutputStream(new FileOutputStream("Games/savegames/" + nameFile))) {
            bufis.write(saveFileText);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

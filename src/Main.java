import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main (String[] args) {
        File saveGamesDir = new File("Games" ,"savegames");
        if (! saveGamesDir.exists()) {
            saveGamesDir.mkdirs();
        }
        new GameProgress(100, 12, 2, 2.45D).saveGame("gameProgress01Jan2022.dat");
        new GameProgress(44, 16, 8, 45.67D).saveGame("gameProgress12Jan2022.dat");
        new GameProgress(29, 22, 12, 60.33D).saveGame("gameProgress30Jan2022.dat");
        zipFiles();
    }

    public static void zipFiles() {
        File[] files = new File("Games/savegames").listFiles();
        if (files.length > 0) {
            try (ZipOutputStream zops = new ZipOutputStream(new FileOutputStream("Games/savegames/savegames.zip"))) {
                for (File file : files) {
                    FileInputStream fileInput = new FileInputStream(file);
                    ZipEntry entry = new ZipEntry(file.getName());
                    zops.putNextEntry(entry);
                    byte[] bytes = new byte[fileInput.available()];
                    fileInput.read(bytes);
                    zops.write(bytes);
                    zops.closeEntry();
                    fileInput.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                for (File file : files) {
                    file.delete();
                }
            }
        } else {
            System.out.println("Нет файлов для архивирования");
        }
    }
}

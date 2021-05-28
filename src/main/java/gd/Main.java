package gd;

import gd.enums.SortingCode;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        processFeatured(SortingCode.DEFAULT);
        generateTopDemons();
        processEpic(SortingCode.DEFAULT);
    }

    private static void processFeatured(SortingCode sortingCode) {
        String[] res = ResponseGenerator.processLevels(sortingCode);
        if (res == null) {
            logger.warn("Featured levels list is empty! No changes were made.");
            return;
        }
        for (int j = 0; j < 11; j++) {
            String prefix = difficultyFolderMap.get(j + 1) + " featured";
            writeToFile(sortingCode, prefix, j + 1, res[j].getBytes());
        }
        writeToFile(sortingCode, "Featured", 0, res[11].getBytes());
        writeToFile(SortingCode.LONGEST_DESCRIPTION, "Featured", 0, res[12].getBytes());
        writeToFile(SortingCode.DEFAULT, "Featured audio info", 0, res[13].getBytes());
        writeToFile(SortingCode.DEFAULT, "Featured audio info expanded", 0, res[14].getBytes());
        writeToFile(SortingCode.DEFAULT, "Featured builders info", 0, res[15].getBytes());
        logger.info("All featured lists are finished");
    }

    private static void processEpic(SortingCode sortingCode) {
        String[] res = ResponseGenerator.processLevels(sortingCode);
        if (res == null) {
            logger.warn("Epic levels list is empty! No changes were made.");
            return;
        }
        for (int j = 0; j < 11; j++) {
            String prefix = difficultyFolderMap.get(j + 1) + " epic";
            writeToFile(sortingCode, prefix, j + 1, res[j].getBytes());
        }
        writeToFile(sortingCode, "Epic", 0, res[11].getBytes());
        writeToFile(SortingCode.LONGEST_DESCRIPTION, "Epic", 0, res[12].getBytes());
        writeToFile(SortingCode.DEFAULT, "Epic audio info", 0, res[13].getBytes());
        writeToFile(SortingCode.DEFAULT, "Epic audio info expanded", 0, res[14].getBytes());
        writeToFile(SortingCode.DEFAULT, "Epic builders info", 0, res[15].getBytes());
        logger.info("All epic lists are finished");
    }

    private static void generateTopDemons() {
        String res = ResponseGenerator.generateTopDemonsList();
        if (res.isEmpty()) {
            return;
        }
        writeToFile(SortingCode.DEFAULT, "Top 50 popular demons", 0, res.getBytes());
        logger.info("Top-50 demon list finished");
    }


    private static void writeToFile(SortingCode sortingCode, String prefix, int difficultyCode, byte[] data) {
        FileOutputStream out;
        try {
            out = getFileOutputStream(sortingCode, prefix, difficultyCode);
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: refactor
    private static FileOutputStream getFileOutputStream(SortingCode sortingCode, String prefix, int difficultyFolder) throws IOException {
        FileOutputStream out;
        String baseFolder = "Statistics";
        Path path = Paths.get(baseFolder);
        if (!Files.exists(path))
            Files.createDirectories(path);
        baseFolder += "/";
        String folder = difficultyFolderMap.get(difficultyFolder);
        String secondFolder = "";
        if (!folder.equals("")) {
            String p = "Statistics/" + folder.trim();
            path = Paths.get(p);
            if (!Files.exists(path))
                Files.createDirectories(path);
            secondFolder = folder + "/";
        }

        out = new FileOutputStream(baseFolder + secondFolder + prefix + sortingFileSuffix.get(sortingCode));
        return out;
    }

    //Difficulties map for replace in text
    public static final Map<Integer, String> difficultyFolderMap = initDifficultyFolderMap();

    private static Map<Integer, String> initDifficultyFolderMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "");
        map.put(1, "Auto");
        map.put(2, "Easy");
        map.put(3, "Normal");
        map.put(4, "Hard");
        map.put(5, "Harder");
        map.put(6, "Insane");
        map.put(7, "Easy demon");
        map.put(8, "Medium demon");
        map.put(9, "Hard demon");
        map.put(10, "Insane demon");
        map.put(11, "Extreme demon");
        return map;
    }

    //Demon difficulties map for replace in text
    public static final Map<SortingCode, String> sortingFileSuffix = initSortingFileSuffix();

    private static Map<SortingCode, String> initSortingFileSuffix() {
        Map<SortingCode, String> map = new HashMap<>();
        map.put(SortingCode.DEFAULT, " list.md");
        map.put(SortingCode.DESCENDING_LIKES, " list with descending likes.md");
        map.put(SortingCode.ASCENDING_LIKES, " list with ascending likes.md");
        map.put(SortingCode.DESCENDING_DOWNLOADS, " list with descending downloads.md");
        map.put(SortingCode.ASCENDING_DOWNLOADS, " list with ascending downloads.md");
        map.put(SortingCode.LONGEST_DESCRIPTION, " list with longest descriptions.md");
        return map;
    }
}

package project.solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Solution {

    private static final Logger LOGGER = LoggerFactory.getLogger(Solution.class);

    public static String BASE_DIRECTORY;
    public static final ConcurrentHashMap<String, Object> TASK_OUTPUT = new ConcurrentHashMap<>();

    private String fileName;
    private String baseKey;

    public Solution(String baseDirectory, String fileName, String baseKey) {
        BASE_DIRECTORY = baseDirectory;
        this.fileName = fileName;
        this.baseKey = baseKey;
    }

    public void solve() {
        //LOGGER.info(baseKey + " Entry");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(BASE_DIRECTORY + "\\" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Yaml yaml = new Yaml();
        Map<String, Object> mp = yaml.load(inputStream);
        new WorkFlow((LinkedHashMap<String, Object>) mp.get(baseKey), baseKey);
        //LOGGER.info(baseKey + " Exit");
    }

    public static void addTaskOutput(String key, Object value) {
        TASK_OUTPUT.put("$(" + key + ")", value);
    }

}

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Configuration {
    private static final String CONFIG_FILE = "config.properties";

    private Map<String, List<Integer>> arraysMap;

    public Configuration() {
        loadProperties();
    }

    public Map<String, List<Integer>> getArraysMap() {
        return arraysMap;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE);
                return;
            }

            Properties properties = new Properties();
            properties.load(input);
            arraysMap = parseProperties(properties);

            // Add a null list for testing
            arraysMap.put("emptyArray",Collections.emptyList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, List<Integer>> parseProperties(Properties properties) {
        Map<String, List<Integer>> result = new HashMap<>();

        for (String propertyName : properties.stringPropertyNames()) {
            String propertyValue = properties.getProperty(propertyName);
            if (!propertyValue.startsWith("#")) { // Ignore comments
                List<Integer> array = parseStringToList(propertyValue);
                result.put(propertyName, array);
            }
        }

        return result;
    }

    private List<Integer> parseStringToList(String arrayString) {
        return Arrays.stream(arrayString.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}

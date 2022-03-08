package project.solution;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadYaml {
    public void readYaml() {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\gopika\\Desktop\\KLA\\DataSet\\Milestone1\\Milestone1B.yaml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //BlockingQueue
        Yaml yaml = new Yaml();
        //Customer customer = yaml.load(inputStream);
        //System.out.println(customer.toString());
        Map<String, Object> mp = yaml.load(inputStream);
        //System.out.println(mp.toString());
        new WorkFlow((LinkedHashMap<String, Object>) mp.get("M1B_Workflow"),"M1B_Workflow");


    }
}

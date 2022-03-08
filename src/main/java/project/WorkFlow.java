package project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class WorkFlow {

    String type;
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlow.class);

    public void readYaml() {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\gopika\\Desktop\\KLA\\DataSet\\Milestone1\\Milestone1A.yaml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //BlockingQueue
        Yaml yaml = new Yaml();
        //Customer customer = yaml.load(inputStream);
        //System.out.println(customer.toString());
        ArrayList<Map<String,Object>> mp= new ArrayList<>();
        //System.out.println(mp.toString());

        for(Object obj: yaml.loadAll(inputStream)){
            Map<String,Object> hmp= (Map<String, Object>) obj;
            mp.add(hmp);
            //System.out.println(hmp.get("M1A_Workflow"));
            //type=
            LOGGER.info("<workflow> <function>");
        }
        //System.out.println(mp.get("Type").toString());
    }
}

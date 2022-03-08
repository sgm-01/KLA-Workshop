package project.solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkFlow {

    LinkedHashMap<String, Object> flow;
    String path;
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlow.class);
    WorkFlow(LinkedHashMap<String, Object> flow,String path){
        this.path=path;
        LOGGER.info(path+" Entry");
     this.flow = flow;
        //System.out.println(flow.get("Type"));
        if (((String) flow.get("Type")).equals("Flow")) {
            FlowType ft = new FlowType(flow,path);
        }else{
            TaskType tt=new TaskType(flow,path);
        }
        LOGGER.info(path+" Exit");
    }

        }
        //System.out.println(mp.get("Type").toString());



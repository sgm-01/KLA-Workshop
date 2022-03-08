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

    private LinkedHashMap<String, Object> flow;
    private String path;

    WorkFlow(LinkedHashMap<String, Object> flow, String path) {
        this.path = path;
        this.flow = flow;
        if (((String) flow.get("Type")).equals("Flow")) {
            new FlowType(flow, path);
        } else {
            new TaskType(flow, path);
        }
    }

}
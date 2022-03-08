package project.solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TaskType {
    String function;
    LinkedHashMap<String,Object> inputs;
    ArrayList<String> outputs;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskType.class);
    TaskType(LinkedHashMap<String,Object>flow,String path){
        function=(String) flow.get("Function");
        inputs=(LinkedHashMap<String,Object>)flow.get("Inputs");

        LOGGER.info(path+" Executing "+function+" ("+inputs.get("FunctionInput")+", "+inputs.get("ExecutionTime")+")");
        Integer sleepTime=Integer.parseInt((String) inputs.get("ExecutionTime"));
        try {
            Thread.sleep(sleepTime*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

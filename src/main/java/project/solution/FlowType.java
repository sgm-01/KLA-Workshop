package project.solution;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class FlowType {
    String Execution;
    LinkedHashMap<String,Object> Activities;
    String path;
    FlowType(LinkedHashMap<String,Object> flow,String path){
        //System.out.println("flowtype");
        this.path=path;
        Execution=(String)flow.get("Execution");
        Activities=(LinkedHashMap<String,Object>) flow.get("Activities");

        Set<String> ks= Activities.keySet();
        for(String s:ks){
            //System.out.println((LinkedHashMap<String,Object>)Activities.get(s));
            new WorkFlow((LinkedHashMap<String,Object>)Activities.get(s),path+"."+s);

        }

    }
}

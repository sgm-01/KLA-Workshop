package project.solution;

import java.util.LinkedHashMap;
import java.util.Set;



public class FlowType {
    String Execution;
    LinkedHashMap<String, Object> Activities;
    String path;

    class ConcurrentFlowType extends Thread {
        String s;
        ConcurrentFlowType(String s){
            this.s=s;
        }
        public void run() {
                new WorkFlow((LinkedHashMap<String, Object>) Activities.get(s), path + "." + s);
                //System.out.println(Thread.);
        }
    }

    FlowType(LinkedHashMap<String, Object> flow, String path) {
        //System.out.println("flowtype");
        this.path = path;
        Execution = (String) flow.get("Execution");
        Activities = (LinkedHashMap<String, Object>) flow.get("Activities");


        if (Execution.equals("Sequential")) {
            Set<String> ks = Activities.keySet();
            for (String s : ks) {
                //System.out.println((LinkedHashMap<String,Object>)Activities.get(s));
                new WorkFlow((LinkedHashMap<String, Object>) Activities.get(s), path + "." + s);

            }
        } else {
            Set<String> ks = Activities.keySet();
            for (String s : ks) {
                //System.out.println((LinkedHashMap<String,Object>)Activities.get(s));
                ConcurrentFlowType cft = new ConcurrentFlowType(s);
                cft.start();
            }
            
        }

    }
}

package project.solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class TaskType {
    String function;
    String condition;
    LinkedHashMap<String, Object> inputs;
    boolean skip;
    ArrayList<String> outputs;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskType.class);

    TaskType(LinkedHashMap<String, Object> flow, String path) {
        skip = false;
        condition = (String) flow.get("Condition");
        inputs = (LinkedHashMap<String, Object>) flow.get("Inputs");
        function = (String) flow.get("Function");

        if (condition != null) {
            String[] parsed = condition.split(" ");

            // LOGGER.info(path + " Waiting for " + parsed[0]);
            // LOGGER.info(path + " Print at wait " + Solution.TASK_OUTPUT);
            while (!Solution.TASK_OUTPUT.containsKey(parsed[0])) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // LOGGER.info(path + " Waiting done for " + parsed[0]);

            String taskOutput = String.valueOf(Solution.TASK_OUTPUT.get(parsed[0]));

            String[] expression = condition.split(" ");
            switch (expression[1]) {
                case ">":
                    skip = Integer.parseInt(taskOutput) > Integer.parseInt(expression[2]);
                    break;
                case ">=":
                    skip = Integer.parseInt(taskOutput) >= Integer.parseInt(expression[2]);
                    break;
                case "<":
                    skip = Integer.parseInt(taskOutput) < Integer.parseInt(expression[2]);
                    break;
                case "<=":
                    skip = Integer.parseInt(taskOutput) <= Integer.parseInt(expression[2]);
                    break;
            }

            skip = !skip;

            // LOGGER.info(path + " " + skip);
        }

        if (Objects.equals(function, "TimeFunction")) {
            String functionInput = (String) inputs.get("FunctionInput");
            if (functionInput.startsWith("$")) {
                // LOGGER.info(path + " Input waiting for " + functionInput);
                while (!Solution.TASK_OUTPUT.containsKey(functionInput)) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // LOGGER.info("Got value " + Solution.TASK_OUTPUT.get(functionInput));
                inputs.put("FunctionInput", Solution.TASK_OUTPUT.get(functionInput));
            }
        }

        LOGGER.info(path + " Entry");

        if (skip) {
            LOGGER.info(path + " Skipped");
            LOGGER.info(path + " Exit");
            return;
        }

        if (Objects.equals(function, "TimeFunction")) {
            LOGGER.info(path + " Executing " + function + " (" + inputs.get("FunctionInput") + ", " + inputs.get("ExecutionTime") + ")");
            Integer sleepTime = Integer.parseInt((String) inputs.get("ExecutionTime"));
            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (Objects.equals(function, "DataLoad")) {
            String fileName = (String) inputs.get("Filename");
            LOGGER.info(path + " Executing " + function + " (" + fileName + ")");
            Path filePath = Paths.get(Solution.BASE_DIRECTORY + "\\" + fileName);

            long lines = 0;
            try {
                lines = Files.lines(filePath).parallel().count() - 1;
            } catch (IOException e) {
                e.printStackTrace();
            }

            Solution.addTaskOutput(path + ".NoOfDefects", lines);
            // LOGGER.info("" + Solution.TASK_OUTPUT);
        }

        LOGGER.info(path + " Exit");
    }
}

package ca.mestevens.unity.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoFailureException;

public class UnityMenuCommands {
	
	private final String unity;
	private final String project;
	private ProcessRunner processRunner;
	
	public UnityMenuCommands(ProcessRunner processRunner, final String unity, final String project) {
		this.processRunner = processRunner;
		this.unity = unity;
		this.project = project;
	}
	
	public void syncMonoDevelopProject() throws MojoFailureException {
		List<String> commandList = new ArrayList<String>();
		commandList.add(unity);
		commandList.add("-projectPath");
		commandList.add(project);
		commandList.add("-executeMethod");
		commandList.add("UnityEditor.SyncVS.SyncSolution");
		commandList.add("-batchmode");
		commandList.add("-quit");
		commandList.add("-logFile");
		processRunner.killProcessWithName("Unity");
		int returnValue = processRunner.runProcess(null, commandList.toArray(new String[commandList.size()]));
		processRunner.checkReturnValue(returnValue);
	}

	public void runTests(String testPlatform, String testResults) throws MojoFailureException {
		List<String> commandList = new ArrayList<String>();
		commandList.add(unity);
		commandList.add("-projectPath");
		commandList.add(project);
		commandList.add("-runTests");
		commandList.add("-testPlatform");
		commandList.add(testPlatform);
		if (testResults != null) {
			commandList.add("-testResults");
			commandList.add(testResults);
		}
		commandList.add("-batchmode");
		commandList.add("-logFile");
		processRunner.killProcessWithName("Unity");
		int returnValue = processRunner.runProcess(null, commandList.toArray(new String[commandList.size()]));
		processRunner.checkReturnValue(returnValue);
	}

	public void executeMethod(String executeMethod) throws MojoFailureException {
		List<String> commandList = new ArrayList<String>();
		commandList.add(unity);
		commandList.add("-projectPath");
		commandList.add(project);
		commandList.add("-executeMethod");
		commandList.add(executeMethod);
		commandList.add("-batchmode");
		commandList.add("-quit");
		commandList.add("-logFile");
		processRunner.killProcessWithName("Unity");
		int returnValue = processRunner.runProcess(null, commandList.toArray(new String[commandList.size()]));
		processRunner.checkReturnValue(returnValue);
	}
}

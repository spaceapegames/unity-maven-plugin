package ca.mestevens.unity;

import ca.mestevens.unity.utils.ProcessRunner;
import ca.mestevens.unity.utils.UnityMenuCommands;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * Goal which runs the tests in unity
 *
 * @goal unity-run-tests
 * 
 * @phase test
 */

public class UnityRunTests extends AbstractMojo {

	/**
	 * @parameter property="project"
	 * @readonly
	 * @required
	 */
	public MavenProject project;

	/**
	 * @parameter property="unity.path" default="/Applications/Unity/Unity.app/Contents/MacOS/Unity"
	 * @readonly
	 * @required
	 */
	public String unity;

	/**
	 * @parameter property="testPlatform" default="editmode"
	 * @readonly
	 * @required
	 */
	public String testPlatform;

	/**
	 * @parameter property="testResults" default="target/TestResults.xml"
	 * @readonly
	 */
	public String testResults;

	private final ProcessRunner processRunner;

	public UnityRunTests() { this.processRunner = new ProcessRunner(this.getLog()); }

	public void execute() throws MojoExecutionException, MojoFailureException {
		UnityMenuCommands menuCommands = new UnityMenuCommands(new ProcessRunner(getLog()), unity, project.getBasedir().getAbsolutePath());
		menuCommands.runTests(testPlatform, testResults);
	}

}

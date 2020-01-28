package ca.mestevens.unity;

import ca.mestevens.unity.utils.ProcessRunner;
import ca.mestevens.unity.utils.UnityMenuCommands;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "unity-execute-method")
public class UnityExecuteMethod extends AbstractMojo {

	@Parameter(property = "project", readonly = true, required = true)
	public MavenProject project;

	@Parameter(property = "unity.path", defaultValue = "/Applications/Unity/Unity.app/Contents/MacOS/Unity", readonly = true, required = true)
	public String unity;

	@Parameter(property = "executeMethod", readonly = true, required = true)
	public String executeMethod;

	private final ProcessRunner processRunner;

	public UnityExecuteMethod() { this.processRunner = new ProcessRunner(this.getLog()); }

	public void execute() throws MojoExecutionException, MojoFailureException {
		UnityMenuCommands menuCommands = new UnityMenuCommands(new ProcessRunner(getLog()), unity, project.getBasedir().getAbsolutePath());
		menuCommands.executeMethod(executeMethod);
	}

}

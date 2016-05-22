package fr.inria.astor.junitexec;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runners.model.InitializationError;

/**
 * This class runs a JUnit test case.
 * 
 * @author Matias Martinez, matias.martinez@inria.fr
 * 
 */
public class JUnitMethodTestExecutor extends JUnitTestExecutor {

	List<String> successTest = new ArrayList<String>();

	List<String> failTest = new ArrayList<String>();

	public static void main(String[] arg) throws Exception, InitializationError {

		JUnitMethodTestExecutor re = new JUnitMethodTestExecutor();

		Result result = re.run(arg);
		
		System.out.println(re.createOutput(result));

		//System.exit(0);
	}

	@Override
	public Result run(String[] arg) throws ClassNotFoundException {
		if (!arg[0].contains("#"))
			throw new IllegalArgumentException("Arg must be 'classname#methodName'");
		String[] classAndMethod = arg[0].split("#");

		TestFilter tf = new TestFilter();
		Class c = Class.forName(classAndMethod[0]);
		if (tf.acceptClass(c)) {
			JUnitTestExecutor re = new JUnitTestExecutor();
			Request request = Request.method(Class.forName(classAndMethod[0]), classAndMethod[1]);

			Result result = new JUnitCore().run(request);
			return result;
		}
		return null;
	}

}

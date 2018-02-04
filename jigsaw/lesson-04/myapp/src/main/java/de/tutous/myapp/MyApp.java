package de.tutous.myapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import de.tutous.myutil.MyUtil;

public class MyApp {

	public static void main(String[] args) throws IOException {
		System.out.println("ProcessHandle-Infos: " + MyUtil.getProcessInfos());

		printProperties("MyApp.class.getResourceAsStream(\"/test.properties\")", "\t\t\t\t",
				MyApp.class.getResourceAsStream("/test.properties"));
		printProperties("MyApp.class.getResourceAsStream(\"test.properties\")", "\t\t\t\t",
				MyApp.class.getResourceAsStream("test.properties"));
		System.out.println();

		printProperties("MyApp.class.getModule().getResourceAsStream(\"test.properties\")", "\t\t\t",
				MyApp.class.getModule().getResourceAsStream("test.properties"));
		printProperties("MyApp.class.getModule().getResourceAsStream(\"/test.properties\")", "\t\t\t",
				MyApp.class.getModule().getResourceAsStream("/test.properties"));
		System.out.println();

		printProperties("MyApp.class.getResourceAsStream(\"/de/tutous/myapp/test.properties\")", "\t\t",
				MyApp.class.getResourceAsStream("/de/tutous/myapp/test.properties"));
		printProperties("MyApp.class.getResourceAsStream(\"de/tutous/myapp/test.properties\")", "\t\t",
				MyApp.class.getResourceAsStream("de/tutous/myapp/test.properties"));
		printProperties("MyApp.class.getModule().getResourceAsStream(\"de/tutous/myapp/test.properties\")", "\t",
				MyApp.class.getModule().getResourceAsStream("de/tutous/myapp/test.properties"));
		System.out.println();

		printProperties("MyApp.class.getClassLoader().getResourceAsStream(\"META-INF/test.properties\")", "\t",
				MyApp.class.getClassLoader().getResourceAsStream("META-INF/test.properties"));
		printProperties("MyApp.class.getModule().getResourceAsStream(\"META-INF/test.properties\")", "\t\t",
				MyApp.class.getModule().getResourceAsStream("META-INF/test.properties"));
		System.out.println();

		printProperties("MyUtil.class.getResourceAsStream(\"/test.properties\")", "\t\t\t\t",
				MyUtil.class.getResourceAsStream("/test.properties"));
		printProperties("MyUtil.class.getResourceAsStream(\"test.properties\")", "\t\t\t\t",
				MyUtil.class.getResourceAsStream("test.properties"));		
		printProperties("MyUtil.class.getModule().getResourceAsStream(\"/de/tutous/myutil/test.properties\")", "",
				MyUtil.class.getModule().getResourceAsStream("/de/tutous/myutil/test.properties"));
		printProperties("MyUtil.class.getModule().getResourceAsStream(\"de/tutous/myutil/test.properties\")", "",
				MyUtil.class.getModule().getResourceAsStream("de/tutous/myutil/test.properties"));
	}

	private static void printProperties(String prefix, String tabs, InputStream inputStream) throws IOException {

		if (Objects.nonNull(inputStream)) {

			Properties properties = new Properties();
			properties.load(inputStream);
			String from = properties.getProperty("from");
			System.out.println(prefix + tabs + "  from=" + from);

		} else {
			System.out.println(prefix + tabs + "  from=" + null);
		}

	}

}

package victor.training.spring;

import org.h2.tools.Server;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.sql.SQLException;

public class StartDatabase {
	public static void main(String[] args) throws SQLException {
		deletePreviousDBContents();

		System.out.println("Started DB...");

//		Server.main();
		org.h2.tools.Server.createTcpServer(/*
				"-tcpPort",
				"9093",
				"-baseDir",
				"c:\\Users\\victo\\h2",
				"-trace"*/
		).start();
	}

	private static void deletePreviousDBContents() {
		File userHomeFolder = new File(System.getProperty("user.home"));
		if (!userHomeFolder.isDirectory()) {
			throw new IllegalArgumentException("Could not locate userHome");
		}
		System.out.println("Assuming user home folder is: " + userHomeFolder.getAbsolutePath());
		File databasePath = new File(userHomeFolder, "source/db");
		if (databasePath.isDirectory()) {
			System.out.println("Deleting previous db contents...");
			boolean ok = FileSystemUtils.deleteRecursively(databasePath);
			if (!ok) {
				System.err.println("Could not delete folder " + databasePath.getAbsolutePath());
			} else {
				System.out.println("SUCCESS");
			}
		} else {
			System.out.println("Nothing found at db path: " + databasePath.getAbsolutePath());
		}
		System.out.println("Check out folder: ~/source/db/database/db");

	}
}

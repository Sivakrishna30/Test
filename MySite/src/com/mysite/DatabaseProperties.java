package com.mysite;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseProperties {

	private static DatabaseProperties dp;
	private String url;
	private String username;
	private String password;

	public DatabaseProperties() {
		try {
			File file = new File("db.properties");
			FileReader reader = new FileReader(file);
			Properties p = new Properties();
			p.load(reader);
			url=p.getProperty("url");
			username=p.getProperty("username");
			password=p.getProperty("password");
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DatabaseProperties getInstance() {
		if(dp==null)
		{
			dp=new DatabaseProperties();
		}
		return dp;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void createTable() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(DatabaseProperties.getInstance().url,
					DatabaseProperties.getInstance().username, DatabaseProperties.getInstance().password);
			String sql = "CREATE TABLE IF NOT EXISTS public.\"Buddies\"\r\n" + "(\r\n"
					+ "    name character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
					+ "    gender character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
					+ "    age integer NOT NULL,\r\n"
					+ "    phone character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
					+ "    email character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
					+ "    city character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
					+ "    password character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
					+ "    CONSTRAINT \"Buddies_pkey\" PRIMARY KEY (name)\r\n" + ")\r\n" + "\r\n"
					+ "TABLESPACE pg_default;\r\n" + "\r\n" + "ALTER TABLE IF EXISTS public.\"Buddies\"\r\n"
					+ "    OWNER to postgres;";
			 Statement statement = connection.createStatement();
			 statement.executeQuery(sql);
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

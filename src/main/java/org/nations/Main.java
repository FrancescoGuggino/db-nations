package org.nations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/db-nations";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "rootpassword";

    private final static String SQL_NATION = """
            select c.name AS COUNTRY, c.country_id as ID, r.name AS REGION, c2.name AS CONTINENT
            from countries c
            join regions r on c.region_id = r.region_id
            join continents c2 on r.continent_id = c2.continent_id
            order by c.name asc
            """;

    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)){

            try (PreparedStatement ps = connection.prepareStatement(SQL_NATION)){
                try (ResultSet rs = ps.executeQuery()){
                    while (rs.next()){
                        String nomeCountry = rs.getString("COUNTRY");
                        int id = rs.getInt("ID");
                        String region = rs.getString("REGION");
                        String continent = rs.getString("CONTINENT");
                    }
                }
            }
        }
    }
}

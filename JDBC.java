package jdbc;
import java.sql.*;
public class JDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String databaseName = "mining";
        String username="root";
        String password="";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            
            connection = DriverManager.getConnection(url+databaseName, username, password);
            statement = connection.createStatement();
            System.out.println("Connection established to database.");
            
            String select1 = "SELECT biome, COUNT(vein_size) AS num FROM netherite GROUP BY biome ORDER BY vein_size ASC;";
            String select2 = "SELECT name FROM piglin INNER JOIN netherite ON netherite.x_chunk=piglin.home_x AND netherite.y_chunk=piglin.home_y WHERE vein_size=0;";
            String listAllFromNetherite = "SELECT * FROM netherite;";
            String listAllFromPiglin = "SELECT * FROM piglin;";
            String insertInto = "INSERT INTO piglin VALUES(4,\"Gabi\",1,0,0);";
            String delete = "DELETE FROM piglin WHERE id=4;";
            
            rs = statement.executeQuery(select1);
            
            System.out.println("Netherites by biome:\n");
            System.out.println("Biome:\tNumber of netherites");
            while(rs.next()){
                System.out.println(rs.getString("biome")+":\t"+rs.getString("num"));
            }
            
            rs = statement.executeQuery(select2);
            System.out.println("\nName of piglins, where netherite was found under their homes:");
            System.out.println("Names:");
            while(rs.next()){
                System.out.println(rs.getString("name"));
            }
            
            System.out.println("\nDemonstrating insert into:");
            System.out.println("\nPiglin table before the insert into:");
            System.out.println("id, name, hoglins_slain, home_x, home_y");
            rs = statement.executeQuery(listAllFromPiglin);
            while(rs.next()){
                System.out.println("Id: "+rs.getString("id")+", Name: "+rs.getString("name")+", Hoglins slain: "+
                rs.getString("hoglins_slain")+", Home x coord: "+rs.getString("home_x")+", Home y coord: "+rs.getString("home_y"));
            }
            
            statement.executeUpdate(insertInto);
            
            System.out.println("\nPiglin table after the insert into:");
            System.out.println("id, name, hoglins_slain, home_x, home_y");
            rs = statement.executeQuery(listAllFromPiglin);
            while(rs.next()){
                System.out.println("Id: "+rs.getString("id")+", Name: "+rs.getString("name")+", Hoglins slain: "+
                rs.getString("hoglins_slain")+", Home x coord: "+rs.getString("home_x")+", Home y coord: "+rs.getString("home_y"));
            }
            
            System.out.println("\nDemonstrating delete:");
            
            System.out.println("\nPiglin table before the delete:");
            System.out.println("id, name, hoglins_slain, home_x, home_y");
            rs = statement.executeQuery(listAllFromPiglin);
            while(rs.next()){
                System.out.println("Id: "+rs.getString("id")+", Name: "+rs.getString("name")+", Hoglins slain: "+
                rs.getString("hoglins_slain")+", Home x coord: "+rs.getString("home_x")+", Home y coord: "+rs.getString("home_y"));
            }
            
            statement.executeUpdate(delete);
            
            System.out.println("\nPiglin table after the delete:");
            System.out.println("id, name, hoglins_slain, home_x, home_y");
            rs = statement.executeQuery(listAllFromPiglin);
            while(rs.next()){
                System.out.println("Id: "+rs.getString("id")+", Name: "+rs.getString("name")+", Hoglins slain: "+
                rs.getString("hoglins_slain")+", Home x coord: "+rs.getString("home_x")+", Home y coord: "+rs.getString("home_y"));
            }
        
        }catch(SQLException exc){
            exc.printStackTrace();
        }finally{
            try{ if(connection != null) connection.close(); }
            catch(SQLException exc){ exc.printStackTrace(); }
            try{ if(statement != null) statement.close();   }
            catch(SQLException exc){ exc.printStackTrace(); }
            try{ if(rs != null) rs.close();                 }
            catch(SQLException exc){ exc.printStackTrace(); }
        }
    }  
}
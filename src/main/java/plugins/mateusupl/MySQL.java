package plugins.mateusupl;

import org.bukkit.entity.Player;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class MySQL {

    public static void criarTabela(){
        try {
            Connection c = abrirConexao();

            PreparedStatement st = c.prepareStatement("CREATE TABLE IF NOT EXISTS PLAYERS(ID VARCHAR(50), MOBKILLS INT);");
            st.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean hasJogador(Player p){
        UUID id = p.getUniqueId();
        try {
            Connection c = abrirConexao();
            PreparedStatement st = c.prepareStatement("SELECT MOBKILLS FROM PLAYERS WHERE ID = ?;");
            st.setString(1,id.toString());
            ResultSet rs = st.executeQuery();
            boolean result = rs.next();
            c.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static void addJogador(Player p){
        UUID id = p.getUniqueId();
        try {
            Connection c = abrirConexao();
            PreparedStatement st = c.prepareStatement("INSERT INTO PLAYERS VALUES(?,0);");
            st.setString(1,id.toString());
            st.executeUpdate();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getKills(Player p){
        UUID id = p.getUniqueId();

        try {
            Connection c = abrirConexao();
            PreparedStatement st = c.prepareStatement("SELECT MOBKILLS FROM PLAYERS WHERE ID = ?;");
            st.setString(1,id.toString());
            ResultSet rs = st.executeQuery();
            int value = 0;
            if(rs.next()){
                value = rs.getInt("MOBKILLS");
            };
            c.close();
            return value;
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public static void addKill(Player p){
        UUID id = p.getUniqueId();

        int newvalue = getKills(p) + 1;

        try {
            Connection c = abrirConexao();
            PreparedStatement st = c.prepareStatement("UPDATE PLAYERS SET MOBKILLS = ? WHERE ID = ?;");
            st.setString(1, String.valueOf(newvalue));
            st.setString(2,id.toString());
            st.executeUpdate();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection abrirConexao(){

        try {
            String host = "localhost";
            String user = "root";
            String password = "";
            String port = "3306";
            String database = "servidor";
            String type = "jdbc:mysql://";
            String url = type+host+":"+port+"/"+database;
            return DriverManager.getConnection(url, user, password);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}

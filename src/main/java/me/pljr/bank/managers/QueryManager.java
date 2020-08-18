package me.pljr.bank.managers;

import me.pljr.bank.Bank;
import me.pljr.bank.enums.BankType;
import me.pljr.bank.objects.CorePlayer;
import me.pljr.pljrapi.database.DataSource;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class QueryManager {
    private final DataSource dataSource;

    public QueryManager(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void loadPlayerSync(UUID uuid){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM bank_players WHERE uuid=?"
            );
            preparedStatement.setString(1, uuid.toString());
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()){
                Bank.getPlayerManager().setCorePlayer(uuid, new CorePlayer(
                        results.getDouble("amount"),
                        BankType.valueOf(results.getString("tier"))
                ));
            }else{
                Bank.getPlayerManager().setCorePlayer(uuid, new CorePlayer());
            }
            dataSource.close(connection, preparedStatement, results);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void savePlayer(UUID uuid){
        Bukkit.getScheduler().runTaskAsynchronously(Bank.getInstance(), ()->{
           try {
               CorePlayer corePlayer = Bank.getPlayerManager().getCorePlayer(uuid);

               Connection connection = dataSource.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(
                    "REPLACE INTO bank_players VALUES (?,?,?)"
               );
               preparedStatement.setString(1, uuid.toString());
               preparedStatement.setDouble(2, corePlayer.getAmount());
               preparedStatement.setString(3, corePlayer.getBankType().toString());
               preparedStatement.executeUpdate();
               dataSource.close(connection, preparedStatement, null);
           }catch (SQLException e){
               e.printStackTrace();
           }
        });
    }

    public void setupTables(){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS bank_players (" +
                            "uuid char(36) NOT NULL PRIMARY KEY," +
                            "amount double NOT NULL," +
                            "tier varchar(16) NOT NULL);"
            );
            preparedStatement.executeUpdate();
            dataSource.close(connection, preparedStatement, null);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

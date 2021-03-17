package me.pljr.bank.managers;

import me.pljr.bank.config.BankType;
import me.pljr.bank.objects.BankPlayer;
import me.pljr.pljrapispigot.database.DataSource;

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

    public BankPlayer loadPlayer(UUID uuid){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM bank_players WHERE uuid=?"
            );
            preparedStatement.setString(1, uuid.toString());
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()){
                dataSource.close(connection, preparedStatement, results);
                return new BankPlayer(
                        uuid,
                        results.getDouble("amount"),
                        BankType.valueOf(results.getString("tier"))
                );
            }
            dataSource.close(connection, preparedStatement, results);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new BankPlayer(uuid);
    }

    public void savePlayer(BankPlayer bankPlayer){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "REPLACE INTO bank_players VALUES (?,?,?)"
            );
            preparedStatement.setString(1, bankPlayer.getUniqueId().toString());
            preparedStatement.setDouble(2, bankPlayer.getAmount());
            preparedStatement.setString(3, bankPlayer.getBankType().toString());
            preparedStatement.executeUpdate();
            dataSource.close(connection, preparedStatement, null);
        }catch (SQLException e){
            e.printStackTrace();
        }
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

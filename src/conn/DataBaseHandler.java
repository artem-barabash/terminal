package conn;

import com.itextpdf.text.DocumentException;
import instrument.CreatePDFfilesClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.OperationItem;
import models.Person;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataBaseHandler {

    public static Connection getConnection(){
        try {
            String driver = "com.postgresql.jdbc.Driver";
            String url = "jdbc:postgresql://localhost:5432/mydb";
            String user = "postgres";
            String password = "1234";
            Connection con = DriverManager.getConnection(url, user, password);

            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean checkForNumberCode(String numberCode) throws SQLException {
        ArrayList <String> array = new ArrayList<>();
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE number = '" + numberCode  + "'" );
        while (rs.next()) {
            String current = rs.getString("number");
            array.add(current);
            return true;
        }

        return  false;
    }

    public static void saveOperation(String numberCode, float summ){
        try {
            String terminal = "Термінал №1";
            Person person = searchSecondPersonForDocument(numberCode);

            String sql = "INSERT INTO operations VALUES (?, ?, ?, ?)";
            CreatePDFfilesClass pdf = new CreatePDFfilesClass();

            Thread.sleep(1000);
            transakciaAddBalance(person.getNumberCode(), summ);

            Thread.sleep(1000);
            pdf.createDocumentOfTransakcia(terminal, person, summ);

            java.util.Date dt = new java.util.Date();
            Timestamp ts = new Timestamp(dt.getTime());

            PreparedStatement pstmt = getConnection().prepareStatement(sql);
            pstmt.setString(1, terminal);
            pstmt.setString(2, person.getNumberCode());
            pstmt.setFloat(3, summ);
            pstmt.setTimestamp(4, ts);

            pstmt.executeUpdate();
        }catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            return;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveOperationOfService(String numberCodeFirst, String service, float summ){
        try {
            //String terminal = "Термінал №1";
            Person person = searchSecondPersonForDocument(numberCodeFirst);

            String sql = "INSERT INTO operations VALUES (?, ?, ?, ?)";
            CreatePDFfilesClass pdf = new CreatePDFfilesClass();

            Thread.sleep(1000);
            //transakciaAddBalance(person.getNumberCode(), summ);

            Thread.sleep(1000);
            pdf.createDocumentOfTransakciaForService(person, service, summ);

            java.util.Date dt = new java.util.Date();
            Timestamp ts = new Timestamp(dt.getTime());

            PreparedStatement pstmt = getConnection().prepareStatement(sql);
            pstmt.setString(1, person.getNumberCode());
            pstmt.setString(2, service);
            pstmt.setFloat(3, summ);
            pstmt.setTimestamp(4, ts);

            pstmt.executeUpdate();
        }catch (SQLException | InterruptedException | DocumentException | IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void saveOperationOfCharityFond(float summ){
        try {
            String terminal = "Термінал №1";
            String numberCode = "CharityFond";

            String sql = "INSERT INTO operations VALUES (?, ?, ?, ?)";


            Thread.sleep(1000);
            transakciaAddBalance(numberCode, summ);

            Thread.sleep(1000);

            java.util.Date dt = new java.util.Date();
            Timestamp ts = new Timestamp(dt.getTime());

            PreparedStatement pstmt = getConnection().prepareStatement(sql);
            pstmt.setString(1, terminal);
            pstmt.setString(2, numberCode);
            pstmt.setFloat(3, summ);
            pstmt.setTimestamp(4, ts);

            pstmt.executeUpdate();
        }catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }

    private static void transakciaAddBalance(String personCode, float summ) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String sql = "UPDATE users SET balance = balance +" + summ  + "  WHERE number = '" + personCode + "'";
        stmt.execute(sql);
    }

    public static Person searchSecondPersonForDocument(String codeNumber) throws SQLException {
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE number = '" + codeNumber + "'");
        Person personSecond = null;
        while(rs.next()){
            ObservableList<OperationItem> incomeList = FXCollections.observableArrayList();
            ObservableList<OperationItem> costsList = FXCollections.observableArrayList();
            LocalDate date = rs.getObject("birthday", LocalDate.class);
            personSecond = new Person(rs.getString("number"), rs.getString("phone"), rs.getString("password")
                    , rs.getString("surname"), rs.getString("name"), rs.getString("fathername"), date, rs.getFloat("balance"), incomeList , costsList);
        }
        return personSecond;
    }


}

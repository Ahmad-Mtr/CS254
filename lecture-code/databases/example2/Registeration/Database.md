```java
package Registration;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {

    private Connection con;

    public Database() {
    }

    public void connect() throws Exception {

        if (con != null) {
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }

        String url = "jdbc:mysql://localhost:3306/cs254";

        con = DriverManager.getConnection(url, "swingUser", "swingUser123");
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Can't close connection");
            }
        }
    }

    public int checkUserName(String userName) {
        String sql = "call checkUserName(?, ?)"; // SQL calling a stored procedure with parameters
        CallableStatement callableStatement;
        try {
            callableStatement = con.prepareCall(sql);

            callableStatement.setString(1, userName); // Input parameter
            callableStatement.registerOutParameter(2, Types.INTEGER); // Output paramete
            callableStatement.execute(); // Execute the stored procedure
            int count = callableStatement.getInt(2); // Get the output parameter (salary)
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 1;
        }

    }

    public User login(String userName, String password) throws SQLException {
        String selectSql = "select * from users  where user_name = ? and password = ?";
        PreparedStatement selectStatement = null;
        User user = null;
        try {
            selectStatement = con.prepareStatement(selectSql);;
            selectStatement.setString(1, userName);
            selectStatement.setString(2, password);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserName(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setFullName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setMajor(rs.getString(6));
                String skills = rs.getString(7);
                String skill[] = skills.split(";");
                for (String s : skill) {
                    user.addSkill(s);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            selectStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public void register(User usr) throws SQLException {

        String insertSql = "insert into users (user_name, password, full_name, gender, email, major, skills) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

        String userName = usr.getUserName();
        String password = usr.getPassword();
        String fullName = usr.getFullName();
        String gender = usr.getGender();
        String major = usr.getMajor();
        String email = usr.getEmail();
        StringBuilder skillList = new StringBuilder();
        for (String skill : usr.getSkills()) {
            skillList.append(skill);
            skillList.append(";");
        }
        String skills = skillList.toString();

        int col = 1;
        insertStatement.setString(col++, userName);
        insertStatement.setString(col++, password);
        insertStatement.setString(col++, fullName);
        insertStatement.setString(col++, gender);
        insertStatement.setString(col++, email);
        insertStatement.setString(col++, major);
        insertStatement.setString(col++, skills);

        insertStatement.executeUpdate();

        insertStatement.close();
    }

    public void load() throws SQLException {

        String sql = "select user_name, full_name, mail, , employment_status, tax_id, us_citizen, gender, occupation from people order by name";
        Statement selectStatement = con.createStatement();

        ResultSet results = selectStatement.executeQuery(sql);

        while (results.next()) {
            int id = results.getInt("id");
            String name = results.getString("name");
            String age = results.getString("age");
            String emp = results.getString("employment_status");
            String taxId = results.getString("tax_id");
            boolean isUs = results.getBoolean("us_citizen");
            String gender = results.getString("gender");
            String occ = results.getString("occupation");
        }

        results.close();
        selectStatement.close();
    }
}

```
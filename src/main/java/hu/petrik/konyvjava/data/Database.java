package hu.petrik.konyvjava.data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static final String URL = "jdbc:mysql://localhost/library";
    static final String USER = "root";
    static final String PASS = "";
    private Connection conn;

    public Database() throws SQLException {
        this.conn = DriverManager.getConnection(URL,USER,PASS);
    }

    public void deleteKonyv(Konyv konyv) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM books WHERE id = "+konyv.getId()+";");
    }

    public List<Konyv> fetchKonyvek() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM books");
        List<Konyv> konyvek = new ArrayList<>();
        while (result.next()){
            konyvek.add(new Konyv(
                    result.getInt("id"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getInt("publish_year"),
                    result.getInt("page_count")
            ));
        }
        return konyvek;
    }

    public int countkolcsonzesek(int bookId) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM rentals WHERE book_id = "+bookId+";");
        int count = 0;
        while (result.next()){
            count++;
        }
        return count;
    }
}

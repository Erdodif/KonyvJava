package hu.petrik.konyvjava;

import hu.petrik.konyvjava.data.Database;
import hu.petrik.konyvjava.data.Konyv;

import java.sql.SQLException;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Statisztika {
    static List<Konyv> konyvek;
    static Database database;

    public static void main(String[] args) {
        try {
            database = new Database();
            konyvek = database.fetchKonyvek();
            System.out.println("500 oldalnál hosszabb könyvek száma: " +
                    konyvek.stream()
                            .filter(konyv -> konyv.getPageCount() > 500)
                            .count()
            );
            System.out.println("Leghosszabb könyv:\n" +
                    konyvek.stream()
                            .max(Comparator.comparingInt(Konyv::getPageCount)).get()
            );
            Map.Entry<String,Long> legtobbKonyvesSzerzo = konyvek.stream()
                    .collect(Collectors.groupingBy(
                            Konyv::getAuthor,Collectors.counting()
                    )).entrySet()
                    .stream()
                    .max(Comparator.comparingLong(Map.Entry::getValue))
                    .get();
            System.out.println("Legtöbb könyvvel rendelkező szerző: "+
                    legtobbKonyvesSzerzo.getKey()
                    );
            System.out.print("Adjon meg egy könyv címet: ");
            String konyvcim = (new Scanner(System.in)).nextLine();
            long darab = database.countkolcsonzesek(konyvek.stream().filter(konyv -> konyv.getTitle().equals(konyvcim)).findFirst().get().getId());
            System.out.println("A megadott könyv "+darab+"x lett kikölcsönözve");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

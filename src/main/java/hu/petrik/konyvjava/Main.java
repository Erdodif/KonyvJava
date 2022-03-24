package hu.petrik.konyvjava;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if(Arrays.asList(args).contains("--stat")){
            Statisztika.main(null);
        }else{
            HelloApplication.main(null);
        }
    }
}

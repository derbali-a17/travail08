package ca.qc.bdeb.travail08;

public class Utils {
    public static String convertSecondsToString(int qtySeconds){
        int minutes = (qtySeconds % 3600) / 60;
        int seconds = qtySeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public  static int convertStringSecondsToInt(String stringSeconds){
        int minutes = Integer.parseInt(stringSeconds.substring(0, 2));
        int seconds = Integer.parseInt(stringSeconds.substring(3));
        return  minutes * 60  + seconds;
    }
}

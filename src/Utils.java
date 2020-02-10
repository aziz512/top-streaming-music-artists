// Aziz Yokubjonov - aziz.yokubjonov@gmail.com
// GitHub: @aziz512
// azizwrites.xyz

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

class Utils {
    public static int findIndex(String artistName, String[][] data, int trueLength) {
        for (int i = 0; i < trueLength; i++) {
            if (data[i][0].equals(artistName)) {
                return i;
            }
        }
        return -1;
    }

    public static List<String> getArtistsFromSongName(String songName) {
        // i.e. (feat. Eminem & 50 Cent)
        Pattern featOrWith = Pattern.compile("\\((with|feat\\.) (.*)\\)", Pattern.CASE_INSENSITIVE);
        Matcher m = featOrWith.matcher(songName);
        if (m.find()) {
            String artists = m.group(2);
            return Arrays.asList(artists.split(" & "));
        }
        return new ArrayList<String>();
    }

    public static ArrayList<String> parseCSVLine(String line) {
        ArrayList<String> result = new ArrayList<>();
        boolean isQuoted = false;
        ArrayList<Integer> delimeterLocations = new ArrayList<>();

        // walk the string char-by-char and find true delimeters
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            if (currentChar == '"') { // handle quote escaping
                if (line.length() > i + 1) {
                    char nextChar = line.charAt(i + 1);
                    if (nextChar == '"') {
                        i++; // skip the escaped quote
                        continue;
                    }
                }
                isQuoted = !isQuoted; // no escaping, value wrapped in quotes
            } else if (!isQuoted && currentChar == ',') {
                delimeterLocations.add(i);
            }
        }

        if (delimeterLocations.size() == 0) { // only one column in line
            result.add(line);
            return result;
        }
        for (int i = 0; i < delimeterLocations.size(); i++) {
            int previous = 0;
            if (i != 0) {
                previous = delimeterLocations.get(i - 1) + 1;
            }
            result.add(line.substring(previous, delimeterLocations.get(i)));
        }
        // add column after last delimeter (aka last column)
        int lastDelimeter = delimeterLocations.get(delimeterLocations.size() - 1);
        result.add(line.substring(lastDelimeter + 1));
        return result;
    }
}
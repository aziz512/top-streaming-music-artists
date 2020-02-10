// Aziz Yokubjonov - aziz.yokubjonov@gmail.com
// GitHub: @aziz512
// azizwrites.xyz

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final String datasetPath = "../data/charts.csv";
        File csv = new File(datasetPath);
        Scanner reader = new Scanner(csv);
        System.out.println("\r\r");
        System.out.println("Reading file: " + datasetPath);
        // skip headers
        reader.nextLine();
        reader.nextLine();

        // Potential edge case where #artists > artistsMeta.length -- map would be better & faster
        // [ [artist1Name, count], [artist2Name, count] ]
        String[][] artistsMeta = new String[200][2];
        int lastArtistIndex = -1;

        TopStreamingArtists artistNames = new TopStreamingArtists();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            // [rank, song name, artist, streams, url]
            String[] data = Utils.parseCSVLine(line).toArray(new String[0]);

            if (data[2].contains(" ")) { // trim quotation marks
                data[2] = data[2].substring(1, data[2].length() - 1);
            }

            // get artists listed in (feat. X) in the song name
            List<String> featuredArtists = new ArrayList<String>(Utils.getArtistsFromSongName(data[1]));
            featuredArtists.add(data[2]); // add main artist
            for (String artist : featuredArtists) {
                int artistIndex = Utils.findIndex(artist, artistsMeta, lastArtistIndex + 1);
                if (artistIndex == -1) { // artist not in the nested arrays yet
                    artistsMeta[++lastArtistIndex] = new String[] { artist, "1" };
                    artistNames.insert(artist);
                } else {
                    artistsMeta[artistIndex][1] = "" + (Integer.parseInt(artistsMeta[artistIndex][1]) + 1);
                }
            }
        }

        final String artistsStatsPath = "../out/artists-stats.txt";
        System.out.println("Writing artists' stats to: " + artistsStatsPath);
        PrintWriter writer = new PrintWriter(new File(artistsStatsPath));
        for (int i = 0; i < lastArtistIndex + 1; i++) {
            writer.println(artistsMeta[i][0] + " " + artistsMeta[i][1]);
        }
        writer.close();

        final String sortedArtistsPath = "../out/sorted-artists.txt";
        System.out.println("Writing sorted list of artists to: " + sortedArtistsPath);
        PrintWriter artistsWriter = new PrintWriter(new File(sortedArtistsPath));
        artistNames.displayList(str -> artistsWriter.println(str));
        artistsWriter.close();

        System.out.println("Done.");
    }
}
# Top Streaming Artists

The program analyzes a CSV file from [Spotify Charts](https://spotifycharts.com/) and parses the number of times every artist appears on the charts. Additionally, it uses LinkedList to store the list of artists in the alphabetical order. Finally, the program writes the two results to the /out directory as text files.

The `charts.csv` in the `data` directory is an extract for Week ending Jan 23, 2020 (US). It's a great example to showcase the program's ability to handle certain edge cases (i.e. commas in the artist name, featured artists, etc).
## Dependencies

* [Java 8](https://docs.oracle.com/javase/8/docs/api/index.html)

## Setup
1) `git clone https://github.com/aziz512/top-streaming-music-artists.git`
2) `cd top-streaming-music-artists/src`
3) `javac Main.java && java Main` 
4) check `/out` directory for results

## Folder Structure

* Code is saved into the `src` folder.
* Data is parsed from the `data` folder.
* Output placed in the `out` folder
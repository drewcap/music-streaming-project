import java.util.ArrayList;
import java.util.Arrays;

public class Album extends SongList {
    private String artist;


//     Creates an Album with the given name, artist, and songs.
    public Album(String name, String artist, Song[] songs) {
        super(name);
        this.artist = artist;
        setSongs(new ArrayList<>(Arrays.asList(songs))); 
        //creates a new ArrayList from the songs Array
        // .asList() returns a list view of the specified array (here its songs)
    }

//     Returns a String containing a list of Songs in the Album.
    public String toString() {
        String returned = getName() + "\n";
        returned += "by " + artist + "\n";
        for (Song song : getSongs()) {
            returned += "-" + song.getName() + "\n";
        }

        return returned;
    }
}

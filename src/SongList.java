import java.util.ArrayList;

//helps us inherit things to albums and playlists
// quite literally just a list of songs
public abstract class SongList {
    private String name;
    private ArrayList<Song> songs; //not a HashSet to maintain order 


//  Creates an empty SongList with the given name.
    public SongList(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

//	Returns the name of the SongList
    public String getName() {
        return name;
    }


//  Returns a String containing a list of Songs in the SongList.
    public String toString() {
        String rtn = name + "\n";
        for (Song song : songs) {
            rtn += "-" + song.getName() + "\n";
        }
        return rtn;
    }

//  Returns the ArrayList of Songs.
    public ArrayList<Song> getSongs() {
        return songs;
    }

//     Changes the ArrayList of Songs.
//     songs is the new ArrayList of Songs we want
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}

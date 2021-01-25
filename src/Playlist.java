
public class Playlist extends SongList {
    public Playlist(String name) {
        super(name);
    }

//     Adds a song to the Playlist from those in the Database.
//     passed in songName is the name of the Song
//     Returns true if the Song was found in Database, otherwise false.
    public boolean addSong(String songName) {
        Song song = Database.findSong(songName);

        if (song == null) {
            System.out.println(" - A Song with that name was not found - ");
            return false;
        }

        getSongs().add(song);
        return true;
    }

//    Removes the specified Song from the Playlist.
//    passed in songName is the name of the Song being removed
//    Returns true if the Song was removed, false if it was not found
    public boolean removeSong(String songName) {
        return getSongs().remove(Database.findSong(songName));
    }
}

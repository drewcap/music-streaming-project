import java.util.ArrayList;
import java.util.Collections;

public class MusicPlayer {
    private User currentUser;
    private Song currentlyPlaying;
    private ArrayList<Song> songQueue;

//     Creates a new MusicPlayer with no currentUser, no Song currentlyPlaying and a songQueue
    public MusicPlayer() {
        currentUser = null;
        currentlyPlaying = null;
        songQueue = new ArrayList<>();
    }

//     Logs in in the currentUser with the given credentials.
//        username is the username of the User
//        password is the password of the User
//     Returns true if the User is logged in, false if not found
    public boolean login(String username, String password) {
        currentUser = Database.findUser(username, password);
        if (currentUser == null) {
            return false;
        }
        return true;
    }

//     Creates a new user with the given credentials.
//     returns true if the User is registered, false otherwise
    public boolean registerUser(String username, String password) {
        return Database.addUser(username, password);
    }

//     Adds song to the songQueue.
//     passed in song is the Song being added the songQueue
    public void addToQueue(Song song) {
        songQueue.add(song);
    }

//     Adds song to the songQueue.
//     passed in songs is the SongList being added the songQueue
    public void addToQueue(SongList songs) {
        songQueue.addAll(songs.getSongs());
        //.addAll appends ALL the parameter elements to the songQueue
    }

//     Moves to the next song in the songQueue
    public void nextSong() {
        if (songQueue.size() == 0) {
            System.out.println("Queue is Empty");
            currentlyPlaying = null;
            return;
        }

        currentlyPlaying = songQueue.get(0);
        songQueue.get(0).play();
        songQueue.remove(0);
    }

//     Shuffles the songQueue
    public void shuffle() {
        Collections.shuffle(songQueue);
        	//collections.shuffle works more broadly
        System.out.println("Queue was shuffled");
    }

//     Clears the songQueue.
    public void clearQueue() {
        songQueue = new ArrayList<>();
        System.out.println("Queue Cleared");
    }

@Override
//     Returns a String of this MusicPlayer.
    public String toString() {
        String returned =  "\nLogged in as: " + currentUser.getName() + "\n";

        if (currentlyPlaying == null) {
            returned += "No Song Playing";
        } else {
        	returned += currentlyPlaying;
        }
        return returned;
    }

//     Returns the currentUser of this
    public User getCurrentUser() {
        return currentUser;
    }
}

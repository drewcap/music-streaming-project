import java.util.*;

// Stores the songs, albums, and users in the Fotify database.
public class Database {
    private static HashSet<String> usernames = new HashSet<>();
    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, Album> albums = new HashMap<>();
    private static HashMap<String, Song> songs = new HashMap<>();


//     returns a User if a User with the given username and password exists in the Database.
//      returns the User with the given username and password, null if one does not exist
    
    public static User findUser(String username, String password) {
        if (!usernames.contains(username)) {
            System.out.println(" - A user with that username does not exist - ");
            return null;
        }

        User user = users.get(username);
        if (password.equals(user.getLoginInfo()[1])) { //user.getLoginInfo()[1] is the password stored in the User
            System.out.println("Logged In");
            return user;
        }

        System.out.println(" - Incorrect password - ");
        return null;
    }
    
//      Adds a User with the given username and password to the Database 
//    	  if one with the same username does not already exist
//     Passed in username is the username of the new User
//     Passed in password is the password of the new User
//     Returns true if the user was registered, false otherwise
     
    public static boolean addUser(String username, String password) {
        if (usernames.contains(username)) {
            System.out.println(" - A user with that username already exists - ");
            return false;
        }

        usernames.add(username);
        users.put(username, new User(username, password));
        System.out.println(" - " + username + " was successfully registered - ");
        return true;
    }

//     passed in name is the name of the Album being searched for
//     returns the Album with the given name, null if one does not exist in the Database
    public static Album findAlbum(String name) {
        return albums.get(name);
    }

//     passed in name is the name of the Song that is being searched for
//     returns the Song with the given name, null if one does not exist in the Database
    public static Song findSong(String name) {
        return songs.get(name);
    }

//     Adds the given Album to the Database if one with the same name does not already exist.
//     passed in album is the Album being added
    public static void addAlbum(Album album) {
        if (albums.containsKey(album.getName())) {
            System.out.println(" - Database already contains an Album with the same name - ");
        } else {
            albums.put(album.getName(), album);
        }
    }


//     Adds the given Song to the Database if one with the same name does not already exist.
//     the song passed in is the Song being added
    public static void addSong(Song song) {
        if (songs.containsKey(song.getName())) {
            System.out.println(" - Database already contains a Song with the same name - ");
        } else {
            songs.put(song.getName(), song);
        }
    }

//	Prints the names of all Songs stored in the Database.
    public static void printAllSongs() {
        Set<String> songList = songs.keySet();
        ArrayList<String> songNames = new ArrayList<>();

        for (String songName : songList) {
            songNames.add(songName);
        }

        Collections.sort(songNames);
        //Collections.sort() works similar to Arrays.sort() 

        for (String songName : songNames) {
            System.out.println(" - " + songName);
        }
    }
}

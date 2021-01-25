import java.util.HashMap;

public class User {
    private String[] loginInfo; //index 0 = username, index 1 = password
    private HashMap<String, Playlist> playLists;


//  Creates a new User with no playlists, given the parameters as new info.
    public User(String username, String password) {
        this.loginInfo = new String[]{username, password};
        this.playLists = new HashMap<>();
    }

//  Returns the loginInfo of the User.
//		a String array of length 2 in the format {username, password}
    public String[] getLoginInfo() {
        return loginInfo;
    }

//  Returns the Playlist with the given name from the User's list of Playlists.
    public Playlist getPlayList(String name) {
        return playLists.get(name);
    }

//  Creates an empty Playlist with the given (passed in) name.
    public void createPlaylist(String name) {
        playLists.put(name, new Playlist(name));
    }


//  Returns the username of the User.
    public String getName() {
        return loginInfo[0];
    }

//  Returns the HashMap containing the Playlists.
    public HashMap<String, Playlist> getPlayLists() {
        return playLists;
    }
}


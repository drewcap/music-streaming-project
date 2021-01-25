import java.util.Scanner;
import java.util.Set;

public class Driver {
    public static void main(String[] args) {
        MusicPlayer musicPlayer = new MusicPlayer();
        Scanner scanner = new Scanner(System.in);

        //Database.addSong(new Song("song", "artist", 123));
        Database.addSong(new Song("Let's Stay Together", "Al Green", 199));
        Database.addSong(new Song("Love and Happiness", "Al Green", 307));
        Database.addSong(new Song("La-La for You", "Al Green", 210));
        Database.addSong(new Song("Move on Up", "Curtis Mayfield", 166));
        Database.addSong(new Song("Ain't No Sunshine", "Bill Withers", 125));
        Database.addSong(new Song("Comfort Me", "Carla Thomas", 158));
        Database.addSong(new Song("Mustang Sally", "Wilson Pickett", 187));
        Database.addSong(new Song("(Sittin' On) the Dock of the Bay", "Otis Redding", 163));
        Database.addSong(new Song("My Girl", "Otis Redding", 175));
        Database.addSong(new Song("Love Train", "The O'Jays", 178));
        Database.addSong(new Song("Lovely Day", "Bill Withers", 255));
        Database.addSong(new Song("California Soul", "Marlena Shaw", 178));
        Database.addSong(new Song("Respect", "Aretha Franklin", 157));

        Database.addAlbum(new Album("Let's Stay Together", "Al Green", new Song[]{Database.findSong("Let's Stay Together"),
                Database.findSong("Love and Happiness"),
                Database.findSong("La-La for You")}));

        //Database.addUser("drew", "123");

        run(musicPlayer, scanner);
    }

//     loops the program.
//     passed in musicPlayer is the MusicPlayer being used
    public static void run(MusicPlayer musicPlayer, Scanner scanner) {
        loginScreen(musicPlayer, scanner);
        do {
            System.out.println(musicPlayer);
        } 
        while (continueToMainMenu(musicPlayer, scanner));
        
        //run(musicPlayer, scanner);
    }

//   The screen where the user logins into the MusicPlayer.
//   musicPlayer the MusicPlayer being used
//   scanner the Scanner being used
    public static void loginScreen(MusicPlayer musicPlayer, Scanner scanner) {
        while (true) {
            System.out.println("\n1: Login" +
                    "\n2: Create Account" +
                    "\n3: Quit");

            String response = scanner.nextLine();
            while (response.equals("1")) {
                System.out.println("Enter your username: ");
                String username = scanner.nextLine();
                System.out.println("Enter your password: ");
                String password = scanner.nextLine();

                if (musicPlayer.login(username, password)) {
                    break;
                }
            }

            while (response.equals("2")) {
                System.out.println("Create your username: ");
                String username = scanner.nextLine();
                System.out.println("Create your password: ");
                String password = scanner.nextLine();

                if (musicPlayer.registerUser(username, password)) {
                    break;
                }
            }

            if (response.equals("1")) {
                break;
            }

            if (response.equals("3")) {
                System.exit(0);
            }

            if (!response.equals("2")) {
                System.out.println("Please enter valid option 2");
            }
        }
    }

//     Users will choose from these options here in the menu
//     passed in musicPlayer is the MusicPlayer object being used
//     Returns true if the user wants to continue, false if they log out
    public static boolean continueToMainMenu(MusicPlayer musicPlayer, Scanner scanner) {
        System.out.println("\n1: Add Song to Queue" +
                "\n2: Next Song" +
                "\n3: Add Playlist to Queue" +
                "\n4: Add Album to Queue" +
                "\n5: Shuffle" +
                "\n6: Clear Queue" +
                "\n7: Create a Playlist" +
                "\n8: Edit Playlists" +
                "\n9: List Songs Available" +
                "\n0: Logout");

        String input = scanner.nextLine();

        switch (input) {
            case "1" : 
            	addSongToQueue(musicPlayer, scanner);
            	break;
            case "2" : 
            	musicPlayer.nextSong();
            	break;
            case "3" : 
            	addPlaylistToQueue(musicPlayer, scanner);
            	break;
            case "4" : 
            	addAlbumToQueue(musicPlayer, scanner);
            	break;
            case "5" : 
            	musicPlayer.shuffle();
            	break;
            case "6" : 
            	musicPlayer.clearQueue();
            	break;
            case "7" : 
            	createAPlaylist(musicPlayer, scanner);
            	break;
            case "8" : 
            	editAPlaylist(musicPlayer, scanner);
            	break;
            case "9" : 
            	Database.printAllSongs();
            	break;
            case "0" : {
                return false;
            }
        }
        return true;
    }

//     Helper method to add a Song to the musicPlayer's queue.
//     passed in musicPlayer the MusicPlayer being used
    public static void addSongToQueue(MusicPlayer musicPlayer, Scanner scanner) {
        System.out.println("What song would you like to add?");
        String songName = scanner.nextLine();
        Song song = Database.findSong(songName);

        if (song == null) {
            System.out.println(songName + " was not found");
        } else {
            musicPlayer.addToQueue(song);
            System.out.println(songName + " was added to your queue");
        }
    }

//     Helper method to create a Playlist for the currentUser in musicPlayer.
    public static void createAPlaylist(MusicPlayer musicPlayer, Scanner scanner) {
        System.out.println("Name your new Playlist: ");
        String name = scanner.nextLine();

        if (musicPlayer.getCurrentUser().getPlayList(name) == null) {
            musicPlayer.getCurrentUser().createPlaylist(name);
            System.out.println(name + " was created");
        } else {
            System.out.println(name + " already exists");
        }
        //run(musicPlayer, scanner);
    }

//     Helper method to list all the Playlists of the musicPlayer's currentUser.
    public static void listOfPlaylists(MusicPlayer musicPlayer) {
        Set<String> playLists = musicPlayer.getCurrentUser().getPlayLists().keySet();

        System.out.println("Playlists: ");
        for (String playlist : playLists) {
            System.out.println("- " + playlist);
        }
    }

//     Method that returns a Playlist for musicPlayer's currentUser.
//     Returns the Playlist that was requested
    public static Playlist pickAPlaylist(MusicPlayer musicPlayer, Scanner scanner) {
        listOfPlaylists(musicPlayer);
        System.out.println("Pick a Playlist by entering its name: ");
        String playlist = scanner.nextLine();

        return musicPlayer.getCurrentUser().getPlayLists().get(playlist);
    }

//     Adds a Playlist to the musicPlayer's queue.
    public static void addPlaylistToQueue(MusicPlayer musicPlayer, Scanner scanner) {
        Playlist playlist = pickAPlaylist(musicPlayer, scanner);

        if (playlist == null) {
            System.out.println("Invalid Playlist Name");
            return;
        }

        musicPlayer.addToQueue(playlist);
        System.out.println(playlist.getName() + " added to Queue");
    }

//     Adds an Album to the musicPlayer's queue.
    public static void addAlbumToQueue(MusicPlayer musicPlayer, Scanner scanner) {
        System.out.println("What Album would you like to add to your Queue?");
        String albumName = scanner.nextLine();
        Album album = Database.findAlbum(albumName);

        if (album == null) {
            System.out.println(albumName + " was not found in our Database");
        } else {
            musicPlayer.addToQueue(album);
            System.out.println(albumName + " was added to the Queue");
        }
    }

//     Edits a Playlist owned by the musicPlayer's currentUser.
    public static void editAPlaylist(MusicPlayer musicPlayer, Scanner scanner) {
        Playlist playlist = pickAPlaylist(musicPlayer, scanner);

        if (playlist == null) {
            System.out.println("Invalid Playlist Name");
            return;
        }

        System.out.println("1: Add a song to " + playlist.getName() +
                "\n2: Remove a song from " + playlist.getName() +
                "\n3: List Songs in " + playlist.getName() +
                "\n4: Delete " + playlist.getName() +
                "\nAll other inputs: Return to Main Menu");
        String input = scanner.nextLine();

        if (input.equals("1")) {
            System.out.println("What song would you like to add to " + playlist.getName() + "?");
            String songName = scanner.nextLine();

            if (playlist.addSong(songName)) {
                System.out.println(songName + " was added to " + playlist.getName());
            }
        } //end if 1
        else if (input.equals("2")) {
            System.out.println("What song would you like to add to " + playlist.getName() + "?");
            String songName = scanner.nextLine();
            if (playlist.removeSong(songName)) {
                System.out.println(songName + " was removed from " + playlist.getName());

            } //end if
            else {
                System.out.println(songName + " was not found in " + playlist.getName());
            }
        } //end else if 2
        else if (input.equals("3")) {
            System.out.println(playlist);
        } 
        else if (input.equals("4")) {
            musicPlayer.getCurrentUser().getPlayLists().remove(playlist.getName());
            System.out.println(playlist.getName() + " was deleted");
        }//end else if 4
    }
}

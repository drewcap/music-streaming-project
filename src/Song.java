public class Song {
    private String name;
    private String artist;
    private int duration; //seconds
    private int streams;


//     Creates a Song with the given name, artist, and duration.
//     parameter name is the name of the Song
//     parameter artist is the artist of the Song's name
//     parameter duration is the duration of the Song (in seconds)
    public Song(String name, String artist, int duration) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        streams = 0;
    }

//     Returns a String version of the Song.
    public String toString() {
        return name + "\n" +
                "by " + artist + "\n" +
                "Length : " + duration / 60 + ":" + String.format("%02d", duration % 60) + "\n" +
                "Total Plays : " + streams;
    }

//     Returns the name of the Song.
    public String getName() {
        return name;
    }

       @Override  //overrides the equals method of Song
//     Passed in object is what's being compared
//     Returns true if this has the same name as o, false otherwise
    public boolean equals(Object o) {
        if (o instanceof Song) {
            Song other = (Song) o;
            return this.name.equals(other.name);
        }

        return false;
    }

//	Increments the streams variable
    public void play() {
        streams++;
    }
}

import java.util.*;

public class RecentlyPlayedSong {

    private int capacity;
    private Map<String, LinkedList<BaseTest>> playlist;

    public RecentlyPlayedSong(int capacity) {
        this.capacity = capacity;
        this.playlist = new HashMap<>();
    }

    public void addSong(String song, String user) {
        if (playlist.containsKey(user)) {
            LinkedList<BaseTest> songs = playlist.get(user);
            songs.removeIf(pair -> pair.getSong().equals(song));
            songs.addFirst(new BaseTest(song, user));
        } else {
            LinkedList<BaseTest> songs = new LinkedList<>();
            songs.addFirst(new BaseTest(song, user));
            playlist.put(user, songs);
        }

        if (playlist.get(user).size() > capacity) {
            LinkedList<BaseTest> songs = playlist.get(user);
            songs.removeLast();
        }
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        if (!playlist.containsKey(user)) {
            return Collections.emptyList();
        }

        List<String> songs = new ArrayList<>();
        for (BaseTest pair : playlist.get(user)) {
            songs.add(pair.getSong());
        }

        return songs;
    }
}

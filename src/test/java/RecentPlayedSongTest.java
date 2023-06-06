import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class RecentPlayedSongTest {

    private RecentlyPlayedSong store;

    @DataProvider(name="testData")
    public Object[][] setUp() {
        Object[][] data = ReadDataFromExcel.getTestData("src/main/resources/testData.xlsx", "Sheet1");
        /*for(Object[] row : data){
            System.out.println(Arrays.toString(row));
        }*/

        return data;
    }

    @Test(dataProvider = "testData")
    public void testAddSongAndGetRecentlyPlayedSongs(double capacity, String songsPLayed, String recentPlaylist) {
        try {
            store = new RecentlyPlayedSong((int)capacity);

            Arrays.asList(songsPLayed.split(",")).forEach(e ->{
                store.addSong(e, "User1");
            });

            List<String> expectedSongs =Arrays.asList(recentPlaylist.split(","));
            List<String> recentlyPlayedSongs = store.getRecentlyPlayedSongs("User1");

            String result = (expectedSongs.containsAll(recentlyPlayedSongs))
                    ? "Playlist Contains Recent Played Songs" : "Playlist Does Not Contains Recent Played Songs";

           Assert.assertTrue(result.equals("Playlist Contains Recent Played Songs"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*@Test
    public void testGetRecentlyPlayedSongsWhenUserHasNotPlayedAnySongs() {
        List<String> expectedSongs = Collections.emptyList();
        List<String> recentlyPlayedSongs = store.getRecentlyPlayedSongs("User1");

        assertEquals(expectedSongs, recentlyPlayedSongs);
    }

    @Test
    public void testGetRecentlyPlayedSongsWhenUserHasPlayedLessSongsThanCapacity() {
        store.addSong("S1", "User1");
        store.addSong("S2", "User1");

        List<String> expectedSongs = Arrays.asList("S2", "S1");
        List<String> recentlyPlayedSongs = store.getRecentlyPlayedSongs("User1");

        assertEquals(expectedSongs, recentlyPlayedSongs);
    }*/

}

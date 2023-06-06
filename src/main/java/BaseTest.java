public class BaseTest {

    private String song;
    private String user;

    public BaseTest(String song, String user) {
        this.song = song;
        this.user = user;
    }

    public String getSong() {
        return song;
    }

    public String getUser() {
        return user;
    }
}

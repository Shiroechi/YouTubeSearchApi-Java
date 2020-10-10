package YouTubeSearchApi;

import YouTubeSearchApi.entity.YoutubeVideo;

import java.io.IOException;
import java.util.List;

public class TestYoutubeClientGetVideoInfo {
    public static void main(String[] args) {
        YoutubeClient client = new YoutubeClient();
        try {
            YoutubeVideo video = client.getInfoByVideoUrl("https://www.youtube.com/watch?v=wM4laths4-Y");
            System.out.println(video);
            video = client.getInfoByVideoId("wM4laths4-Y");
            System.out.println(video);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

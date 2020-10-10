package YouTubeSearchApi;


import YouTubeSearchApi.entity.YoutubeVideo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestYoutubeClientSearch {

    public static void main(String[] args) {

        YoutubeClient client = new YoutubeClient();

        List<YoutubeVideo> videos;

        try {
            videos = client.search("CHiCO Love Letter", 5);
            System.out.println(videos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

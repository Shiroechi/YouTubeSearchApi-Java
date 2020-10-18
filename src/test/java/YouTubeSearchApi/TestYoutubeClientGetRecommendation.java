package YouTubeSearchApi;

import YouTubeSearchApi.entity.YoutubePlaylist;
import YouTubeSearchApi.exception.NoResultFoundException;

import java.io.IOException;

public class TestYoutubeClientGetRecommendation {
    public static void main(String[] args) {
        YoutubeClient client = new YoutubeClient();
        try {
            YoutubePlaylist youtubePlaylist = client.getRecommendationPlaylist("oCrwzN6eb4Q");
            System.out.println(youtubePlaylist);
        } catch (IOException | NoResultFoundException e) {
            e.printStackTrace();
        }
    }
}

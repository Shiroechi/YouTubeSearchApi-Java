package YouTubeSearchApi;

import YouTubeSearchApi.entity.YoutubeVideo;
import YouTubeSearchApi.exception.NoResultFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestYoutubeClientSearch {

  public static void main(String[] args) {

    YoutubeClient client = new YoutubeClient();

    List<YoutubeVideo> videos;

    try {
      videos = client.search("yoasobi", 5, "en");
      System.out.println(videos.size());
      System.out.println(videos.toString());
    } catch (IOException | NoResultFoundException e) {
      e.printStackTrace();
    }
  }
}

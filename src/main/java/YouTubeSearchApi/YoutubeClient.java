package YouTubeSearchApi;

import YouTubeSearchApi.entity.YoutubePlaylist;
import YouTubeSearchApi.entity.YoutubeVideo;
import YouTubeSearchApi.exception.NoResultFoundException;
import YouTubeSearchApi.utility.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeClient {

    private String YOUTUBE_BASE_URL;

    public YoutubeClient() {
        this.YOUTUBE_BASE_URL = "https://www.youtube.com/";
    }

    public List<YoutubeVideo> search(String keywords, int maxResults) throws IOException, NoResultFoundException {
        String startFeature = "window[\"ytInitialData\"]";
        String encodedKeywords = URLEncoder.encode(keywords, StandardCharsets.UTF_8.toString());
        String searchUrl = this.YOUTUBE_BASE_URL + "results?search_query=" + encodedKeywords;

        // try get feature 3 times
        String pageContent = "";
        boolean foundFeatureFlag = false;
        for (int i = 0; i < 3; i++) {
            pageContent = Utils.httpRequest(searchUrl);
            if (pageContent.contains(startFeature)) {
                foundFeatureFlag = true;
                break;
            }
        }

        if (!foundFeatureFlag) {
            throw new NoResultFoundException("What you searched was unfortunately not found or doesn't exist. keywords: " + keywords);
        }


        // + startFeature.length() + 3. because we want to get rid of the startFeature and " = ".
        // And get only the Json data
        int startIndex = pageContent.indexOf(startFeature) + startFeature.length() + 3;

        final String startIndexToEndIndex = pageContent.substring(startIndex, pageContent.length() - 1);

        int endIndex = startIndexToEndIndex.indexOf("};");

        String jsonString = startIndexToEndIndex.substring(0, endIndex) + "}";

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        JsonArray contents = jsonObject.get("contents").getAsJsonObject()
                                  .get("twoColumnSearchResultsRenderer").getAsJsonObject()
                                  .get("primaryContents").getAsJsonObject()
                                  .get("sectionListRenderer").getAsJsonObject()
                                  .get("contents").getAsJsonArray()
                                  .get(0).getAsJsonObject()
                                  .get("itemSectionRenderer").getAsJsonObject()
                                  .get("contents").getAsJsonArray();

        // Flags
        int counter = 0;
        List<YoutubeVideo> videos = new ArrayList<YoutubeVideo>();

        for (JsonElement videoJsonElement : contents) {
            JsonObject videoJsonObject = videoJsonElement.getAsJsonObject();
            if (videoJsonObject.has("videoRenderer")) {
                JsonObject videoData = videoJsonObject.get("videoRenderer").getAsJsonObject();

                // Initiate YoutubeVideo object instance & parse video renderer data
                YoutubeVideo video = YoutubeVideo.getParsedVideoRenderer(videoData);

                videos.add(video);

                counter += 1;
                if (counter >= maxResults) {
                    break;
                }
            }
        }
        return videos;
    }

    public YoutubeVideo getInfoByVideoId(String id) throws IOException {
        String videoUrl = this.YOUTUBE_BASE_URL + "watch?v=" + id;
        String requestUrl = "https://noembed.com/embed?url=" + videoUrl;

        String pageContent = Utils.httpRequest(requestUrl);

        Gson gson = new Gson();

        JsonObject videoObject = gson.fromJson(pageContent, JsonObject.class);

        return YoutubeVideo.getParsedOEmbedObject(videoObject);
    }

    public YoutubeVideo getInfoByVideoUrl(String videoUrl) throws IOException {
        String requestUrl = "https://noembed.com/embed?url=" + videoUrl;

        String pageContent = Utils.httpRequest(requestUrl);

        Gson gson = new Gson();

        JsonObject videoObject = gson.fromJson(pageContent, JsonObject.class);

        return YoutubeVideo.getParsedOEmbedObject(videoObject);
    }

    public YoutubePlaylist getRecommendation(String videoId) throws IOException, NoResultFoundException {
        String requestUrl = this.YOUTUBE_BASE_URL + "watch?v=" + videoId;

        String pageContent = Utils.httpRequest(requestUrl);

        Pattern pattern = Pattern.compile(
                "\"compactRadioRenderer\"(.+?)\"compactVideoRenderer\"",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = pattern.matcher(pageContent);

        if (matcher.find()) {
            String compactRadioRenderer = matcher.group();
            compactRadioRenderer = "{" + compactRadioRenderer.substring(0,
                    compactRadioRenderer.length() - ",{\"compactVideoRenderer\"".length());

            Gson gson = new Gson();

            JsonObject compactRadioRendererObject = gson.fromJson(compactRadioRenderer, JsonObject.class)
                                                        .get("compactRadioRenderer")
                                                        .getAsJsonObject();
            YoutubePlaylist youtubePlaylist = YoutubePlaylist.parseCompactRadioRenderer(compactRadioRendererObject);

            return youtubePlaylist;
        }
        else {
            throw new NoResultFoundException("Cannot find any recommendation for videoId: " + videoId);
        }
    }
}

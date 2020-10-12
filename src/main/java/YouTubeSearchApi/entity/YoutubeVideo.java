package YouTubeSearchApi.entity;

import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeVideo {

    private String id;
    private String url;
    private String title;
    private String thumbnailUrl;
    private String author;
    private String duration;

    public YoutubeVideo() {

    }

    public YoutubeVideo(String id, String title, String thumbnailUrl, String author, String duration) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.author = author;
        this.duration = duration;
    }

    public YoutubeVideo(String id, String url, String title, String thumbnailUrl, String author, String duration) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.author = author;
        this.duration = duration;
    }

    /**
     * Parse given Youtube's Video Renderer Json Object into YoutubeVideo Object
     *
     * @param videoRendererObject
     * @returns null.
     */
    public void parseVideoRenderer(JsonObject videoRendererObject) {
        String videoId = videoRendererObject.get("videoId").getAsString();

        String videoTitle = videoRendererObject.get("title").getAsJsonObject()
                .get("runs").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("text").getAsString();

        String videoThumbnailUrl = videoRendererObject.get("thumbnail").getAsJsonObject()
                .get("thumbnails").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("url").getAsString();

        String videoDuration = "";
        if (videoRendererObject.has("lengthText"))
            videoDuration = videoRendererObject.get("lengthText").getAsJsonObject()
                    .get("simpleText").getAsString().replace(".", ":");

        String videoAuthor = videoRendererObject.get("longBylineText").getAsJsonObject()
                .get("runs").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("text").getAsString();

        String videoUrl = "https://www.youtube.com/watch?v=" + videoId;

        this.id = videoId;
        this.url = videoUrl;
        this.title = videoTitle;
        this.thumbnailUrl = videoThumbnailUrl;
        this.duration = videoDuration;
        this.author = videoAuthor;
    }

    /**
     * Parse given Youtube's Video Renderer Json Object into YoutubeVideo Object
     *
     * @param videoRendererObject
     * @returns YoutubeVideo instance.
     */
    public static YoutubeVideo getParsedVideoRenderer(JsonObject videoRendererObject) {
        String videoId = videoRendererObject.get("videoId").getAsString();

        String videoTitle = videoRendererObject.get("title").getAsJsonObject()
                .get("runs").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("text").getAsString();

        String videoThumbnailUrl = videoRendererObject.get("thumbnail").getAsJsonObject()
                .get("thumbnails").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("url").getAsString();

        String videoDuration = "";
        if (videoRendererObject.has("lengthText"))
            videoDuration = videoRendererObject.get("lengthText").getAsJsonObject()
                    .get("simpleText").getAsString().replace(".", ":");

        String videoAuthor = videoRendererObject.get("longBylineText").getAsJsonObject()
                .get("runs").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("text").getAsString();

        String videoUrl = "https://www.youtube.com/watch?v=" + videoId;

        return new YoutubeVideo(videoId, videoUrl,videoTitle, videoThumbnailUrl, videoAuthor, videoDuration);
    }

    public void parseOEmbedObject(JsonObject videoOEmbedObject) {
        String videoUrl = videoOEmbedObject.get("url").getAsString();
        String videoId = "";

        // found a complete regex pattern to get video id from all kind of youtube video url on stackoverflow. say no more.
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(videoUrl);
        if (matcher.find()) {
            videoId = matcher.group();
        }

        String videoTitle = videoOEmbedObject.get("title").getAsString();
        String authorName = videoOEmbedObject.get("author_name").getAsString();
        String thumbnailUrl = videoOEmbedObject.get("thumbnail_url").getAsString();
        String duration = "";

        this.id = videoId;
        this.url = videoUrl;
        this.title = videoTitle;
        this.thumbnailUrl = thumbnailUrl;
        this.duration = duration;
        this.author = authorName;
    }

    public static YoutubeVideo getParsedOEmbedObject(JsonObject videoOEmbedObject) {
        String videoUrl = videoOEmbedObject.get("url").getAsString();
        String videoId = "";

        // found a complete regex pattern to get video id from all kind of youtube video url on stackoverflow. say no more.
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(videoUrl);
        if (matcher.find()) {
            videoId = matcher.group();
        }

        String videoTitle = videoOEmbedObject.get("title").getAsString();
        String authorName = videoOEmbedObject.get("author_name").getAsString();
        String thumbnailUrl = videoOEmbedObject.get("thumbnail_url").getAsString();
        String duration = "";

        return new YoutubeVideo(videoId, videoUrl, videoTitle, thumbnailUrl, authorName, duration);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "YoutubeVideo{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", author='" + author + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}

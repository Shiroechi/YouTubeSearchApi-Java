package YouTubeSearchApi.entity;

import com.google.gson.JsonObject;

public class YoutubePlaylist {

    private String id;
    private String url;
    private String title;
    private String thumbnailUrl;
    private String metadataUrl;

    public YoutubePlaylist(String id, String url, String title, String thumbnailUrl, String metaDataUrl) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.metadataUrl = metaDataUrl;
    }

    public static YoutubePlaylist parseCompactRadioRenderer(JsonObject compactRadioRendererObject) {
        String playlistId = compactRadioRendererObject.get("playlistId").getAsString();

        String thumbnailUrl = compactRadioRendererObject.get("thumbnail").getAsJsonObject()
                .get("thumbnails").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("url").getAsString();

        String title = compactRadioRendererObject.get("title").getAsJsonObject()
                .get("simpleText").getAsString();

        String playlistUrl = compactRadioRendererObject.get("shareUrl").getAsString()
                .replace("\\u0026", "&");

        String metaDataUrl = compactRadioRendererObject.get("navigationEndpoint").getAsJsonObject()
                .get("commandMetadata").getAsJsonObject()
                .get("webCommandMetadata").getAsJsonObject()
                .get("url").getAsString()
                .replace("\\u0026", "&");

        return new YoutubePlaylist(playlistId, playlistUrl, title, thumbnailUrl, metaDataUrl);
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

    public void setUrl(String url) {
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

    public String getMetadataUrl() {
        return metadataUrl;
    }

    public void setMetadataUrl(String metadataUrl) {
        this.metadataUrl = metadataUrl;
    }

    @Override
    public String toString() {
        return "YoutubePlaylist{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", metadataUrl='" + metadataUrl + '\'' +
                '}';
    }
}

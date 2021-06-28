package com.example.serviceexample.models;

public class Id{
    public String kind;
    public String playlistId;
    public String channelId;
    public String videoId;

    public Id(String kind, String playlistId, String channelId, String videoId) {
        this.kind = kind;
        this.playlistId = playlistId;
        this.channelId = channelId;
        this.videoId = videoId;
    }

    public Id() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}

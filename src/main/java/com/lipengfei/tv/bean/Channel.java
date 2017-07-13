package com.lipengfei.tv.bean;

import com.google.android.exoplayer2.source.MediaSource;

import java.util.Map;

/**
 * Created by Lee on 2017/7/13.
 */

public class Channel {
    /**
     * Src : http://192.168.18.235/live/CCTV1HD/CCTV1HD.m3u8
     * ChannelNum : 1
     * ViewID : 29
     * ChannelPicURL : http://mres.cleartv.cn/default/c6cfbb28669a8547a11c244e73a6d371_14848204102.png
     * ChannelPicURLRelatvie : /Main/resource/c6cfbb28669a8547a11c244e73a6d371_14848204102.png
     * SrcBackup : null
     * ChannelName : {"zh-CN":"CCTV1HD","en-US":"CCTV1HD"}
     */

    private String Src;
    private int ChannelNum;
    private int ViewID;
    private String ChannelPicURL;
    private String ChannelPicURLRelatvie;
    private Map<String, String> ChannelName;

    private MediaSource mediaSource;

    public MediaSource getMediaSource() {
        return mediaSource;
    }

    public void setMediaSource(MediaSource mediaSource) {
        this.mediaSource = mediaSource;
    }

    public String getSrc() {
        return Src;
    }

    public void setSrc(String Src) {
        this.Src = Src;
    }

    public int getChannelNum() {
        return ChannelNum;
    }

    public void setChannelNum(int ChannelNum) {
        this.ChannelNum = ChannelNum;
    }

    public int getViewID() {
        return ViewID;
    }

    public void setViewID(int ViewID) {
        this.ViewID = ViewID;
    }

    public String getChannelPicURL() {
        return ChannelPicURL;
    }

    public void setChannelPicURL(String ChannelPicURL) {
        this.ChannelPicURL = ChannelPicURL;
    }

    public String getChannelPicURLRelatvie() {
        return ChannelPicURLRelatvie;
    }

    public void setChannelPicURLRelatvie(String ChannelPicURLRelatvie) {
        this.ChannelPicURLRelatvie = ChannelPicURLRelatvie;
    }

    public Map<String, String> getChannelName() {
        return ChannelName;
    }

    public void setChannelName(Map<String, String> ChannelName) {
        this.ChannelName = ChannelName;
    }
}

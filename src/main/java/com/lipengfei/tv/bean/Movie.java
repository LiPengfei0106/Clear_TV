package com.lipengfei.tv.bean;

import com.lipengfei.tv.global.Variables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lee on 2017/5/24.
 */

public class Movie implements Serializable {


    /**
     * Introduce : {"zh-CN":"一名纯真的21岁女大学生安娜塔希娅．因为为校报作一篇报道，前去采访28岁英俊的企业家克里斯钦．格雷（Christian Grey），两人之间擦出了爱的火花，但很快安娜就发现格雷的一个惊人的秘密......。得知真相的安娜在爱与痛的边缘之间不断挣扎，结果不断发现自己不为人知的阴暗面","en-US":"When Anastasia Steele, a literature student, goes to interview the wealthy Christian Grey as a favor to her roommate Kate Kavanagh, she encounters a beautiful, brilliant and intimidating man. The innocent and naive Ana starts to realize she wants him. Despite his enigmatic reserve and advice, she finds herself desperate to get close to him. Not able to resist Ana's beauty and independent spirit, Christian Grey admits he wants her too, but on his own terms. Ana hesitates as she discovers the singular tastes of Christian Grey - despite the embellishments of success, his multinational businesses, his vast wealth, and his loving family, Grey is consumed by the need to control everything."}
     * PicSize : 68879
     * Name : {"zh-CN":"五十度灰","en-US":"Fifty Shades of Grey"}
     * Seq : 200
     * SearchName : WSDH
     * URL : /Video/resource/0ca15c63b982bf5750d79613a953887a_149380638811_transcoded.mp4
     * Price : 5
     * Tags : [{"TagID":1},{"TagID":2}]
     * URL_ABS : http://vodresource.cleartv.cn/movies/0ca15c63b982bf5750d79613a953887a_149380638811_transcoded.mp4
     * Actor : {"zh-CN":"杰米·道南，达科塔·约翰逊，詹妮弗·艾莉","en-US":"Dakota Johnson, Jamie Dornan, Jennifer Ehle"}
     * Director : {"zh-CN":"萨姆·泰勒-约翰逊","en-US":"Sam Taylor-Johnson"}
     * PicURL_ABS : http://mres.cleartv.cn/default/c4ca4238a0b923820dcc509a6f75849b_149438148536.jpg
     * Score : 4
     * Location : [{"LocationID":2}]
     * MovieSize : 2511007751
     * Year : 2015
     * Duration : 7482
     * ID : 451
     * PicURL : /Video/resource/c4ca4238a0b923820dcc509a6f75849b_149438148536.jpg
     */

    private HashMap<String,String> Introduce;
    private int PicSize;
    private HashMap<String,String> Name;
    private int Seq;
    private String SearchName;
    private String URL;
    private double Price;
    private String URL_ABS;
    private HashMap<String,String> Actor;
    private HashMap<String,String> Director;
    private String PicURL_ABS;
    private float Score;
    private long MovieSize;
    private String Year;
    private int Duration;
    private int ID;
    private String PicURL;
    private List<TagsBean> Tags;
    private List<LocationBean> Location;

    public HashMap<String,String> tagNameMap = new HashMap<>();


    public HashMap<String,String> getName() {
        return Name;
    }

    public void setName(HashMap<String,String> Name) {
        this.Name = Name;
    }


    public String getBackgroundUrl() {
        return Variables.host + PicURL;
    }

    public String getCardImageUrl() {
        return Variables.host + PicURL;
    }

    public String getVideoUrl() {
        return Variables.host + URL;
    }

    public String getTitle() {
        if(!Name.containsKey(Variables.language_code))
            return Name.get(Variables.default_language_code);
        return Name.get(Variables.language_code);
    }

    public HashMap<String, String> getActor() {
        return Actor;
    }

    public void setActor(HashMap<String,String> actor) {
        this.Actor = actor;
    }

    public String getDescription() {
        if(!Introduce.containsKey(Variables.language_code))
            return Introduce.get(Variables.default_language_code);
        return Introduce.get(Variables.language_code);
    }

    public String getStudio() {
        if(!Actor.containsKey(Variables.language_code))
            return Actor.get(Variables.default_language_code);
        return Actor.get(Variables.language_code);
    }

    public HashMap<String, String> getIntroduce() {
        return Introduce;
    }

    public int getPicSize() {
        return PicSize;
    }

    public int getSeq() {
        return Seq;
    }

    public String getSearchName() {
        return SearchName;
    }

    public String getURL() {
        return URL;
    }

    public double getPrice() {
        return Price;
    }

    public String getURL_ABS() {
        return URL_ABS;
    }



    public HashMap<String, String> getDirector() {
        return Director;
    }

    public String getPicURL_ABS() {
        return PicURL_ABS;
    }

    public float getScore() {
        return Score;
    }

    public long getMovieSize() {
        return MovieSize;
    }

    public String getYear() {
        return Year;
    }

    public int getDuration() {
        return Duration;
    }

    public int getID() {
        return ID;
    }

    public String getPicURL() {
        return PicURL;
    }

    public List<TagsBean> getTags() {
        return Tags;
    }

    public List<LocationBean> getLocation() {
        return Location;
    }

    public HashMap<String, String> getTagNameMap() {
        return tagNameMap;
    }



    public static class TagsBean implements Serializable{
        /**
         * TagID : 1
         */

        private int TagID;

        public int getTagID() {
            return TagID;
        }

    }

    public static class LocationBean implements Serializable{
        /**
         * LocationID : 2
         */

        private int LocationID;

        public int getLocationID() {
            return LocationID;
        }

        public void setLocationID(int LocationID) {
            this.LocationID = LocationID;
        }
    }
}

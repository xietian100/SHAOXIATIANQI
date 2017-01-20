package com.xietian.shaoxiaweather.sxtq.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pc on 2017/1/13.
 */

public class CityInfo {

        /**
         * id : WS9DZQ1QXHGR
         * name : 赣州
         * country : CN
         * path : 赣州,赣州,江西,中国
         * timezone : Asia/Shanghai
         * timezone_offset : +08:00
         */

        @SerializedName("id")
        private String idX;
        @SerializedName("name")
        private String nameX;
        @SerializedName("country")
        private String countryX;
        @SerializedName("path")
        private String pathX;
        @SerializedName("timezone")
        private String timezoneX;
        private String timezone_offset;

        public String getIdX() {
            return idX;
        }

        public void setIdX(String idX) {
            this.idX = idX;
        }

        public String getNameX() {
            return nameX;
        }

        public void setNameX(String nameX) {
            this.nameX = nameX;
        }

        public String getCountryX() {
            return countryX;
        }

        public void setCountryX(String countryX) {
            this.countryX = countryX;
        }

        public String getPathX() {
            return pathX;
        }

        public void setPathX(String pathX) {
            this.pathX = pathX;
        }

        public String getTimezoneX() {
            return timezoneX;
        }

        public void setTimezoneX(String timezoneX) {
            this.timezoneX = timezoneX;
        }

        public String getTimezone_offset() {
            return timezone_offset;
        }

        public void setTimezone_offset(String timezone_offset) {
            this.timezone_offset = timezone_offset;
        }

}

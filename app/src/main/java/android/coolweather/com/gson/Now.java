package android.coolweather.com.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hbhdw on 2018/6/21.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}

package android.coolweather.com.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hbhdw on 2018/6/21.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comport comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comport {
        @SerializedName("txt")
        public String info;
    }

    public class CarWash {
        @SerializedName("txt")
        public String info;
    }

    public class Sport {
        @SerializedName("txt")
        public String info;
    }
}

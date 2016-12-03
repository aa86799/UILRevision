package utils;

import okhttp3.OkHttpClient;

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 2016/12/3 23 01
 */
public class OkHttpManager {

    private OkHttpManager(){}

    public static OkHttpClient getOKOkHttpClient() {
        return Builder.client;
    }

    private static class Builder {
        private static OkHttpClient client = new OkHttpClient();
    }
}

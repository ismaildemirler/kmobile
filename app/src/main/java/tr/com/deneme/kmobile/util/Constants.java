package tr.com.deneme.kmobile.util;

public class Constants {

    public static final String DATABASE_NAME = "***************";

    public static final String BASE_URL = "*****************";

    public static final String GRANT_TYPE = "***********";
    public static final String USERNAME = "************";
    public static final String PASSWORD = "**********";

    public static final int CONNECTION_TIMEOUT = 10; // 10 seconds
    public static final int READ_TIMEOUT = 2; // 2 seconds
    public static final int WRITE_TIMEOUT = 2; // 2 seconds

    public static final String USER_NOT_FOUND = "Kullanıcı bulunamamıştır. Lütfen kullanıcı adınızı ve şifrenizi kontrol ediniz!";

    public static final int FIRM_REFRESH_TIME = 60 * 60 * 24 * 30; // 30 days (in seconds)

    public final static String SHARED_PREFS_ACCESS_TOKEN = "Access_Token";

    public static final String NAMED_OKHTTP_CIENT_WITHOUT_AUTH = "OkHttpClientWithoutAuth";
    public static final String NAMED_OKHTTP_CIENT_WITH_AUTH = "OkHttpClientWithAuth";
    public static final String NAMED_RETROFIT_WITHOUT_AUTH = "RetrofitWithoutAuth";
    public static final String NAMED_RETROFIT_WITH_AUTH = "RetrofitWithAuth";

    public static final String FIRMS_REQUEST_BUNDLE = "FirmRequest";

    public static final String QUERY_EXHAUSTED_MESSAGE = "Gösterilecek Sonuç Kalmamıştır.";

    public static final String LOADING_MESAGE = "LOADING...";
    public static final String EXHAUSTED_MESAGE = "EXHAUSTED...";
}

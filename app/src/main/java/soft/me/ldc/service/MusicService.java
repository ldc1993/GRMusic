package soft.me.ldc.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Response;
import soft.me.ldc.BuildConfig;
import soft.me.ldc.config.App;
import soft.me.ldc.config.AppConfig;
import soft.me.ldc.http.okhttp3Request;
import soft.me.ldc.http.param.HttpParam;
import soft.me.ldc.model.Music;
import soft.me.ldc.utils.DeviceUtil;
import soft.me.ldc.utils.ToFormat;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class MusicService {

    static final String testJson = "{\n" +
            "    \"song_list\": [\n" +
            "        {\n" +
            "            \"artist_id\": \"1641\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-27\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/aac3ec9d550094dc94d8a792e74e2ad5/567270931/567270931.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"67941\",\n" +
            "            \"all_artist_ting_uid\": \"1579\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"1\",\n" +
            "            \"all_artist_id\": \"1641\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 260,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"华宇世博音乐文化（北京）有限公司-亚神\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"567270530\",\n" +
            "            \"title\": \"言不由衷\",\n" +
            "            \"ting_uid\": \"1579\",\n" +
            "            \"author\": \"徐佳莹\",\n" +
            "            \"album_id\": \"568564580\",\n" +
            "            \"album_title\": \"心里学\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 1,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"徐佳莹\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"1665\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-29\",\n" +
            "            \"album_no\": \"3\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/9d923fe8df5c82aa38fb1992214e2c05/567626249/567626249.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"54377\",\n" +
            "            \"all_artist_ting_uid\": \"2611\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"2\",\n" +
            "            \"all_artist_id\": \"1665\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 285,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"567479594\",\n" +
            "            \"title\": \"如我\",\n" +
            "            \"ting_uid\": \"2611\",\n" +
            "            \"author\": \"刘惜君\",\n" +
            "            \"album_id\": \"568664804\",\n" +
            "            \"album_title\": \"如我\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 1,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"刘惜君\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"567299514\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/4a2cffbaf32b0a7cb77986bc483e8642/567298831/567298831.JPG@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/4a2cffbaf32b0a7cb77986bc483e8642/567298831/567298831.JPG@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-05\",\n" +
            "            \"album_no\": \"12\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/b354311e0140f2cda8e1787d4668e04e/567299539/567299539.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"221767\",\n" +
            "            \"all_artist_ting_uid\": \"340290414\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"0\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"3\",\n" +
            "            \"all_artist_id\": \"567299514\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64\",\n" +
            "            \"file_duration\": 266,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"北京唱吧科技股份有限公司\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"567299854\",\n" +
            "            \"title\": \"我们不一样\",\n" +
            "            \"ting_uid\": \"340290414\",\n" +
            "            \"author\": \"Mc黑总 bb机登录用户\",\n" +
            "            \"album_id\": \"567299830\",\n" +
            "            \"album_title\": \"唱吧精选\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 0,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"Mc黑总 bb机登录用户\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/4a2cffbaf32b0a7cb77986bc483e8642/567298831/567298831.JPG@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/4a2cffbaf32b0a7cb77986bc483e8642/567298831/567298831.JPG\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/4a2cffbaf32b0a7cb77986bc483e8642/567298831/567298831.JPG\",\n" +
            "            \"pic_huge\": \"\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/4a2cffbaf32b0a7cb77986bc483e8642/567298831/567298831.JPG\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"88\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/1c2424e15b4f432d5ad65f69f0e2d11d/568328873/568328873.png@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/1c2424e15b4f432d5ad65f69f0e2d11d/568328873/568328873.png@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-22\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/9b90b83c17e694eaedb354fd8d19fcff/568325003/568325003.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"119554\",\n" +
            "            \"all_artist_ting_uid\": \"2517\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"4\",\n" +
            "            \"all_artist_id\": \"88\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 235,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568320992\",\n" +
            "            \"title\": \"狐狸（电影《二代妖精之今生有幸》推广曲）\",\n" +
            "            \"ting_uid\": \"2517\",\n" +
            "            \"author\": \"薛之谦\",\n" +
            "            \"album_id\": \"568320989\",\n" +
            "            \"album_title\": \"狐狸（电影《二代妖精之今生有幸》推广曲）\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 1,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"薛之谦\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/1c2424e15b4f432d5ad65f69f0e2d11d/568328873/568328873.png@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/1c2424e15b4f432d5ad65f69f0e2d11d/568328873/568328873.png@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/1c2424e15b4f432d5ad65f69f0e2d11d/568328873/568328873.png@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/1c2424e15b4f432d5ad65f69f0e2d11d/568328873/568328873.png@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/1c2424e15b4f432d5ad65f69f0e2d11d/568328873/568328873.png@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/1c2424e15b4f432d5ad65f69f0e2d11d/568328873/568328873.png@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"345\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2018-01-05\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/a115a4b2ed347d8f2047521494d8c9ee/568574517/568574517.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"39013\",\n" +
            "            \"all_artist_ting_uid\": \"1209\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"1\",\n" +
            "            \"rank\": \"5\",\n" +
            "            \"all_artist_id\": \"345\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 244,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"华宇世博音乐文化（北京）有限公司-赵传曲库\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568572710\",\n" +
            "            \"title\": \"逆风飞行\",\n" +
            "            \"ting_uid\": \"1209\",\n" +
            "            \"author\": \"赵传\",\n" +
            "            \"album_id\": \"568855270\",\n" +
            "            \"album_title\": \"你过得还好吗\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 1,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"赵传\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"1641\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-27\",\n" +
            "            \"album_no\": \"2\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/d80873123a644c0a5610dc02c7dffc1b/568270394/568270394.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"39759\",\n" +
            "            \"all_artist_ting_uid\": \"1579\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"-1\",\n" +
            "            \"rank\": \"6\",\n" +
            "            \"all_artist_id\": \"1641\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 323,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568221179\",\n" +
            "            \"title\": \"灰色\",\n" +
            "            \"ting_uid\": \"1579\",\n" +
            "            \"author\": \"徐佳莹\",\n" +
            "            \"album_id\": \"568564580\",\n" +
            "            \"album_title\": \"心里学\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 1,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"徐佳莹\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"268\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-28\",\n" +
            "            \"album_no\": \"2\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/687afd2d70752f1c075ddde18ea9f392/568212679/568212679.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"36432\",\n" +
            "            \"all_artist_ting_uid\": \"1163\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"7\",\n" +
            "            \"all_artist_id\": \"268\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 247,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568003612\",\n" +
            "            \"title\": \"如愿以偿\",\n" +
            "            \"ting_uid\": \"1163\",\n" +
            "            \"author\": \"品冠\",\n" +
            "            \"album_id\": \"568635221\",\n" +
            "            \"album_title\": \"言外之意\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"品冠\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"362\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/c7f49519b856ab158bee607dd45c60fe/568249523/568249523.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/c7f49519b856ab158bee607dd45c60fe/568249523/568249523.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-19\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/694a054c9417628f6012b756126c5449/568249957/568249957.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"86087\",\n" +
            "            \"all_artist_ting_uid\": \"1219\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"8\",\n" +
            "            \"all_artist_id\": \"362\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 307,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"电影《芳华》片尾曲\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"肆想文化传媒（北京）有限责任公司\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568249533\",\n" +
            "            \"title\": \"绒花\",\n" +
            "            \"ting_uid\": \"1219\",\n" +
            "            \"author\": \"韩红\",\n" +
            "            \"album_id\": \"568249531\",\n" +
            "            \"album_title\": \"绒花\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"韩红\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/c7f49519b856ab158bee607dd45c60fe/568249523/568249523.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/c7f49519b856ab158bee607dd45c60fe/568249523/568249523.jpg\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/c7f49519b856ab158bee607dd45c60fe/568249523/568249523.jpg\",\n" +
            "            \"pic_huge\": \"\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/c7f49519b856ab158bee607dd45c60fe/568249523/568249523.jpg\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"776\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/55438873881e95fbeb8036e135b26396/568967878/568967878.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/55438873881e95fbeb8036e135b26396/568967878/568967878.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-26\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/8021b2c60ba0d4af082a2cb319d13422/568445391/568445391.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"30435\",\n" +
            "            \"all_artist_ting_uid\": \"1383,10559334\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"9\",\n" +
            "            \"all_artist_id\": \"776,14947058\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 278,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"电影《解忧杂货店》宣传曲\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"奔跑怪物（北京）文化娱乐有限公司\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568445418\",\n" +
            "            \"title\": \"雾中列车\",\n" +
            "            \"ting_uid\": \"1383\",\n" +
            "            \"author\": \"李健,王俊凯\",\n" +
            "            \"album_id\": \"568445416\",\n" +
            "            \"album_title\": \"雾中列车\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 1,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"李健,王俊凯\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/55438873881e95fbeb8036e135b26396/568967878/568967878.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/55438873881e95fbeb8036e135b26396/568967878/568967878.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/55438873881e95fbeb8036e135b26396/568967878/568967878.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/55438873881e95fbeb8036e135b26396/568967878/568967878.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/55438873881e95fbeb8036e135b26396/568967878/568967878.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/55438873881e95fbeb8036e135b26396/568967878/568967878.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"163\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/94fe9736048a0e6d4001bf81adac3b9e/568362714/568362714.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/94fe9736048a0e6d4001bf81adac3b9e/568362714/568362714.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"港台\",\n" +
            "            \"area\": \"1\",\n" +
            "            \"publishtime\": \"2017-12-25\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/1fb698746448007fe46008fbc8aa4f13/568363086/568363086.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"29111\",\n" +
            "            \"all_artist_ting_uid\": \"1117,39138578\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"10\",\n" +
            "            \"all_artist_id\": \"163,32278697\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"flac,320,256,128,64\",\n" +
            "            \"file_duration\": 213,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"华宇世博音乐文化（北京）有限公司-力行网际\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568363089\",\n" +
            "            \"title\": \"我在未来等你\",\n" +
            "            \"ting_uid\": \"1117\",\n" +
            "            \"author\": \"齐秦,米咪\",\n" +
            "            \"album_id\": \"568363087\",\n" +
            "            \"album_title\": \"我在未来等你\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"齐秦,米咪\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/94fe9736048a0e6d4001bf81adac3b9e/568362714/568362714.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/94fe9736048a0e6d4001bf81adac3b9e/568362714/568362714.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/94fe9736048a0e6d4001bf81adac3b9e/568362714/568362714.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/94fe9736048a0e6d4001bf81adac3b9e/568362714/568362714.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/94fe9736048a0e6d4001bf81adac3b9e/568362714/568362714.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/94fe9736048a0e6d4001bf81adac3b9e/568362714/568362714.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"566601109\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-26\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/6787f600b16806bb18e98b6494c01f6f/568460334/568460334.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"28013\",\n" +
            "            \"all_artist_ting_uid\": \"340287477\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"11\",\n" +
            "            \"all_artist_id\": \"566601109\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 259,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"影视原声\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"北京麦特文化发展有限公司\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568460363\",\n" +
            "            \"title\": \"无问\",\n" +
            "            \"ting_uid\": \"340287477\",\n" +
            "            \"author\": \"毛不易\",\n" +
            "            \"album_id\": \"568460360\",\n" +
            "            \"album_title\": \"无问\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"毛不易\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"1665\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-29\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/45d773d3a6628b859c17253a983b156a/568302543/568302543.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"27976\",\n" +
            "            \"all_artist_ting_uid\": \"2611\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"12\",\n" +
            "            \"all_artist_id\": \"1665\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 282,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568281588\",\n" +
            "            \"title\": \"浪里游\",\n" +
            "            \"ting_uid\": \"2611\",\n" +
            "            \"author\": \"刘惜君\",\n" +
            "            \"album_id\": \"568664804\",\n" +
            "            \"album_title\": \"如我\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"刘惜君\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"20633953\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/e213567b81194c6d08e31eac760d9be8/568820244/568820244.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/e213567b81194c6d08e31eac760d9be8/568820244/568820244.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2018-01-03\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/34c933b7ddaec888aa857554372d1ccb/568809070/568809070.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"27173\",\n" +
            "            \"all_artist_ting_uid\": \"31514359\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"13\",\n" +
            "            \"all_artist_id\": \"20633953\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 292,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"刘同小说《我在未来等你》同名主题曲\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"上海好靓文化传媒工作室（有限合伙）\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568809145\",\n" +
            "            \"title\": \"我在未来等你\",\n" +
            "            \"ting_uid\": \"31514359\",\n" +
            "            \"author\": \"好妹妹乐队\",\n" +
            "            \"album_id\": \"568809143\",\n" +
            "            \"album_title\": \"我在未来等你\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"好妹妹乐队\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/e213567b81194c6d08e31eac760d9be8/568820244/568820244.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/e213567b81194c6d08e31eac760d9be8/568820244/568820244.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/e213567b81194c6d08e31eac760d9be8/568820244/568820244.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/e213567b81194c6d08e31eac760d9be8/568820244/568820244.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/e213567b81194c6d08e31eac760d9be8/568820244/568820244.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/e213567b81194c6d08e31eac760d9be8/568820244/568820244.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"15\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2018-01-11\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/ef80d282b94f37e92bee6e5b9b417124/569080826/569080826.lrc\",\n" +
            "            \"copy_type\": \"3\",\n" +
            "            \"hot\": \"11861\",\n" +
            "            \"all_artist_ting_uid\": \"45561\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"17\",\n" +
            "            \"rank\": \"14\",\n" +
            "            \"all_artist_id\": \"15\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 290,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"影视原声\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"上海腾讯企鹅影视文化传播有限公司\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"569080829\",\n" +
            "            \"title\": \"无问西东\",\n" +
            "            \"ting_uid\": \"45561\",\n" +
            "            \"author\": \"王菲\",\n" +
            "            \"album_id\": \"569080827\",\n" +
            "            \"album_title\": \"无问西东\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"王菲\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/4865939a77b87edc79789df87b6f22d8/569080825/569080825.png@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"1665\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-29\",\n" +
            "            \"album_no\": \"6\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/0379b43d87591e8927046c03ea0710b2/566512048/566512048.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"22924\",\n" +
            "            \"all_artist_ting_uid\": \"2611\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"-1\",\n" +
            "            \"rank\": \"15\",\n" +
            "            \"all_artist_id\": \"1665\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 262,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"566307540\",\n" +
            "            \"title\": \"嗜睡症\",\n" +
            "            \"ting_uid\": \"2611\",\n" +
            "            \"author\": \"刘惜君\",\n" +
            "            \"album_id\": \"568664804\",\n" +
            "            \"album_title\": \"如我\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"刘惜君\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"345\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2018-01-05\",\n" +
            "            \"album_no\": \"9\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/7ccaa8c02044f48ebc0bd78c989c7341/568861972/568861972.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"21397\",\n" +
            "            \"all_artist_ting_uid\": \"1209\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"0\",\n" +
            "            \"rank\": \"16\",\n" +
            "            \"all_artist_id\": \"345\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 271,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"华宇世博音乐文化（北京）有限公司-赵传曲库\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568855291\",\n" +
            "            \"title\": \"感谢你\",\n" +
            "            \"ting_uid\": \"1209\",\n" +
            "            \"author\": \"赵传\",\n" +
            "            \"album_id\": \"568855270\",\n" +
            "            \"album_title\": \"你过得还好吗\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"赵传\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"291\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/18fa053dd52bdf8e9aa0d3aa59fdc401/568681943/568681943.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/18fa053dd52bdf8e9aa0d3aa59fdc401/568681943/568681943.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2018-01-01\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/425f5eb583d9ee349b5981acca6a4ece/568732596/568732596.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"21593\",\n" +
            "            \"all_artist_ting_uid\": \"1178\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"-2\",\n" +
            "            \"rank\": \"17\",\n" +
            "            \"all_artist_id\": \"291\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"flac,320,256,128,64\",\n" +
            "            \"file_duration\": 476,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568681945\",\n" +
            "            \"title\": \"送别2017\",\n" +
            "            \"ting_uid\": \"1178\",\n" +
            "            \"author\": \"窦唯\",\n" +
            "            \"album_id\": \"568681932\",\n" +
            "            \"album_title\": \"送别2017\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"窦唯\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/18fa053dd52bdf8e9aa0d3aa59fdc401/568681943/568681943.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/18fa053dd52bdf8e9aa0d3aa59fdc401/568681943/568681943.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/18fa053dd52bdf8e9aa0d3aa59fdc401/568681943/568681943.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/18fa053dd52bdf8e9aa0d3aa59fdc401/568681943/568681943.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/18fa053dd52bdf8e9aa0d3aa59fdc401/568681943/568681943.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/18fa053dd52bdf8e9aa0d3aa59fdc401/568681943/568681943.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"268\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2017-12-28\",\n" +
            "            \"album_no\": \"4\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/88fbdf7587a37a7114d53abf5b75435a/568213225/568213225.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"20573\",\n" +
            "            \"all_artist_ting_uid\": \"1163\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"-1\",\n" +
            "            \"rank\": \"18\",\n" +
            "            \"all_artist_id\": \"268\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 258,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568003613\",\n" +
            "            \"title\": \"想\",\n" +
            "            \"ting_uid\": \"1163\",\n" +
            "            \"author\": \"品冠\",\n" +
            "            \"album_id\": \"568635221\",\n" +
            "            \"album_title\": \"言外之意\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"品冠\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/5d358cef0da9a2196288a67252506b9f/568681930/568681930.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"345\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2018-01-05\",\n" +
            "            \"album_no\": \"3\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/0b34fae4717d21255314a9d6b3471c7c/557660068/557660068.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"17717\",\n" +
            "            \"all_artist_ting_uid\": \"1209\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"1\",\n" +
            "            \"rank\": \"19\",\n" +
            "            \"all_artist_id\": \"345\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 252,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"华宇世博音乐文化（北京）有限公司-赵传曲库\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"557660201\",\n" +
            "            \"title\": \"解脱的自由\",\n" +
            "            \"ting_uid\": \"1209\",\n" +
            "            \"author\": \"赵传\",\n" +
            "            \"album_id\": \"568855270\",\n" +
            "            \"album_title\": \"你过得还好吗\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 1,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"赵传\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_1000,h_1000\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/91fbed731515806dcc4f5ee7de73f69a/568958047/568958047.jpg@s_1,w_1000,h_1000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"artist_id\": \"334823829\",\n" +
            "            \"language\": \"国语\",\n" +
            "            \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/7656047f49930dabcd90ba2c3feff969/569021105/569021105.jpg@s_1,w_150,h_150\",\n" +
            "            \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/7656047f49930dabcd90ba2c3feff969/569021105/569021105.jpg@s_1,w_90,h_90\",\n" +
            "            \"country\": \"内地\",\n" +
            "            \"area\": \"0\",\n" +
            "            \"publishtime\": \"2018-01-09\",\n" +
            "            \"album_no\": \"1\",\n" +
            "            \"lrclink\": \"http://qukufile2.qianqian.com/data2/lrc/9bf6d4b99ad5961796ec1dfbe53107fb/568633832/568633832.lrc\",\n" +
            "            \"copy_type\": \"1\",\n" +
            "            \"hot\": \"17426\",\n" +
            "            \"all_artist_ting_uid\": \"240086616\",\n" +
            "            \"resource_type\": \"0\",\n" +
            "            \"is_new\": \"1\",\n" +
            "            \"rank_change\": \"1\",\n" +
            "            \"rank\": \"20\",\n" +
            "            \"all_artist_id\": \"334823829\",\n" +
            "            \"style\": \"\",\n" +
            "            \"del_status\": \"0\",\n" +
            "            \"relate_status\": \"0\",\n" +
            "            \"toneid\": \"0\",\n" +
            "            \"all_rate\": \"64,128,256,320,flac\",\n" +
            "            \"file_duration\": 244,\n" +
            "            \"has_mv_mobile\": 0,\n" +
            "            \"versions\": \"影视原声\",\n" +
            "            \"bitrate_fee\": \"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\n" +
            "            \"biaoshi\": \"first,lossless\",\n" +
            "            \"info\": \"电视剧《艳骨》片头曲\",\n" +
            "            \"has_filmtv\": \"0\",\n" +
            "            \"si_proxycompany\": \"TAIHE MUSIC GROUP\",\n" +
            "            \"res_encryption_flag\": \"0\",\n" +
            "            \"song_id\": \"568632066\",\n" +
            "            \"title\": \"彻骨伤（电视剧《艳骨》片头曲）\",\n" +
            "            \"ting_uid\": \"240086616\",\n" +
            "            \"author\": \"岳靖川\",\n" +
            "            \"album_id\": \"568993489\",\n" +
            "            \"album_title\": \"《艳骨》电视剧原声带\",\n" +
            "            \"is_first_publish\": 0,\n" +
            "            \"havehigh\": 2,\n" +
            "            \"charge\": 0,\n" +
            "            \"has_mv\": 0,\n" +
            "            \"learn\": 0,\n" +
            "            \"song_source\": \"web\",\n" +
            "            \"piao_id\": \"0\",\n" +
            "            \"korean_bb_song\": \"0\",\n" +
            "            \"resource_type_ext\": \"0\",\n" +
            "            \"mv_provider\": \"0000000000\",\n" +
            "            \"artist_name\": \"岳靖川\",\n" +
            "            \"pic_radio\": \"http://qukufile2.qianqian.com/data2/pic/7656047f49930dabcd90ba2c3feff969/569021105/569021105.jpg@s_1,w_300,h_300\",\n" +
            "            \"pic_s500\": \"http://qukufile2.qianqian.com/data2/pic/7656047f49930dabcd90ba2c3feff969/569021105/569021105.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_premium\": \"http://qukufile2.qianqian.com/data2/pic/7656047f49930dabcd90ba2c3feff969/569021105/569021105.jpg@s_1,w_500,h_500\",\n" +
            "            \"pic_huge\": \"http://qukufile2.qianqian.com/data2/pic/7656047f49930dabcd90ba2c3feff969/569021105/569021105.jpg\",\n" +
            "            \"album_500_500\": \"http://qukufile2.qianqian.com/data2/pic/7656047f49930dabcd90ba2c3feff969/569021105/569021105.jpg@s_1,w_500,h_500\",\n" +
            "            \"album_800_800\": \"\",\n" +
            "            \"album_1000_1000\": \"http://qukufile2.qianqian.com/data2/pic/7656047f49930dabcd90ba2c3feff969/569021105/569021105.jpg\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"billboard\": {\n" +
            "        \"billboard_type\": \"1\",\n" +
            "        \"billboard_no\": \"2438\",\n" +
            "        \"update_date\": \"2018-01-13\",\n" +
            "        \"billboard_songnum\": \"121\",\n" +
            "        \"havemore\": 1,\n" +
            "        \"name\": \"新歌榜\",\n" +
            "        \"comment\": \"该榜单是根据百度音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次\",\n" +
            "        \"pic_s192\": \"http://hiphotos.qianqian.com/ting/pic/item/9922720e0cf3d7caf39ebc10f11fbe096b63a968.jpg\",\n" +
            "        \"pic_s640\": \"http://hiphotos.qianqian.com/ting/pic/item/f7246b600c33874495c4d089530fd9f9d62aa0c6.jpg\",\n" +
            "        \"pic_s444\": \"http://hiphotos.qianqian.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg\",\n" +
            "        \"pic_s260\": \"http://hiphotos.qianqian.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg\",\n" +
            "        \"pic_s210\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_c49310115801d43d42a98fdc357f6057.jpg\",\n" +
            "        \"web_url\": \"http://music.baidu.com/top/new\"\n" +
            "    },\n" +
            "    \"error_code\": 22000\n" +
            "}";


    /**
     * @param type   音乐类型
     * @param size   返回数量
     * @param offset 偏移 分页
     * @return
     */
    public static Music MusicList(String type, String size, String offset) {
        Response response = null;
        Music music = null;
        Gson gson = null;
        try {
            HttpParam param = new HttpParam();
            param.url = AppConfig.ServiceUrl;
            param.method = "0";//get
            param.requestType = HttpParam.FormBody;
            param.parems.put("method", "baidu.ting.billboard.billList");
            param.parems.put("type", "" + type);
            param.parems.put("size", "" + size);
            param.parems.put("offset", "" + offset);
            response = okhttp3Request.getInstance().Method(param);
//            if (response != null && response.isSuccessful()) {
//                if (gson == null)
//                    gson = new Gson();
//                music = gson.fromJson(response.body().toString(), Music.class);
//            } else {
//                music = null;
//            }
            if (gson == null)
                gson = new Gson();
            music = gson.fromJson(testJson, Music.class);
        } catch (Exception e) {
            music = null;
            e.printStackTrace();
        } finally {
            if (response != null)
                response.close();
        }
        return music;

    }

    /**
     * @param type   音乐类型
     * @param size   返回数量
     * @param offset 偏移 分页
     * @return
     */
    public static Music TestMusicList(String type, String size, String offset) {
        String result = "";
        Music music = null;
        Gson gson = null;
        HttpURLConnection connection = null;
        int responseCode = 200;
        URL url = null;
        InputStream is = null;
        try {
            if (url == null)
                url = new URL(AppConfig.ServiceUrl);
            if (connection == null)
                connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.addRequestProperty("User-Agent", "" + DeviceUtil.makeUA());
            connection.setRequestProperty("method", "baidu.ting.billboard.billList");
            connection.setRequestProperty("type", "" + type);
            connection.setRequestProperty("size", "" + size);
            connection.setRequestProperty("offset", "" + offset);
            connection.setConnectTimeout(40 * 1000);
            connection.setReadTimeout(50 * 1000);
            connection.connect();//连接
            responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
                result = ToFormat.Stream2String(is);
            } else {
                result = testJson;
            }

            if (gson == null)
                gson = new Gson();
            music = gson.fromJson(result, Music.class);
            if (is != null)
                is.close();

        } catch (Exception e) {
            music = null;
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();

        }
        return music;

    }

}

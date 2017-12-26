package com.example.jiecaovideoplayer;



import com.example.jiecaovideoplayer.Bean.VideoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 鱼握拳 on 2017/12/19.
 */

public interface ApiService {
        @GET("selected?v2/feed/")
        Observable<VideoBean> getMessage();
}

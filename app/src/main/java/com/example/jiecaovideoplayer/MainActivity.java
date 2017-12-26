package com.example.jiecaovideoplayer;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.jiecaovideoplayer.Bean.VideoBean;
import com.example.jiecaovideoplayer.Custom.GlideImageLoader;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private  Banner banner;
    private TypeAdapter adapter;
    private EasyRecyclerView recyclerView;
    private List<CategoryTab> list=new ArrayList<>();
    private static final int STATE_REFRESH = 0;
    private static final int STATE_MORE = 1;
    private int limit = 10;
    private int curPage = 0;
    String lastTime = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner= (Banner) findViewById(R.id.banner);
        Bmob.initialize(this,"3294a0f092543dc76c82b6b04134ac6f");
        getData();
        getIcon(0, STATE_REFRESH);
        recyclerView= (EasyRecyclerView) findViewById(R.id.recycler);
        recyclerView.setRefreshListener(this);
        adapter = new TypeAdapter(this);
//        recyclerView.setAdapter(adapter);
    }

    private void getIcon(int page,final int actionType) {

        BmobQuery<CategoryTab> query=new BmobQuery<>();
        query.order("-createdAt");
        if (actionType == STATE_MORE) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(lastTime);
                Log.i("0414", date.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            query.setSkip(limit*curPage-10);
        } else {
            page = 0;
            query.setSkip(page);
        }
        query.setLimit(limit);
        query.findObjects(MainActivity.this, new FindListener<CategoryTab>() {
            @Override
            public void onSuccess(List<CategoryTab> list) {
                adapter.addAll(list);
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(MainActivity.this, "888", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getData() {

        RetrofitManger.getRetrofit()
                .create(ApiService.class)
                .getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VideoBean>() {
                    @Override
                    public void accept(final VideoBean videoBean) throws Exception {
                        List<VideoBean.ItemListBean.DataBean.CoverBean> images =new ArrayList<>();
                        if (videoBean.getItemList()!=null) {
                            List<String> listImage = new ArrayList<>();
                            List<String> listTitle = new ArrayList<>();
                            for (int i = 0; i <5 ; i++) {
                                if (i==5){
                                }else {
                                    listImage.add(videoBean.getItemList().get(i).getData().getCover().getFeed());
                                    listTitle.add(videoBean.getItemList().get(i).getData().getTitle());
                                }
                            }
                                banner.setImages((List<?>) listImage)
                                        .setImageLoader(new GlideImageLoader())
                                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                                        .setBannerTitles(listTitle)
                                        .isAutoPlay(true);
                                banner.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
                                        Intent intent=new Intent(MainActivity.this,WebActivity.class);
                                        intent.putExtra("url",videoBean.getItemList().get(position).getData().getWebUrl().getRaw());
                                        startActivity(intent);
                                    }
                                });
                                banner.start();

//                            }
                        }
                    }
                });



}


    @Override
    public void onRefresh() {

    }
}

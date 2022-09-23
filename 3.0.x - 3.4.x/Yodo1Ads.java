//
// Created by Yodo1 on 21/07/2022.
//

package com.cocos.game;

import android.content.Context;
import android.os.Debug;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.cocos.lib.CocosHelper;
import com.yodo1.mas.Yodo1Mas;
import com.yodo1.mas.banner.Yodo1MasBannerAdListener;
import com.yodo1.mas.banner.Yodo1MasBannerAdSize;
import com.yodo1.mas.banner.Yodo1MasBannerAdView;
import com.yodo1.mas.error.Yodo1MasError;
import com.yodo1.mas.helper.model.Yodo1MasAdBuildConfig;
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAd;
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAdListener;
import com.yodo1.mas.reward.Yodo1MasRewardAd;
import com.yodo1.mas.reward.Yodo1MasRewardAdListener;
import com.cocos.lib.CocosJavascriptJavaBridge;
public class Yodo1Ads {

    private static Yodo1MasBannerAdView bannerAdView;
    private static AppActivity activity;

    public void initSDK(AppActivity activity) {
        this.activity = activity;
    }
    public static void initializeSdk(AppActivity activity, String appKey,boolean isEnabled) //its working
    {

        Yodo1Ads yodo1Ads = new Yodo1Ads();
        yodo1Ads.initSDK(activity);

        activity.runOnUiThread( new Runnable() {
            public void run() {
                if(isEnabled) {
                    Yodo1MasAdBuildConfig config = new Yodo1MasAdBuildConfig.Builder().enableUserPrivacyDialog(true).build();
                    Yodo1Mas.getInstance().setAdBuildConfig(config);
                }
                Yodo1Mas.getInstance().initMas(activity, appKey, new Yodo1Mas.InitListener() {
                    @Override
                    public void onMasInitSuccessful() {
                       // nativeOnMasInitSuccessful();
                    }

                    @Override
                    public void onMasInitFailed(@NonNull Yodo1MasError error) {
                        //nativeOnMasInitFailed();
                    }
                });
            }
         });
    }

    public static void initializeInterstitialAds()
    {
        activity.runOnUiThread( new Runnable() {
            public void run() {
                Yodo1MasInterstitialAd.getInstance().loadAd(activity);

                Yodo1MasInterstitialAd.getInstance().setAdListener(new Yodo1MasInterstitialAdListener() {

                    @Override
                    public void onInterstitialAdLoaded(Yodo1MasInterstitialAd ad) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("find(\"Yodo1\").getComponent(\"Yodo1Ads\").onInterstitialAdLoaded()");
                            }
                        });
                    }

                    @Override
                    public void onInterstitialAdFailedToLoad(Yodo1MasInterstitialAd ad, @NonNull Yodo1MasError error) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("find(\"Yodo1\").getComponent(\"Yodo1Ads\").onInterstitialAdFailedToLoad()");
                            }
                        });
                    }

                    @Override
                    public void onInterstitialAdOpened(Yodo1MasInterstitialAd ad) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("find(\"Yodo1\").getComponent(\"Yodo1Ads\").onInterstitialAdOpened()");
                            }
                        });
                    }

                    @Override
                    public void onInterstitialAdFailedToOpen(Yodo1MasInterstitialAd ad, @NonNull Yodo1MasError error) {
                        ad.loadAd(activity);
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("find(\"Yodo1\").getComponent(\"Yodo1Ads\").onInterstitialAdFailedToOpen()");
                            }
                        });
                    }

                    @Override
                    public void onInterstitialAdClosed(Yodo1MasInterstitialAd ad) {
                        ad.loadAd(activity);
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("find(\"Yodo1\").getComponent(\"Yodo1Ads\").onInterstitialAdClosed()");
                            }
                        });
                    }
                });
            }
        });

    }

    public static void showInterstitialAds()
    {
        activity.runOnUiThread( new Runnable() {
            public void run() {
                Yodo1MasInterstitialAd.getInstance().showAd(activity);
            }
        });
    }

    public static void initializeRewardAds() {

        activity.runOnUiThread( new Runnable() {
            public void run() {
                Yodo1MasRewardAd.getInstance().loadAd(activity);

                Yodo1MasRewardAd.getInstance().setAdListener(new Yodo1MasRewardAdListener() {

                    @Override
                    public void onRewardAdLoaded(Yodo1MasRewardAd ad) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onRewardAdLoaded()");
                            }
                        });
                    }

                    @Override
                    public void onRewardAdFailedToLoad(Yodo1MasRewardAd ad, @NonNull Yodo1MasError error) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onRewardAdFailedToLoad()");
                            }
                        });
                    }

                    @Override
                    public void onRewardAdOpened(Yodo1MasRewardAd ad) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onRewardAdOpened()");
                            }
                        });

                    }

                    @Override
                    public void onRewardAdFailedToOpen(Yodo1MasRewardAd ad, @NonNull Yodo1MasError error) {
                        ad.loadAd(activity);
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onRewardAdFailedToOpen()");
                            }
                        });
                    }

                    @Override
                    public void onRewardAdClosed(Yodo1MasRewardAd ad) {
                        ad.loadAd(activity);
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onRewardAdClosed()");
                            }
                        });
                    }

                    @Override
                    public void onRewardAdEarned(Yodo1MasRewardAd ad) {

                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onRewardAdEarned()");
                            }
                        });

                    }
                });
            }
        });


    }

    public static void showRewardAds()
    {
        activity.runOnUiThread( new Runnable() {
            public void run() {
                Yodo1MasRewardAd.getInstance().showAd(activity);
            }
        });
    }



    public static void loadBannerAds(String size, String horizontal_alignment, String vertical_alignment)
    {
        activity.runOnUiThread( new Runnable() {
            public void run() {

                bannerAdView = new Yodo1MasBannerAdView(activity);

                if(size.equalsIgnoreCase( "Banner"))
                    bannerAdView.setAdSize(Yodo1MasBannerAdSize.Banner);
                else if(size.equalsIgnoreCase( "AdaptiveBanner"))
                    bannerAdView.setAdSize(Yodo1MasBannerAdSize.AdaptiveBanner);
                else if(size.equalsIgnoreCase( "LargeBanner"))
                    bannerAdView.setAdSize(Yodo1MasBannerAdSize.LargeBanner);
                else if(size.equalsIgnoreCase( "SmartBanner"))
                    bannerAdView.setAdSize(Yodo1MasBannerAdSize.SmartBanner);
                else if(size.equalsIgnoreCase( "IABMediumRectangle"))
                    bannerAdView.setAdSize(Yodo1MasBannerAdSize.IABMediumRectangle);

                RelativeLayout adRelativeLayout = new RelativeLayout(activity);

                if(horizontal_alignment.equalsIgnoreCase("CENTER"))
                    adRelativeLayout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
                else if(horizontal_alignment.equalsIgnoreCase("left"))
                    adRelativeLayout.setHorizontalGravity(Gravity.LEFT);
                else if(horizontal_alignment.equalsIgnoreCase("right"))
                    adRelativeLayout.setHorizontalGravity(Gravity.RIGHT);

                if(vertical_alignment.equalsIgnoreCase("TOP"))
                    adRelativeLayout.setVerticalGravity(Gravity.TOP);
                else if(vertical_alignment.equalsIgnoreCase("BOTTOM"))
                    adRelativeLayout.setVerticalGravity(Gravity.BOTTOM);

                adRelativeLayout.addView(bannerAdView);

                bannerAdView.setAdListener(new Yodo1MasBannerAdListener() {
                    @Override public void onBannerAdLoaded(Yodo1MasBannerAdView bannerAdView) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onBannerAdLoaded()");
                            }
                        });
                    }
                    @Override
                    public void onBannerAdFailedToLoad(Yodo1MasBannerAdView bannerAdView, @NonNull Yodo1MasError error) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onBannerAdFailedToLoad()");
                            }
                        });
                    }
                    @Override public void onBannerAdOpened(Yodo1MasBannerAdView bannerAdView) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onBannerAdOpened()");
                            }
                        });
                    }
                    @Override
                    public void onBannerAdFailedToOpen(Yodo1MasBannerAdView bannerAdView, @NonNull Yodo1MasError error) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onBannerAdFailedToOpen()");
                            }
                        });
                    }
                    @Override
                    public void onBannerAdClosed(Yodo1MasBannerAdView bannerAdView) {
                        CocosHelper.runOnGameThread(new Runnable() {
                            public void run() {
                                CocosJavascriptJavaBridge.evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onBannerAdClosed()");
                            }
                        });
                    }
                });
                FrameLayout rootView = activity.findViewById(android.R.id.content);

                rootView.addView(adRelativeLayout);
                //activity.setContentView(adRelativeLayout);
                bannerAdView.loadAd();
            }
        });
    }

    public static void hideBannerAds() {
        activity.runOnUiThread( new Runnable() {
            public void run() {
                if (bannerAdView.getVisibility() == View.VISIBLE) {
                    bannerAdView.setVisibility(View.GONE);
                }
            }
        });
    }

    public static void showBannerAds() {

        activity.runOnUiThread( new Runnable() {
            public void run() {
                if (bannerAdView.getVisibility() == View.GONE) {
                    bannerAdView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public static void setCOPPA(boolean isEnabled)
    {
        activity.runOnUiThread( new Runnable() {
            public void run() {
                Yodo1Mas.getInstance().setCOPPA(isEnabled);
            }
        });
    }

    public static void setGDPR(boolean isEnabled)
    {
        activity.runOnUiThread( new Runnable() {
            public void run() {
                Yodo1Mas.getInstance().setGDPR(isEnabled);
            }
        });
    }

    public static void setCCPA(boolean isEnabled)
    {
        activity.runOnUiThread( new Runnable() {
            public void run() {
                Yodo1Mas.getInstance().setCCPA(isEnabled);
            }
        });
    }



}
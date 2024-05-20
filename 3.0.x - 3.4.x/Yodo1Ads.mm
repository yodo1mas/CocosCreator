//
//  Yodo1Ads.m
//  cocosiOS-mobile
//
//  Created by Abdur Rafay Khan on 19/05/2024.
//
#import <Foundation/Foundation.h>
#import "Yodo1Ads.h"
#import "Yodo1Mas.h"
#include "SeApi.h"

@implementation Yodo1Ads

+ (instancetype)getInstance {
    static Yodo1Ads *sharedInstance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        sharedInstance = [[Yodo1Ads alloc] init];
    });
    return sharedInstance;
}

- (instancetype)init {
    self = [super init];
    if (self) {
        _rootViewController = [UIApplication sharedApplication].keyWindow.rootViewController;
    }
    return self;
}



+ (void)initializeSdkWithAppKey:(NSString *)appKey isEnabled:(BOOL)isEnabled { 
    if(isEnabled) {
        Yodo1MasAdBuildConfig *config = [Yodo1MasAdBuildConfig instance];
        config.enableUserPrivacyDialog = isEnabled;
        [[Yodo1Mas sharedInstance] setAdBuildConfig:config];
    }
    
   
    
    [[Yodo1Mas sharedInstance] initMasWithAppKey:appKey successful:^{
        NSLog(@"Yodo1 MAS SDK initialized successfully");
    } fail:^(NSError * _Nonnull error) {

    }];
}


- (void)hideBannerAds {
}


- (void)loadBannerAds:(NSString *)size horizontalAlignment:(NSString *)horizontalAlignment verticalAlignment:(NSString *)verticalAlignment {
        
}

+ (void)setCOPPA:(BOOL)isEnabled {
    [Yodo1Mas sharedInstance].isCOPPAAgeRestricted = isEnabled;
}

+ (void)setGDPR:(BOOL)isEnabled {
    [Yodo1Mas sharedInstance].isGDPRUserConsent = isEnabled;
}

+ (void)setCCPA:(BOOL)isEnabled {
    
    [Yodo1Mas sharedInstance].isCCPADoNotSell = isEnabled;
}

- (void)showBannerAds {
}

+ (void)initializeInterstitialAds {
    [Yodo1MasInterstitialAd sharedInstance].adDelegate = self.getInstance;
      [Yodo1MasInterstitialAd sharedInstance].autoDelayIfLoadFail = YES;
      [[Yodo1MasInterstitialAd sharedInstance] loadAd];
}

+ (void)showInterstitialAds {
    BOOL isLoaded = [[Yodo1MasInterstitialAd sharedInstance] isLoaded];

    // Show ad without placement name
    if(isLoaded) [[Yodo1MasInterstitialAd sharedInstance] showAd];
    
}

+ (void)initializeRewardAds {
    [Yodo1MasRewardAd sharedInstance].adDelegate = self.getInstance;
    [Yodo1MasRewardAd sharedInstance].autoDelayIfLoadFail = YES;
    [[Yodo1MasRewardAd sharedInstance] loadAd];

}

+ (void)showRewardAds {
    BOOL isLoaded = [[Yodo1MasRewardAd sharedInstance] isLoaded];

    // Show ad without placement name
    if (isLoaded) [[Yodo1MasRewardAd sharedInstance] showAd];
}




#pragma mark - Yodo1MasRewardDelegate
- (void)onRewardAdLoaded:(Yodo1MasRewardAd *)ad {
    // Code to be executed when an ad finishes loading.
    _retryAttempt = 0;
    NSLog(@"Yodo1Ads rewarded loaded");
    // Show ad without placement name
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onRewardAdLoaded()");
    //se::ScriptEngine::getInstance()->evalString("cc.find(\"Yodo1\").getComponent(\"Yodo1Ads\").onRewardAdLoaded()");
    //se::ScriptEngine::getInstance()->evalString("Yodo1Ads.onRewardAdLoaded()");
    
    //[self callJavaScriptFunction:@"cc.Yodo1Ads.getInstance().onRewardAdLoaded()"];
    //se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onRewardAdLoaded()");

    // Show ad with placement name, like app_start, level_end
    // [[Yodo1MasRewardAd sharedInstance] showAdWithPlacement:@"Your placement id"];
}

- (void)onRewardAdFailedToLoad:(Yodo1MasRewardAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad request fails.
    NSLog(@"Yodo1Ads rewarded failed to load");
    _retryAttempt++;
    NSInteger delaySec = pow(2, MIN(5, self.retryAttempt));
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, delaySec * NSEC_PER_SEC), dispatch_get_main_queue(), ^{
        [[Yodo1MasRewardAd sharedInstance] loadAd];
    
        
        
    });
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onRewardAdFailedToLoad()");
}

- (void)onRewardAdOpened:(Yodo1MasRewardAd *)ad {
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onRewardAdOpened()");
    NSLog(@"Yodo1Ads rewarded opened");
    // Code to be executed when an ad opened
}

- (void)onRewardAdFailedToOpen:(Yodo1MasRewardAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad open fails.
    [[Yodo1MasRewardAd sharedInstance] loadAd];
    
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onRewardAdFailedToOpen()");
}

- (void)onRewardAdClosed:(Yodo1MasRewardAd *)ad {
    // Code to be executed when the ad closed
    [[Yodo1MasRewardAd sharedInstance] loadAd];
    NSLog(@"Yodo1Ads rewarded closed");
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onRewardAdClosed()");
}

- (void)onRewardAdEarned:(Yodo1MasRewardAd *)ad {
    // Code executed when getting rewards
    NSLog(@"Yodo1Ads Reward Earned");
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onRewardAdEarned()");

}


#pragma mark - Yodo1MasInterstitialDelegate
- (void)onInterstitialAdLoaded:(Yodo1MasInterstitialAd *)ad {
    // Code to be executed when an ad finishes loading.
    _retryAttempt = 0;
    // Show ad without placement name
    NSLog(@"Yodo1Ads interstitial loaded");
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onInterstitialAdLoaded()");

   
}

- (void)onInterstitialAdFailedToLoad:(Yodo1MasInterstitialAd *)ad withError:(Yodo1MasError *)error {
    NSLog(@"Yodo1Ads interstitial failed to load");
    // Code to be executed when an ad request fails.
    _retryAttempt++;
    NSInteger delaySec = pow(2, MIN(5, self.retryAttempt));
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, delaySec * NSEC_PER_SEC), dispatch_get_main_queue(), ^{
        [[Yodo1MasInterstitialAd sharedInstance] loadAd];
    });
    
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onInterstitialAdFailedToLoad()");
}

- (void)onInterstitialAdOpened:(Yodo1MasInterstitialAd *)ad {
    NSLog(@"Yodo1Ads interstitial opened");
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onInterstitialAdOpened()");
    // Code to be executed when an ad opened
}

- (void)onInterstitialAdFailedToOpen:(Yodo1MasInterstitialAd *)ad withError:(Yodo1MasError *)error {
    NSLog(@"Yodo1Ads interstitial failed to open");
    // Code to be executed when an ad open fails.
    [[Yodo1MasInterstitialAd sharedInstance] loadAd];
    
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onInterstitialAdFailedToOpen()");
}

- (void)onInterstitialAdClosed:(Yodo1MasInterstitialAd *)ad {
    // Code to be executed when the ad closed
    NSLog(@"Yodo1Ads interstitial closed ");
    [[Yodo1MasInterstitialAd sharedInstance] loadAd];
    se::ScriptEngine::getInstance()->evalString("Yodo1Ads.getInstance().onInterstitialAdClosed()");
}




@end






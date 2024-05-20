//
//  Yodo1Ads.h
//  cocosiOS-mobile
//
//  Created by Abdur Rafay Khan on 19/05/2024.
//

#ifndef Yodo1Ads_h
#define Yodo1Ads_h

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "Yodo1Mas.h"




@interface Yodo1Ads : NSObject <Yodo1MasInterstitialDelegate, Yodo1MasRewardDelegate, Yodo1MasBannerAdDelegate>

@property (nonatomic, strong) Yodo1MasBanner *bannerAd;
@property (nonatomic, unsafe_unretained) UIViewController *rootViewController;
@property (nonatomic, assign) NSInteger retryAttempt;


+ (Yodo1Ads *)getInstance;


+ (void)setCOPPA:(BOOL)isEnabled;
+ (void)setGDPR:(BOOL)isEnabled;
+ (void)setCCPA:(BOOL)isEnabled;
+ (void)initializeSdkWithAppKey:(NSString *)appKey isEnabled:(BOOL)isEnabled;
+ (void)initializeInterstitialAds;
+ (void)showInterstitialAds;
+ (void)initializeRewardAds;
+ (void)showRewardAds;
- (void)loadBannerAds:(NSString *)size horizontalAlignment:(NSString *)horizontalAlignment verticalAlignment:(NSString *)verticalAlignment;
- (void)hideBannerAds;
- (void)showBannerAds;


@end



#endif /* Yodo1Ads_h */

// Learn TypeScript:
//  - https://docs.cocos.com/creator/2.4/manual/en/scripting/typescript.html
// Learn Attribute:
//  - https://docs.cocos.com/creator/2.4/manual/en/scripting/reference/attributes.html
// Learn life-cycle callbacks:
//  - https://docs.cocos.com/creator/2.4/manual/en/scripting/life-cycle-callbacks.html

const {ccclass, property} = cc._decorator;


@ccclass
export default class Yodo1Ads extends cc.Component {

    @property(cc.Label)
    label: cc.Label = null;

   // @property
    //text: string = 'hello';
    
   
    // LIFE-CYCLE CALLBACKS:

    private static _instance:Yodo1Ads = null;

    public static getInstance(){
        if(this._instance==null) this._instance = new Yodo1Ads();
        return this._instance;
    }

    public setCOPPA (isEnabled)
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "setCOPPA", "(Z)V",isEnabled);
    }

    public setGDPR (isEnabled)
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "setGDPR", "(Z)V",isEnabled);
    }

    public setCCPA (isEnabled)
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "setCCPA", "(Z)V",isEnabled);
    }


    public initializeMasSdk (appkey,isEnabled)
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "initializeSdk", "(Ljava/lang/String;Z)V",appkey,isEnabled);
    }

    public initializeInterstitialAds() 
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "initializeInterstitialAds", '()V');

    }

    public showInterstitialAds() 
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "showInterstitialAds", '()V');

    }

    public initializeRewardAds() 
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "initializeRewardAds", '()V');

    }

    public showRewardAds() 
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "showRewardAds", '()V');

    }

    public loadBannerAds(size, horizontalAlignment, verticalAlignment) 
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "loadBannerAds", '(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V',size,horizontalAlignment,verticalAlignment);

    }

    public hideBannerAds() 
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "hideBannerAds", '()V');

    }

    public showBannerAds() 
    {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/AppActivity", "showBannerAds", '()V');

    }

    //Interstitial Ad events 

    public onInterstitialAdLoaded() {

    }

    public onInterstitialAdFailedToLoad() {
        
    }

    public onInterstitialAdOpened() {
       
    }

    
    public onInterstitialAdFailedToOpen() {
       
        
    }
   
    public onInterstitialAdClosed() {
        
        
    }

    // Rewarded Ad events. 

    public onRewardAdLoaded() {
        
    }

    
    public onRewardAdFailedToLoad() {
        
    }

   
    public  onRewardAdOpened() {
        

    }

    public onRewardAdFailedToOpen() {
      
    }

    public  onRewardAdClosed() {
       
    }

    public onRewardAdEarned() {
       // Reward user here

    }

    //Banner add events
    public onBannerAdLoaded() {

    }

    public onBannerAdFailedToLoad() {

    }

    public onBannerAdOpened() {

    }

    public onBannerAdFailedToOpen() {

    }

    public onBannerAdClosed() {

    }


   

}

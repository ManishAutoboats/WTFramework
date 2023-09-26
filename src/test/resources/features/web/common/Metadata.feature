Feature: Meta data feature

  @gloDeRegression2 @gloItRegression @gloKzRegression @gloPlRegression
  @LyftDKReg @LyftRegression2 @epokRegression
  @VuseDEReg2 @VuseDKReg @VuseZAReg
  @regression @COReg @de @dk @fr @IEReg @ITReg2 @MXReg @nl @Cxregression
  @GlobalSubsFR @PDFSubsPro
  @gloDeLive @gloItLive @gloKzLive @gloPlLive
  @LyftDKLive @LyftLive @epokLive
  @VuseDELive @VuseDKLive @VuseZALive
  @CoLive @delive @dklive @frlive @IElive @ITLive @MXLive @NLlive @live
  @NonCaptchaLive @VuseUKLive2 @VuseFRReg3 @VuseITAnonReg2 @VuseMXReg @VuseMXLive
  Scenario: verify robots meta data tag content
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    Then assert "robots" meta data tag content
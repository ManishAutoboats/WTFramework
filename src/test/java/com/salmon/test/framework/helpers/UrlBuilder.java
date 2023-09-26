package com.salmon.test.framework.helpers;

import com.amazonaws.services.route53domains.model.CountryCode;
import com.salmon.test.enums.PermittedBrowserMode;
import com.salmon.test.enums.PermittedMobileMode;
import com.salmon.test.enums.PermittedSiteMode;
import org.apache.xpath.operations.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.salmon.test.framework.helpers.WebDriverHelper.*;

public class UrlBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    private static URL basePath;
    private static URL apiUrl;
    public static String ENVIRONMENT;
    public static String LOCALE;
    public static String url;
    private static Properties profileProperties;
    private static String regionCode;
    public static String env;
    public static String storeCode;
    public static String SITE;
    public static String SCOPE;
    public static String countryCode;

    static {
        try {
            profileProperties = Props.getProfileProps();
            env=getEnv();
            regionCode=getRegionCode();
            basePath = new URL(Props.getProp("site.url"));
            //apiUrl = new URL(Props.getProp("api.url"));
            apiUrl = new URL(Props.getProp("api.url")+regionCode+"/"+env+"/"+storeCode+"/");

        } catch (MalformedURLException e) {
            LOG.error(e.getMessage());
        }

    }

    public static String getLocale(){
        return LOCALE;
    }

    public static boolean isDesktop(){
        return getSiteMode()==PermittedSiteMode.DESKTOP;
    }

    public static boolean isFirefox(){
        return getBrowserMode()== PermittedBrowserMode.FIREFOX;
    }

    public static boolean isEdge(){
        return getBrowserMode()== PermittedBrowserMode.EDGE;
    }

    public static boolean isSafari(){
        return getBrowserMode()== PermittedBrowserMode.SAFARI;
    }

    public static boolean isMobile(){
        return getSiteMode()==PermittedSiteMode.MOBILE;
    }

    public static boolean isSamsungMobile(){
        return getMobileMode()== PermittedMobileMode.SAMSUNG;
    }

    public static boolean isIPhone(){
        return getMobileMode()== PermittedMobileMode.IPHONE;
    }

    public static boolean isIpad(){
        return getMobileMode()== PermittedMobileMode.IPAD;
    }

    public static boolean isTablet(){
        return getSiteMode()==PermittedSiteMode.TABLET;
    }

    public static void setSite(final String site)
    {
        SITE = site;
    }

    public static String getSite()
    {
        return SITE;
    }

    public static void setEnvironment(final String env)
    {
        ENVIRONMENT = env;
    }

    public static void setLocale(final String newLocale)
    {
        LOCALE = newLocale;
    }

    public static void setStoreCode(final String newStoreCode)
    {
        storeCode = newStoreCode;
    }

    public static void setRegionCode(final String newRegionCode)
    {
        regionCode = newRegionCode;
    }

    public static String getEnv()
    {
        try {
            if (isGlobalInstance()) {
                return ENVIRONMENT.split("-")[1];
            } else {
                return ENVIRONMENT;
            }
        }
        catch(final Exception ignored){
            return "";
        }
    }

    public static String getRegionCode()
    {
        try {
            if (isGlobalInstance()) {
                return "region_" + ENVIRONMENT.split("-")[0];
            } else {
                return "region_" + countryCode.replaceAll("-","");
            }
        }
        catch(final Exception ignored){
            return "";
        }
    }

    public static String getSecret() {
        return Props.getProp("ssh.user.secret");
    }

    public static void startAtHomePage() {
        WebDriverHelper.getWebDriver().navigate().to((basePath));
    }

    public static void startAtMobileHomePage()
    {
        AndroidHelper.getAndroidWebDriver().navigate().to(basePath);
    }

    public static void navigateToUrl(String url) {
        WebDriverHelper.getWebDriver().get(url);
    }

    public static void navigateToRelativeUrl(String relativeUrl) {
        UrlBuilder.navigateToUrl(UrlBuilder.getUrl() + "/" +
                (relativeUrl.startsWith("/")? relativeUrl.substring(1) : relativeUrl));
    }

    public static URL getApiUrlForEndPoint(String endpoint) {
        return createApiUrl(endpoint);
    }

    public static URI getBasePathURI() {
        //return URI.create(Props.getProp("api.url"));
        env=getEnv();
        regionCode=getRegionCode();
        String url=Props.getProp("api.url")+regionCode+"/"+env+"/"+storeCode+"/";
        return URI.create(url.toString());
//        return URI.create(apiUrl.toString());
    }

    public static String getHttpRequestPathURI(String site) {
        String region = site.split("-")[0];
        String brand = site.split("-")[1];
        String language = site.split("-")[2];
        if (brand.equals("epok")) {
            brand = "velo";
        }
        String newUrl = "https://www-" + region + "-" + getEnv() + "-global-" + brand + ".non-prod.marketing.bat.net/" + language;
        return newUrl;
    }

    public static String getStandardAPIPathURI() {
        String newUrl = "https://www-" + regionCode.substring(regionCode.length() - 2) + "-" + getEnv() + "-global-" + getSite() + ".non-prod.marketing.bat.net/rest/" + storeCode;
        return newUrl;
    }

    public static String getISSAPIPathURI() {
        String rc = regionCode.substring(regionCode.length() - 2);
        return "https://api-" + rc + ".non-prod.marketing.bat.net/region_" + rc + "/" + getEnv() + "/" + storeCode;
    }

    public static String getUserName() {
        return Props.getProp("api.auth.username");
    }

    public static String getPassWord() {
        return Props.getProp("api.auth.password");
    }

    public static String getUrl(String applicationUrl) {
        return Props.getProp(applicationUrl);
    }

    public static URL createApiUrl(String endpoint) {
        try {
            return new URL(apiUrl.getProtocol(), apiUrl.getHost(), apiUrl.getPort(), endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public static URL createUrl(String path) {
        try {
            return new URL(basePath.getProtocol(), basePath.getHost(), basePath.getPort(), path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static URI getSystestIP() {
        return URI.create(Props.getProp("host.ip"));
    }

    public static URI getSystestUser() {
        return URI.create(Props.getProp("host.user"));
    }

    public static URI getSystestPassword() {
        return URI.create(Props.getProp("host.user.pwd"));
    }

    public static String readValueFromConfig(String key) {
        return Props.getProp(key);
    }

    public static void startBATHomePage() {
        WebDriverHelper.startDriver(getSiteMode());
        LOG.info("**** URL : " + url);
        if (!System.getProperty("override.url", Props.getProp("override.url")).equals("")) {
            url = System.getProperty("override.url", Props.getProp("override.url")) + getEndPoints(LOCALE);
            WebDriverHelper.getWebDriver().navigate().to(url);
        } else {
            // global instance
            if (ENVIRONMENT.contains("eu-") || ENVIRONMENT.contains("am-")) {
                SCOPE = "global";
                countryCode = "";
                // dedicated instance
            } else {
                switch (SITE) {
                    case "glo":
                        if (getLocale().equals("glode")) {
                            SCOPE = "germany";
                            countryCode = "de-";
                            break;
                        }
                        if (getLocale().equals("it")) {
                            SCOPE = "italy";
                            countryCode = "it-";
                            break;
                        } else {
                            SCOPE = "global";
                            countryCode = "";
                            break;
                        }
                }
            }
            url = "https://www-" + countryCode + ENVIRONMENT + "-" + SCOPE + "-" + SITE + ".non-prod.marketing.bat.net/" + getEndPoints(LOCALE);
            WebDriverHelper.getWebDriver().navigate().to(url);
            LOG.info("Navigating to: " + url);
        }
    }

    public static void startAvalancheHomePage(String language) {
        WebDriverHelper.startDriver(getSiteMode());LOG.info("**** URL : " + url);
        switch (getLocale()) {
            case "veloza":
                if (!System.getProperty("override.url", Props.getProp("override.url")).equals("")) {
                    url = System.getProperty("override.url", Props.getProp("override.url")) + getEndPoints(LOCALE);
                    if (System.getProperty("override.url") .equals("https://www.velo.com/")) {
                        WebDriverHelper.getWebDriver().navigate().to(url);
                    } else {
                        LOG.info("URL not Define " + url);
                        WebDriverHelper.getWebDriver().navigate().to(url);
                        LOG.info("Navigating to: " + url);
                        url = url.replace("eu-uat6", ENVIRONMENT);
                        WebDriverHelper.getWebDriver().navigate().to(url);
                        url = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/" + getEndPoints(LOCALE);
                        WebDriverHelper.getWebDriver().navigate().to(url);
                    }}
              break;
            default:
        if (!System.getProperty("override.url", Props.getProp("override.url")).equals("")) {
            url = System.getProperty("override.url", Props.getProp("override.url")) + getEndPoints(LOCALE);
            // ensure the correct language
            url = url.replace("be/en", "be/" + language);
            WebDriverHelper.getWebDriver().navigate().to(url);
        } else {
            url = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/" + getEndPoints(LOCALE);
            if (UrlBuilder.getLocale().equalsIgnoreCase("velopl")) {
                url = url.replace("gb/en", "pl/" + language);
            }
            else
            {
                url = url.replace("be/en", "be/" + language);
            }
            WebDriverHelper.getWebDriver().navigate().to(url);
            LOG.info("Navigating to: " + url);
        }}
    }


    public static void revisitBATHomePage() {
        LOG.info("**** URL : " + url);
        if (!System.getProperty("override.url", Props.getProp("override.url")).equals("")) {
            url = System.getProperty("override.url", Props.getProp("override.url")) + getEndPoints(LOCALE);
            WebDriverHelper.getWebDriver().navigate().to(url);
        } else {
            url = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/" + getEndPoints(LOCALE);
            WebDriverHelper.getWebDriver().navigate().to(url);
            LOG.info("Navigating to: " + url);
        }
    }

    public static void startInStoreSubsHomePage() {
        WebDriverHelper.startDriver(getSiteMode());
        if (!System.getProperty("override.url", Props.getProp("override.url")).equals("")) {
            url = System.getProperty("override.url", Props.getProp("override.url")) + getEndPoints(LOCALE) + "/iss/app/";
            WebDriverHelper.getWebDriver().navigate().to(url);
        } else {
            url = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/" + getEndPoints(LOCALE) + "/iss/app/";
            LOG.info("Navigating to: " + url);
            WebDriverHelper.getWebDriver().navigate().to(url);
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getEndPoints(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        }
        return ResourceBundle.getBundle("endpoints/locale").getString(key).trim();
    }

    public static void resumeInStoreSubsHomePage() {
        url = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/" + getEndPoints(LOCALE) + "/iss/app/";
        WebDriverHelper.getWebDriver().navigate().to(url);
        LOG.info("Navigating to: " + url);
    }

    /**
     * Gets the key from locales for a Site depending on the country selected
     *
     * @param key
     **/
    public static String getMessage(String key) {
        String url = "";
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            switch (LOCALE.toLowerCase()) {
                case "uk":
                    url = ResourceBundle.getBundle("locales/uk").getString(key).trim();
                    break;
                case "us":
                    url = ResourceBundle.getBundle("locales/us").getString(key).trim();
                    break;
                case "lyftdk":
                    url = ResourceBundle.getBundle("locales/lyftdk").getString(key).trim();
                    break;
                case "dk":
                    url = ResourceBundle.getBundle("locales/dk").getString(key).trim();
                    break;
                case "nl":
                    url = ResourceBundle.getBundle("locales/nl").getString(key).trim();
                    break;
                case "it":
                    url = ResourceBundle.getBundle("locales/it").getString(key).trim();
                    break;
                case "de":
                    url = ResourceBundle.getBundle("locales/de").getString(key).trim();
                    break;
                case "fr":
                    url = ResourceBundle.getBundle("locales/fr").getString(key).trim();
                    break;
                case "mx":
                    url = ResourceBundle.getBundle("locales/mx").getString(key).trim();
                    break;
                case "epok":
                    url = ResourceBundle.getBundle("locales/epok").getString(key).trim();
                    break;
                case "glode":
                    url = ResourceBundle.getBundle("locales/glode").getString(key).trim();
                    break;
                case "vypeit":
                    url = ResourceBundle.getBundle("locales/vypeit").getString(key).trim();
                    break;
                case "lyftse":
                    url = ResourceBundle.getBundle("locales/lyftse").getString(key).trim();
                    break;
                case "ie":
                    url = ResourceBundle.getBundle("locales/ie").getString(key).trim();
                    break;
                case "uklive":
                    url = ResourceBundle.getBundle("locales/uk").getString(key).trim();
                    break;
                case "kz":
                    url = ResourceBundle.getBundle("locales/kz").getString(key).trim();
                    break;
                case "pl":
                    url = ResourceBundle.getBundle("locales/pl").getString(key).trim();
                    break;
                case "vuseza":
                    url = ResourceBundle.getBundle("locales/vuseza").getString(key).trim();
                    break;
                case "vusedk":
                    url = ResourceBundle.getBundle("locales/vusedk").getString(key).trim();
                    break;
                case "vusede":
                    url = ResourceBundle.getBundle("locales/vusede").getString(key).trim();
                    break;
                case "vuseuk":
                    url = ResourceBundle.getBundle("locales/vuseuk").getString(key).trim();
                    break;
                case "glojp":
                    url = ResourceBundle.getBundle("locales/glojp").getString(key).trim();
                    break;
                case "vusefr":
                    url = ResourceBundle.getBundle("locales/vusefr").getString(key).trim();
                    break;
                case "vuseuklive":
                    url = ResourceBundle.getBundle("locales/vuseuk").getString(key).trim();
                    break;
                case "vuseit":
                    url = ResourceBundle.getBundle("locales/vuseit").getString(key).trim();
                    break;
                case "velode":
                    url = ResourceBundle.getBundle("locales/velode").getString(key).trim();
                    break;
                case "velobe":
                    url = ResourceBundle.getBundle("locales/velobe").getString(key).trim();
                    break;
                case "velopl":
                    url = ResourceBundle.getBundle("locales/velopl").getString(key).trim();
                    break;
                case "veloza":
                    url = ResourceBundle.getBundle("locales/veloza").getString(key).trim();
                    break;
                case "vuseco":
                    url = ResourceBundle.getBundle("locales/vuseco").getString(key).trim();
                    break;
                case "vusemx":
                    url = ResourceBundle.getBundle("locales/vusemx").getString(key).trim();
                    break;
            }
        }
        return url;
    }

    public static void navigateToPdpPage() throws MalformedURLException {
        WebDriverHelper.getWebDriver().navigate().to(Props.getProp("test.magento.url") + getEndPoints("it") + "/dispositivi/glo-pro");
    }

    public static void navigateToCheckoutPage() throws MalformedURLException {
        WebDriverHelper.getWebDriver().navigate().to(Props.getProp("test.magento.url") + getEndPoints("it") + "/checkout/");
    }

    private static boolean isGlobalInstance() {
        return ENVIRONMENT.contains("eu-") || ENVIRONMENT.contains("am-");
    }

    public static void startMagentoAdminHomePage() {
        String newUrl;
//      WebDriverHelper.getWebDriver().navigate().to((Props.getProp("test.magento.admin.url")));
        if (isGlobalInstance()) {
            newUrl = "https://admin-" + ENVIRONMENT + "-global.non-prod.marketing.bat.net/smc";
        } else {
            switch (SITE) {
                case "glo":
                    if (getLocale().equals("glode")) {
                        SCOPE = "germany";
                        countryCode = "de-";
                        break;
                    }
                    if (getLocale().equals("it")) {
                        SCOPE = "italy";
                        countryCode = "it-";
                        break;
                    } else {
                        SCOPE = "global";
                        countryCode = "";
                        break;
                    }
            }
            newUrl = "https://admin-" + countryCode + ENVIRONMENT + "-" + SCOPE + ".non-prod.marketing.bat.net/smc";
        }
        WebDriverHelper.getWebDriver().navigate().to(newUrl);
        LOG.info("Navigating to: " + newUrl);
    }

    private static String constructInstanceUrl() {
        switch (SITE) {
            case "glo":
                switch (getLocale()) {
                    case "glode":
                        SCOPE = "germany";
                        countryCode = "de-";
                        break;
                    case "it":
                        SCOPE = "italy";
                        countryCode = "it-";
                        break;
                    default:
                        SCOPE = "global";
                        countryCode = "";
                        break;
                }
        }
        return "https://www-" + countryCode + ENVIRONMENT + "-" + SCOPE + "-" + SITE + ".non-prod.marketing.bat.net/";
//        try {
//            return "https://" + getMessage("authUser") + ":" + getMessage("authPassword") + "@" + url + "?returnUrl=https://" + url + "/";
//        } catch (Exception e) {
//            return "https://" + url + "/";
//        }
    }

    public static void navigateToBATHomePage() {
        String newUrl;
        if (isGlobalInstance()) {
            newUrl = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/";
        } else {
            newUrl = constructInstanceUrl();
        }
        WebDriverHelper.getWebDriver().navigate().to(addAuthToUrl(newUrl) + getEndPoints(LOCALE));
        LOG.info("Navigating to: " + newUrl + getEndPoints(LOCALE));
    }

    private static String addAuthToUrl(String url) {
        try {
            String authUser = getMessage("authUser");
            String authPassword = getMessage("authPassword");
            return "https://" + authUser + ":" + authPassword + "@" + url + "?returnUrl=https://" + url + "/";
        } catch (Exception e) {
            return url;
        }
    }

    public static Map<String, String> profilePropsMap() {
        Map<String, String> propertiesMap = new HashMap<>();
        profileProperties.stringPropertyNames().forEach(propName -> {
            propertiesMap.put(propName, profileProperties.getProperty(propName));
        });
        return propertiesMap;
    }

    public static void startDeviceTrialLandingPage() {
        String newUrl;
        if (!System.getProperty("override.url", Props.getProp("override.url")).equals("")) {
            WebDriverHelper.getWebDriver().navigate().to(System.getProperty("override.url", Props.getProp("override.url")) + getEndPoints(LOCALE));
        } else {
            if (isGlobalInstance()) {
                newUrl = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/";
            } else {
                newUrl = constructInstanceUrl();
            }
            WebDriverHelper.getWebDriver().navigate().to(newUrl + getEndPoints(LOCALE) + "/devicetrial/");
            LOG.info("Navigating to: " + newUrl + getEndPoints(LOCALE) + "/devicetrial/");

        }
    }

    public static void revisitDeviceTrialLandingPage() {
        String newUrl = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/";
        LOG.info("Navigating to: " + newUrl + getEndPoints(LOCALE) + "/devicetrial/");
        getWebDriver().get(newUrl + getEndPoints(LOCALE) + "/devicetrial/");
    }

    public static void startFreeDeviceTrialLandingPage() {
        WebDriverHelper.startDriver(getSiteMode());
        if (!System.getProperty("override.url", Props.getProp("override.url")).equals("")) {
            WebDriverHelper.getWebDriver().navigate().to(System.getProperty("override.url", Props.getProp("override.url")) + getEndPoints(LOCALE));
        } else {
            String newUrl = "https://www-" + ENVIRONMENT + "-global-" + SITE + ".non-prod.marketing.bat.net/";
            WebDriverHelper.getWebDriver().navigate().to(newUrl + getEndPoints(LOCALE) + "/devicetrial/");
            LOG.info("Navigating to: " + newUrl + getEndPoints(LOCALE) + "/devicetrial/");
        }
    }

    public static void navigateToAnyPDP() {
        WebDriverHelper.getWebDriver().navigate().to(UrlBuilder.getUrl() + getMessage("pdpDirectUrl.key"));
        LOG.info("Navigating to: " + UrlBuilder.getUrl() + getMessage("pdpDirectUrl.key"));
    }

    public static void startFlavourToolsLandingPage() {
        WebDriverHelper.getWebDriver().navigate().to(UrlBuilder.getUrl() + "/whats-your-flavour");
        LOG.info("Navigating to: " + url);
    }
}

package com.salmon.test.framework.helpers;

import com.salmon.test.enums.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class Props {
    private static final Logger LOG = LoggerFactory.getLogger(Props.class);
    private static Properties environmentProps;
    private static Properties properties;
    public static String storeCode;
    private static Locale locale;
    private static String env;
    private static String site;
    private static String testSuite;
    private static String overrideUrl;
    private static final String DEFAULT_ENV = "eu-uat1";
    private static final String DEFAULT_SITE = "vuse";
    private static final String DEFAULT_LOCAL = "uk";
    private static final String DEFAULT_TEST_SUITE = "Automation";
    private static final String DEFAULT_STORECODE = "vuse_uk_en_gb";
    public static boolean EYES_ON = false;
    public static boolean USE_EYES_GRID = false;

    static {
        loadRunConfigProps("/environment.properties");
    }

    /**
     * Gets the key from messages.properties for a Site
     *
     * @param key
     **/
    public static String getMessage(String key) {

        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return ResourceBundle.getBundle("props/messages").getString(key);

        }
    }

    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return properties.getProperty(key);

        }
    }


    public static void loadRunConfigProps(String configPropertyFileLocation) {
        environmentProps = new Properties();
        try (InputStream inputStream = Props.class.getResourceAsStream(configPropertyFileLocation)) {
            environmentProps.load(inputStream);
            environmentProps.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        properties = new Properties();
        try (InputStream inputStream = Props.class.getResourceAsStream(environmentProps.getProperty("profile.path"))) {
            properties.load(inputStream);
            properties.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        try {
            testSuite = environmentProps.getProperty("test-suite");

            // If test-suite is unset, set it to default.
            if ("${test-suite}".equalsIgnoreCase(testSuite)) {
                testSuite = DEFAULT_TEST_SUITE;
            }
        } catch (final Exception ignored) {
            testSuite = DEFAULT_TEST_SUITE;
        }
        EYES_ON = Boolean.parseBoolean(environmentProps.getProperty("eyesOn"));
        USE_EYES_GRID = Boolean.parseBoolean(environmentProps.getProperty("useEyesGrid"));
        try {
            overrideUrl = environmentProps.getProperty("override.url");
            System.setProperty("override.url", overrideUrl);

            // If test-suite is unset, set it to default.
            if ("${override.url}".equalsIgnoreCase(overrideUrl)) {
                overrideUrl = null;
                System.setProperty("override.url", "");
            }
        } catch (final Exception ignored) {
            System.setProperty("override.url", "");
        }

        try {
            env = environmentProps.getProperty("env");

            // If env is unset, set it to default.
            if ("${env}".equalsIgnoreCase(env)) {
                UrlBuilder.setEnvironment(DEFAULT_ENV);
            } else {
                UrlBuilder.setEnvironment(env);
            }

        } catch (final Exception ignored) {
            UrlBuilder.setEnvironment(env);
        }

        try {
            final String localeCode = environmentProps.getProperty("defaultLocale");

            if ("${defaultLocale}".equalsIgnoreCase(localeCode)) {
                // If localeCode is unset, set it to default.
                UrlBuilder.setLocale(DEFAULT_LOCAL);
                UrlBuilder.setSite(DEFAULT_SITE);
            } else {
                UrlBuilder.setLocale(localeCode);
                final String SITE = environmentProps.getProperty("site");
                UrlBuilder.setSite(SITE);
            }
        } catch (final Exception ignored) {
            UrlBuilder.setLocale(DEFAULT_LOCAL);
            UrlBuilder.setSite(DEFAULT_SITE);
        }
        try {
            storeCode = environmentProps.getProperty("storeCode");
            if ("${storeCode}".equalsIgnoreCase(storeCode)) {
                storeCode = getStoreCode();
            }
            UrlBuilder.setStoreCode(storeCode);
            UrlBuilder.setLocale(Store.valueOf(UrlBuilder.storeCode).getLocale());
        } catch (final Exception ignored) {
            storeCode = DEFAULT_STORECODE;
            UrlBuilder.setStoreCode(storeCode);
        }
    }

    public static String getTestSuite() {
        return testSuite;
    }

    public static Properties getProfileProps() {
        return properties;
    }

    public static String getLanguageCode() {
        return storeCode.substring(storeCode.length() - 5, storeCode.length() - 3).toUpperCase();
    }

    public static String getCountryId() {
        if(UrlBuilder.storeCode.equals("vuse_uk_en_gb")) {
            return "GB";
        }
        else
        {
        return UrlBuilder.storeCode.substring(UrlBuilder.storeCode.length() - 8, UrlBuilder.storeCode.length() - 6).toUpperCase();
        }
    }

    public static Properties getEnvironmentProps() {
        return environmentProps;
    }

    public static String getStoreCode() {
        site=UrlBuilder.getSite().toLowerCase();
        String newLocale=UrlBuilder.getLocale().toLowerCase();
        String tmp="";
        if(newLocale.contains("epok")){
            tmp="epok_de";
        }
        else if (newLocale.contains("velobe")){
            tmp=site+"_eu_"+newLocale.replace("velo","");
        }
        else if (newLocale.contains("veloza")){
            tmp=site+"_za_en_"+newLocale.replace("velo","");
        }
        else if (newLocale.contains("velopl")){
            tmp=site+"_eu_"+newLocale.replace("velo","")+"_"+newLocale.replace("velo","");
        }
        else if (newLocale.contains("vype")){
            tmp=site+"_"+newLocale.replace("vype","");
        }
        else if (newLocale.contains("vuse")){
            tmp=site+"_"+newLocale.replace("vuse","");
        }
        else if (newLocale.contains("glo")){
            tmp=site+"_"+newLocale.replace("glo","");
        }
        else if (newLocale.contains("lyft")){
            tmp=site+"_"+newLocale.replace("lyft","");
        }
        else{
            tmp=site+"_"+newLocale;
        }
        Class clz = Store.class;
        for (Object obj: clz.getEnumConstants()) {
            if(obj.toString().contains(tmp)){
                return obj.toString();
            }
        }
        return DEFAULT_STORECODE;
    }

    public static String getLocalMessage(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        }
        else if(key.startsWith("error.")||key.equals("loginValidPassword.key")||key.equals("loginValidEmail.key"))
        {
            String url = "";
            switch (getLanguageCode().toLowerCase()) {
                case "en":
                    url = "locales/vuseuk";
                    break;
                case "dk":
                    url = "locales/dk";
                    break;
                case "nl":
                    url = "locales/nl";
                    break;
                case "it":
                    url = "locales/it";
                    break;
                case "de":
                    url = "locales/vusede";
                    break;
                case "fr":
                    url = "locales/vusefr";
                    break;
                case "es":
                    url = "locales/mx";
                    break;
                case "ko":
                    url = "locales/kr";
                    break;
                case "sv":
                    url = "locales/lyftse";
                    break;
                case "ie":
                    url = "locales/ie";
                    break;
                case "ja":
                    url = "locales/glojp";
                    break;
                case "ru":
                    url = "locales/kz";
                    break;
                case "pl":
                    url = "locales/pl";
                    break;
                case "co":
                    url = "locales/vuseco";
                    break;
            }
            try {
                LOG.info("The error message is: "+ResourceBundle.getBundle(url).getString(key).trim());
                return ResourceBundle.getBundle(url).getString(key).trim();
            } catch (MissingResourceException e) {
                LOG.info("The error message is: "+ResourceBundle.getBundle("locales/vuseuk").getString(key));
                return ResourceBundle.getBundle("locales/vuseuk").getString(key);
            }
        }
        else {
            return key;
        }
    }


}

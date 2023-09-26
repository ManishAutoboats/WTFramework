package com.salmon.test.framework.helpers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidHelper extends EventFiringWebDriver {

    private static String VENDOR;
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static AndroidDriver<WebElement> ANDROID_DRIVER = null;
    private static final Thread CLOSE_THREAD = new Thread() {

        @Override
        public void run() {
            ANDROID_DRIVER.quit();
        }
    };

    private static String APP_NAME;
    private static String APP_Package;
    private static String APP_Activity;
    private static String PLATFORM_NAME;
    private static String DEVICE_NAME;

    static {
        Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
        VENDOR = Props.getProp("vendor");

        APP_NAME = Props.getProp("app");
        APP_Package = Props.getProp("appPackage");
        APP_Activity = Props.getProp("appActivity");
        PLATFORM_NAME = Props.getProp("platform");
        DEVICE_NAME = Props.getProp("deviceName");

        ANDROID_DRIVER = startAppiumDriver();
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private AndroidHelper() {
        super(ANDROID_DRIVER);
    }


    private static AndroidDriver<WebElement> startAppiumDriver() {
        try {
            if (VENDOR.equalsIgnoreCase("MoTel")) {
                DesiredCapabilities capabilities = getMoTelDesiredCapabilities();
                ANDROID_DRIVER = new AndroidDriver<>(new URL("https://portal.motel.io/wd/hub"), capabilities);
            } else {

                DesiredCapabilities capabilities = getAppiumDesiredCapabilities();
                ANDROID_DRIVER = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ANDROID_DRIVER;
    }


    private static DesiredCapabilities getMoTelDesiredCapabilities() {
        // code to run the WebApp on MoTeL devices
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout", 240);
        capabilities.setPlatform(Platform.ANDROID);
        //capabilities.setPlatform(Platform.IOS);
        capabilities.setCapability("browserName", "Chrome");
        //capabilities.setCapability("browserName","Safari");
        // capabilities.setCapability("udid", setDeviceName());
        //capabilities.setCapability("udid","071607ed83000603");
        capabilities.setCapability("token", "0cb878c3cd004005a36cf7dbbf3ef0289f5ff0ebd7ea438db454233212d61940");
        //capabilities.setCapability("token", USER_TOKEN);
        //capabilities.setCapability("unicodeKeyboard", true);
        //capabilities.setCapability("resetKeyboard", true);
        return capabilities;


        //Code to run the NativeApp on MoTel or Physical device attached to computer

        /*File appDir = new File("https://drive.google.com/uc?export=download&id=0B9mXGOUgBBxUT0xBZ3hDa1AtQW8");
        File app = new File(appDir, APP_NAME);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.ANDROID);
        //capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("appPackage", APP_Package);
        capabilities.setCapability("appActivity", APP_Activity);
        capabilities.setCapability("deviceName", DEVICE_NAME);
        //capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("app", "https://drive.google.com/uc?export=download&id=0B9mXGOUgBBxUT0xBZ3hDa1AtQW8");
        capabilities.setCapability("automationName", "uiautomator2");
        //capabilities.setCapability("deviceName","Nexus 5X");
        capabilities.setCapability("token", "0483d18d8a0d4511bd7b292725203878cfd64c3f05cd43d3889dd5b74e9f5c3b");
        return capabilities;*/
    }

    private static DesiredCapabilities getAppiumDesiredCapabilities() {

       /* //code to run the mobile test on physical device
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("appiumVersion", "1.5.2");
        caps.setCapability("deviceName","Samsung Galaxy S6 Device");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("platformVersion", "5.1.1");
        caps.setCapability("platformName","Android");
        return caps;
*/

        // Code to runt the native app on physical device connected to computer
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/tools");
        File app = new File(appDir, APP_NAME);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //String ANDROID_DEVICE_SOCKET = APP_Package + "_devtools_remote";
        //capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
        capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
        capabilities.setCapability("appPackage", APP_Package);
        capabilities.setCapability("appActivity", APP_Activity);
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("noReset", true);
        return capabilities;
    }

    public static AndroidDriver<WebElement> getAndroidWebDriver() {
        return ANDROID_DRIVER;
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }
}

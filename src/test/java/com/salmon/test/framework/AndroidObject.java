package com.salmon.test.framework;

import com.salmon.test.framework.helpers.AndroidHelper;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.time.Duration;

public abstract class AndroidObject {
    private static final long DRIVER_WAIT_TIME = 10;
    private static final Logger LOG = LoggerFactory.getLogger(AndroidObject.class);

    @Getter
    protected AndroidDriver androidDriver;


    protected AndroidObject() {
        this.androidDriver = AndroidHelper.getAndroidWebDriver();
    }

    public void swipe(int startx, int starty, int endx, int endy, int duration) {
        //androidDriver.swipe(startx, starty, endx, endy, duration);
        (new TouchAction(androidDriver))
                .press(PointOption.point(startx, starty))
                .moveTo(PointOption.point(endx, endy))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .release()
                .perform();

    }

}
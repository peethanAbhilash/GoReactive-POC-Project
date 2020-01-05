package com.wm.rx.refill.mgt.request.util;

import org.aspectj.lang.annotation.Pointcut;

/**
 * A Common config class for all point cut definition
 */
// Notes : AOP to ne used later s
public class CommonJointPointCutConfig {

    /**
     * Tracking execution time. Apply @TrackTime where ever you want to track consumption time.
     */
    @Pointcut("@annotation(com.wm.rx.refill.mgt.request.util.TrackTime)")
    public void trackPerformaceTime() {
    }

}

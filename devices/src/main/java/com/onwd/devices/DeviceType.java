package com.onwd.devices;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
public @interface DeviceType {
    int TYPE_A = 0;
    int TYPE_B = 1;
}

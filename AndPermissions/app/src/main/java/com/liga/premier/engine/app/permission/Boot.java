/*
 * Copyright 2018 Zhenjie Yan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liga.premier.engine.app.permission;

import android.os.Build;

import com.liga.premier.engine.app.permission.install.InstallRequest;
import com.liga.premier.engine.app.permission.install.NRequestFactory;
import com.liga.premier.engine.app.permission.install.ORequestFactory;
import com.liga.premier.engine.app.permission.notify.Notify;
import com.liga.premier.engine.app.permission.notify.option.NotifyOption;
import com.liga.premier.engine.app.permission.option.Option;
import com.liga.premier.engine.app.permission.overlay.LRequestFactory;
import com.liga.premier.engine.app.permission.overlay.MRequestFactory;
import com.liga.premier.engine.app.permission.overlay.OverlayRequest;
import com.liga.premier.engine.app.permission.runtime.Runtime;
import com.liga.premier.engine.app.permission.runtime.option.RuntimeOption;
import com.liga.premier.engine.app.permission.setting.Setting;
import com.liga.premier.engine.app.permission.source.Source;

/**
 * Created by Zhenjie Yan on 2018/4/28.
 */
public class Boot implements Option {

    private static final InstallRequestFactory INSTALL_REQUEST_FACTORY;
    private static final OverlayRequestFactory OVERLAY_REQUEST_FACTORY;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            INSTALL_REQUEST_FACTORY = new ORequestFactory();
        } else {
            INSTALL_REQUEST_FACTORY = new NRequestFactory();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            OVERLAY_REQUEST_FACTORY = new MRequestFactory();
        } else {
            OVERLAY_REQUEST_FACTORY = new LRequestFactory();
        }
    }

    public interface InstallRequestFactory {

        /**
         * Create apk installer request.
         */
        InstallRequest create(Source source);
    }

    public interface OverlayRequestFactory {

        /**
         * Create overlay request.
         */
        OverlayRequest create(Source source);
    }

    private Source mSource;

    public Boot(Source source) {
        this.mSource = source;
    }

    @Override
    public RuntimeOption runtime() {
        return new Runtime(mSource);
    }

    @Override
    public InstallRequest install() {
        return INSTALL_REQUEST_FACTORY.create(mSource);
    }

    @Override
    public OverlayRequest overlay() {
        return OVERLAY_REQUEST_FACTORY.create(mSource);
    }

    @Override
    public NotifyOption notification() {
        return new Notify(mSource);
    }

    @Override
    public Setting setting() {
        return new Setting(mSource);
    }
}

/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.rxsystemsettings.adapter;

import android.content.ContentResolver;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.ivianuu.rxsystemsettings.SettingsType;

/**
 * Reads and writes string values
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class StringAdapter implements Adapter<String> {

    public static final Adapter<String> INSTANCE = new StringAdapter();

    @NonNull
    @Override
    public String get(@NonNull String name,
                      @NonNull String defaultValue,
                      @NonNull ContentResolver contentResolver,
                      @SettingsType int type) {
        String value;
        switch (type) {
            case SettingsType.GLOBAL:
                value = Settings.Global.getString(contentResolver, name);
                break;
            case SettingsType.SECURE:
                value = Settings.Secure.getString(contentResolver, name);
                break;
            case SettingsType.SYSTEM:
                value = Settings.System.getString(contentResolver, name);
                break;
            default:
                throw new IllegalArgumentException("unknown type " + type);
        }

        return value != null ? value : defaultValue;
    }

    @Override
    public void set(@NonNull String name,
                    @NonNull String value,
                    @NonNull ContentResolver contentResolver,
                    @SettingsType int type) {
        switch (type) {
            case SettingsType.GLOBAL:
                Settings.Global.putString(contentResolver, name, value);
                break;
            case SettingsType.SECURE:
                Settings.Secure.putString(contentResolver, name, value);
                break;
            case SettingsType.SYSTEM:
                Settings.System.putString(contentResolver, name, value);
                break;
            default:
                throw new IllegalArgumentException("unknown type " + type);
        }
    }
}

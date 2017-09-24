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

package com.ivianuu.rxsystemsettings;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.ivianuu.rxcontentobserver.RxContentObserver;

import io.reactivex.Observable;

/**
 * Creates content observer observables
 */
final class ContentObserverFactory {

    private final Context context;

    ContentObserverFactory(@NonNull Context context) {
        this.context = context;
    }

    /**
     * Emits on content changes of the uri
     */
    @CheckResult @NonNull
    Observable<Object> observe(@NonNull Uri uri) {
        return RxContentObserver.observe(context, uri).cast(Object.class);
    }
}

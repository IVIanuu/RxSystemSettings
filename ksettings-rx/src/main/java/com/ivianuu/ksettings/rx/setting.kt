/*
 * Copyright 2018 Manuel Wrage
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

package com.ivianuu.ksettings.rx

import com.ivianuu.ksettings.Setting
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

/**
 * Returns a [Observable] which emits on changes of [this]
 */
fun <T> Setting<T>.asObservable(): Observable<T> = Observable.create { emitter ->
    val listener: (T) -> Unit = {
        if (!emitter.isDisposed) {
            emitter.onNext(it)
        }
    }

    emitter.setCancellable { removeListener(listener) }

    if (!emitter.isDisposed) {
        addListener(listener)
    }
}
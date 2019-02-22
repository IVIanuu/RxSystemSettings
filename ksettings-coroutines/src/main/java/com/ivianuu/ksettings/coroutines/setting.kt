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

package com.ivianuu.ksettings.coroutines

import com.ivianuu.ksettings.ChangeListener
import com.ivianuu.ksettings.Setting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel

/**
 * Returns a [ReceiveChannel] which emits on changes
 */
@ExperimentalCoroutinesApi
fun <T> Setting<T>.receiveChannel(): ReceiveChannel<T> {
// todo improve this there must be a better way than using a conflated broadcast channel
    val channel = ConflatedBroadcastChannel<T>()
    val listener: ChangeListener<T> = { channel.offer(it) }
    channel.invokeOnClose { removeListener(listener) }
    addListener(listener)
    return channel.openSubscription()
}
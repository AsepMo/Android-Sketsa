/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.phonemetra.turbo.launcher;

import android.app.backup.BackupDataInputStream;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;

public class LauncherPreferencesBackupHelper extends SharedPreferencesBackupHelper {
   
    private final boolean mRestoreEnabled;

    public LauncherPreferencesBackupHelper(Context context,  String sharedPreferencesKey,
            boolean restoreEnabled) {
        super(context, sharedPreferencesKey);
        mRestoreEnabled = restoreEnabled;
    }

    @Override
    public void restoreEntity(BackupDataInputStream data) {
        if (mRestoreEnabled) {
            super.restoreEntity(data);
        }
    }
}

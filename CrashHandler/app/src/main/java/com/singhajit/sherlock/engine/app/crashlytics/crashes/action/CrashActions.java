package com.singhajit.sherlock.engine.app.crashlytics.crashes.action;

import com.singhajit.sherlock.engine.app.crashlytics.core.investigation.CrashViewModel;
import com.singhajit.sherlock.engine.app.crashlytics.crashes.viewmodel.AppInfoViewModel;

public interface CrashActions {
  void openSendApplicationChooser(String crashDetails);

  void renderAppInfo(AppInfoViewModel viewModel);

  void render(CrashViewModel viewModel);
}

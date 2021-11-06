package com.singhajit.sherlock.engine.app.crashlytics.crashes.action;

import com.singhajit.sherlock.engine.app.crashlytics.crashes.viewmodel.CrashesViewModel;

public interface CrashListActions {
  void render(CrashesViewModel viewModel);

  void openCrashDetails(int crashId);
}

package com.singhajit.sherlock.engine.app.crashlytics.crashes.viewmodel;

import com.singhajit.sherlock.engine.app.crashlytics.core.investigation.CrashViewModel;
import com.singhajit.sherlock.engine.app.crashlytics.crashes.util.ViewState;

import java.util.List;

public class CrashesViewModel {
  private final List<CrashViewModel> crashViewModels;

  public CrashesViewModel(List<CrashViewModel> crashViewModels) {
    this.crashViewModels = crashViewModels;
  }

  public List<CrashViewModel> getCrashViewModels() {
    return crashViewModels;
  }

  public ViewState getCrashNotFoundViewState() {
    return new ViewState.Builder().withVisible(crashViewModels.isEmpty()).build();
  }
}

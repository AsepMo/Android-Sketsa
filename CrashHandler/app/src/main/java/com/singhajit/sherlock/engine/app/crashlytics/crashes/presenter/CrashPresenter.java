package com.singhajit.sherlock.engine.app.crashlytics.crashes.presenter;

import com.singhajit.sherlock.engine.app.crashlytics.core.database.SherlockDatabaseHelper;
import com.singhajit.sherlock.engine.app.crashlytics.core.investigation.Crash;
import com.singhajit.sherlock.engine.app.crashlytics.core.investigation.CrashViewModel;
import com.singhajit.sherlock.engine.app.crashlytics.crashes.action.CrashActions;

public class CrashPresenter {
  private final SherlockDatabaseHelper database;
  private final CrashActions actions;

  public CrashPresenter(SherlockDatabaseHelper database, CrashActions actions) {
    this.database = database;
    this.actions = actions;
  }

  public void render(int crashId) {
    Crash crash = database.getCrashById(crashId);
    CrashViewModel crashViewModel = new CrashViewModel(crash);

    actions.render(crashViewModel);
    actions.renderAppInfo(crashViewModel.getAppInfoViewModel());
  }

  public void shareCrashDetails(CrashViewModel viewModel) {
    actions.openSendApplicationChooser(viewModel.getCrashInfo());
  }
}

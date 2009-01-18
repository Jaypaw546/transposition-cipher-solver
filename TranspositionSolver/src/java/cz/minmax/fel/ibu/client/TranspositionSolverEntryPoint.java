package cz.minmax.fel.ibu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import cz.minmax.fel.ibu.client.model.StringMatrix;
import cz.minmax.fel.ibu.client.view.MainView;
import cz.minmax.fel.ibu.client.view.SplashScreen;

/**
 *
 * @author Jan Suchan
 */
public class TranspositionSolverEntryPoint implements EntryPoint
{
  /** Creates a new instance of TranspositionSolverEntryPoint */
  public TranspositionSolverEntryPoint()
  {
  }

  /**
   * The entry point method, called automatically by loading a module
   * that declares an implementing class as an entry-point
   */
  public void onModuleLoad()
  {
    final MainView mainView = new MainView(new StringMatrix());
    RootPanel.get().add(mainView);
    SplashScreen splashScreen = new SplashScreen();
  }
}

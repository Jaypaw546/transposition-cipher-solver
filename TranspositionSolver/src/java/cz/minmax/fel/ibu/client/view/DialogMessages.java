package cz.minmax.fel.ibu.client.view;

/**
 *
 * @author Petr Horsky
 */
public class DialogMessages
{
  public static void universalAlert()
  {
    alert("Warning!");
  }

  public static void alert(String message)
  {
    final AlertBox ab = new AlertBox("Alert", message);
    ab.center();
    ab.show();
  }
}

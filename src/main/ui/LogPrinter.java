package ui;

import model.events.Event;
import model.events.EventLog;
import ui.exception.LogException;

/**
 * Defines behaviours that event log printers must support.
 */
public class LogPrinter {
    /**
     * Prints the log
     *
     * @param el the event log to be printed
     * @throws LogException when printing fails for any reason
     */

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }


    }
}


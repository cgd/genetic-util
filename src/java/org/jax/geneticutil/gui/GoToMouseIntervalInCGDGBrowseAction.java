/*
 * Copyright (c) 2008 The Jackson Laboratory
 *
 * Permission is hereby granted, free of charge, to any person obtaining  a copy
 * of this software and associated documentation files (the  "Software"), to
 * deal in the Software without restriction, including  without limitation the
 * rights to use, copy, modify, merge, publish,  distribute, sublicense, and/or
 * sell copies of the Software, and to  permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be  included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,  EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF  MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY  CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,  TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE  SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.jax.geneticutil.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.jax.geneticutil.data.BasePairInterval;
import org.jax.util.gui.MessageDialogUtilities;

import edu.stanford.ejalbert.BrowserLauncher;

/**
 * @author <A HREF="mailto:keith.sheppard@jax.org">Keith Sheppard</A>
 */
public class GoToMouseIntervalInCGDGBrowseAction extends AbstractAction
{
    /**
     * every {@link java.io.Serializable} is supposed to have one of these
     */
    private static final long serialVersionUID = -1616576877275666748L;
    
    private static final Logger LOG = Logger.getLogger(
            GoToMouseIntervalInCGDGBrowseAction.class.getName());
    
    private static final String ICON_RESOURCE = "/images/internet-16x16.png";
    
    private static final String URL_PREFIX =
        "http://cgd.jax.org/cgi-bin/gbrowse/mouse/?name=";

    private static final String URL_CHR_SEPERATOR = "%3A";

    private static final String URL_POSITION_SEPERATOR = "..";

    private final BasePairInterval interval;

    private final Component parentComponent;

    /**
     * Constructor
     * @param interval
     *          the interval
     * @param parentComponent
     *          the parent component to use if we have to popup a dialog
     */
    public GoToMouseIntervalInCGDGBrowseAction(
            BasePairInterval interval,
            Component parentComponent)
    {
        super("Visit the CGD Browser for: Chr" +
              interval.getChromosomeNumber() + " " +
              interval.getStartInBasePairs() + "-" +
              interval.getEndInBasePairs(),
              new ImageIcon(GoToMouseIntervalInCGDGBrowseAction.class.getResource(
                      ICON_RESOURCE)));
        
        this.interval = interval;
        this.parentComponent = parentComponent;
    }
    
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            BrowserLauncher browserLauncher = new BrowserLauncher();
            browserLauncher.openURLinBrowser(
                    URL_PREFIX +
                    this.chromosomeNumberToString(this.interval.getChromosomeNumber()) +
                    URL_CHR_SEPERATOR +
                    this.interval.getStartInBasePairs() +
                    URL_POSITION_SEPERATOR +
                    this.interval.getEndInBasePairs());
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE,
                    "Failed to launch browser",
                    ex);
            MessageDialogUtilities.error(
                    this.parentComponent,
                    ex.getMessage(),
                    "Failed to Launch Browser");
        }
    }
    
    /**
     * Convert a chromosome number to a chromosome string
     * @param chromosomeNumber
     *          the number
     * @return
     *          the string
     */
    private String chromosomeNumberToString(int chromosomeNumber)
    {
        switch(chromosomeNumber)
        {
            case 20: return "X";
            case 21: return "Y";
            case 22: return "M";
            default: return Integer.toString(chromosomeNumber);
        }
    }
}

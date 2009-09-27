/*
Copyright 2008 WebAtlas
Authors : Mathieu Bastian, Mathieu Jacomy, Julian Bilcke
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.ui.control.visibility;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.logging.Logger;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.Utilities;

/**
 * Top component which displays something.
 */
final class GraphVisibilityTopComponent extends TopComponent {

    private static GraphVisibilityTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "GraphVisibilityTopComponent";

    //Components
    private VisibilityToolbar toolbar;
    private VisibilityDetailsPanel detailsPanel;
    private VisibilityPieChart visibilityPieChart;
    private Component currentModeComponent;

    private GraphVisibilityTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(GraphVisibilityTopComponent.class, "CTL_GraphVisibilityTopComponent"));
        setToolTipText(NbBundle.getMessage(GraphVisibilityTopComponent.class, "HINT_GraphVisibilityTopComponent"));
//        setIcon(Utilities.loadImage(ICON_PATH, true));

        visibilityPieChart = new VisibilityPieChart();
        toolbar = new VisibilityToolbar();
        detailsPanel = new VisibilityDetailsPanel();
        refreshMode();
        toolbar.addPropertyChangeListener("mode", new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                refreshMode();
            }
        });
        add(toolbar, BorderLayout.SOUTH);
    }

    private void refreshMode() {
        if (currentModeComponent != null) {
            remove(currentModeComponent);
        }
        if (toolbar.getVisibilityDisplayMode().equals(VisibilityToolbar.VisibilityDisplayMode.DETAILS)) {
            add(detailsPanel, BorderLayout.CENTER);
            currentModeComponent = detailsPanel;
        } else {
            add(visibilityPieChart.getChartPanel(), BorderLayout.CENTER);
            currentModeComponent = visibilityPieChart.getChartPanel();
        }
        revalidate();
        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized GraphVisibilityTopComponent getDefault() {
        if (instance == null) {
            instance = new GraphVisibilityTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the GraphVisibilityTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized GraphVisibilityTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(GraphVisibilityTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof GraphVisibilityTopComponent) {
            return (GraphVisibilityTopComponent) win;
        }
        Logger.getLogger(GraphVisibilityTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID +
                "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    /** replaces this in object stream */
    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    final static class ResolvableHelper implements Serializable {

        private static final long serialVersionUID = 1L;

        public Object readResolve() {
            return GraphVisibilityTopComponent.getDefault();
        }
    }
}

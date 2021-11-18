package Vista;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.editor.ChartEditorFactory;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYIntervalSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
/**
 *
 * @author dosor
 */
public class Grafica2 extends javax.swing.JInternalFrame {
    private double x[], y[], coef[];
    private int asig[];
    public Grafica2() {
        
        try {
            initComponents();
            String ruta="TEMPERATURA_VELOCIDAD.arff";
            Instances dataset=new Instances(new BufferedReader(new FileReader(ruta)));
            //dataset.setClassIndex(1); porque es dato no supervisado
            
            x=new double [dataset.numInstances()];
            y=new double [dataset.numInstances()];
            
            for (int i = 0; i < dataset.numInstances(); i++) {
                Instance ins=dataset.instance(i);
                x[i]=ins.value(0);
                y[i]=ins.value(1);
            }
            
            SimpleKMeans skm=new SimpleKMeans();
            skm.setNumClusters(3);
            skm.setPreserveInstancesOrder(true);
            skm.buildClusterer(dataset);
            
            System.out.println(""+skm);
            
            asig= skm.getAssignments();
            System.out.println("\nAsignaciones: "+Arrays.toString(asig));
            
            GraficaDispersion gd=new GraficaDispersion(x, y, asig);
            
            /*Instances centroides = skm.getClusterCentroids();
            for (int i=0; i<centroides.numInstances(); i++){
                
                Instance ins=centroides.instance(i);
                
                jTextArea1.setText("x: " + ins.toString(0) + ", Y: " +ins.toString(1));
            }*/
        
        ChartPanel  cp=gd.generarGrafica();
        jPanel2.removeAll();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(cp, BorderLayout.CENTER);
        jPanel2.validate();
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Grafica2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Grafica2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Grafica2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();

        setClosable(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
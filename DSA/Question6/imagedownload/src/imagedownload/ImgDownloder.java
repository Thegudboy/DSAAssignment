package imagedownload;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;
import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ImgDownloder extends javax.swing.JFrame {

    private boolean isPaused = false;
    private boolean isCancelled = false;

    public ImgDownloder() {
        initComponents();
        jTable1.getColumnModel().getColumn(2).setCellRenderer(new ProgressBarRenderer());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        URLS = new javax.swing.JTextField();
        btn_downlaod = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_resume = new javax.swing.JButton();
        btn_pause = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Picpicker");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        URLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                URLSActionPerformed(evt);
            }
        });
        jPanel1.add(URLS, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 380, 30));

        btn_downlaod.setBackground(new java.awt.Color(51, 255, 51));
        btn_downlaod.setText("DOWNLOAD");
        btn_downlaod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_downlaod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_downlaodActionPerformed(evt);
            }
        });
        jPanel1.add(btn_downlaod, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 100, 30));

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DOWNLOAD IMAGES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 615, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        jLabel1.setText("ENTER URLS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 84, -1));

        btn_resume.setText("RESUME");
        btn_resume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resumeActionPerformed(evt);
            }
        });
        jPanel1.add(btn_resume, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 95, 30));

        btn_pause.setBackground(new java.awt.Color(255, 153, 51));
        btn_pause.setText("PAUSE");
        btn_pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pauseActionPerformed(evt);
            }
        });
        jPanel1.add(btn_pause, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 97, 30));

        btn_cancel.setBackground(new java.awt.Color(255, 0, 0));
        btn_cancel.setText("CANCEL");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 96, 30));

        btn_delete.setText("CLEAR");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 93, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "urls", "size", "progess", "percentage"
            }
        ));
        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 610, 235));
        jPanel1.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, -1, 19));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public class ProgressBarRenderer extends JProgressBar implements TableCellRenderer {

        public ProgressBarRenderer() {
            super(JProgressBar.HORIZONTAL);
            setStringPainted(true);
            setForeground(Color.green);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Integer) {
                setValue((Integer) value);
            } else {
                setValue(0); 
            }

            return this;
        }
    }
    private void btn_downlaodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_downlaodActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = chooser.getSelectedFile();
            String directoryPath = selectedDirectory.getAbsolutePath();
            String url = URLS.getText();
            try {
                List<String> imageLinks = extractImageLinks(url);
                System.out.println("Found Image URLs:");

//                String directory = "C:\\Users\\LEGION\\Desktop\\New folder";

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                for (String link : imageLinks) {
                    String fileName = link.substring(link.lastIndexOf("/") + 1);
                    File file = new File(directoryPath, fileName);

                    model.addRow(new Object[]{link, "0 KB", "", "0%"});

                    downloadImage(link, file, model, jTable1.getRowCount() - 1); // Pass row index
                }
                
                
                    
            }
            catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_downlaodActionPerformed

    private void btn_pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pauseActionPerformed
        isPaused = true;
    }//GEN-LAST:event_btn_pauseActionPerformed

    private void btn_resumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resumeActionPerformed
        isPaused = false; 
    }//GEN-LAST:event_btn_resumeActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        isCancelled = true; 
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        clearDownloads((DefaultTableModel) jTable1.getModel());
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void URLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_URLSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_URLSActionPerformed

    private void clearDownloads(DefaultTableModel model) {
        model.setRowCount(0);
    }

    private void downloadImage(String link, File outputFile, DefaultTableModel model, int rowIndex) {
        try (InputStream in = new URL(link).openStream()) {
            String fileName = link.substring(link.lastIndexOf("/") + 1);
            fileName = fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
            fileName = fileName.replaceFirst("[.][^.]+$", ".png");

            outputFile = new File(outputFile.getParent(), fileName);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));

            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesRead = 0;
            long fileSize = outputFile.length(); // Store the file size for progress calculation

            while ((bytesRead = in.read(buffer)) != -1) {
                if (isCancelled) {
                    out.close();
                    return;
                }

                if (isPaused) {

                    Thread.sleep(10000);
                    continue;
                }
                out.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                double percent = (double) totalBytesRead / outputFile.length() * 100;
                model.setValueAt(totalBytesRead + " KB", rowIndex, 1);
                model.setValueAt((int) percent, rowIndex, 2); // Update progress value
                model.setValueAt(String.format("%.2f", percent) + "%", rowIndex, 3);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error downloading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error pausing download: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<String> extractImageLinks(String url) throws IOException {
        List<String> imageLinks = new ArrayList<>();
        Document document = Jsoup.connect(url).get();
        Elements images = document.select("img");
        for (Element image : images) {
            String imageUrl = image.absUrl("src");
            if (!imageUrl.isEmpty()) {
                imageLinks.add(imageUrl);
            }

        }

        return imageLinks;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImgDownloder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField URLS;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_downlaod;
    private javax.swing.JButton btn_pause;
    private javax.swing.JButton btn_resume;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

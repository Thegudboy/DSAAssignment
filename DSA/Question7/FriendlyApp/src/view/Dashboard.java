
package view;

import controller.DashController;
import controller.LoginController;
import controller.Posts;
import controller.SocialGraph;
import controller.TreeNode;
import controller.postController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author subad
 */
public class Dashboard extends javax.swing.JFrame {

    public static String name = LoginController.getCurrentUser();
    boolean liked = false;

    public void createLabels() {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());

        SocialGraph sg = new SocialGraph();
        List<Posts> posts = sg.getAllPosts();

        List<JLabel> labels = createPostLabels(posts);

        for (JLabel label : labels) {
            postPanel.add(label);
            postPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between labels
        }

        JScrollPane scrollPane = new JScrollPane(postPanel);
        add(scrollPane, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    private void refresh() {
        Dashboard d = new Dashboard(name);
        d.setVisible(true);
        d.setLocationRelativeTo(null);
        this.dispose();
    }

    public List<JLabel> createPostLabels(List<Posts> posts) {
        List<JLabel> postLabels = new ArrayList<>();

        for (Posts post : posts) {
            String labelText = "Username: " + post.getUsername() + "\nDescription: " + post.getDescription();
            JLabel label = new JLabel(labelText);
            postLabels.add(label);
        }

        return postLabels;
    }

    private void createTextAreas(List<Posts> posts) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(204, 255, 255));

        postController pc = new postController();

        for (Posts post : posts) {
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            String labelText = post.getUsername() + " posted\n" + post.getDescription() + "\n\nLikes: " + pc.getLikesCount(post.getId());
            textArea.setText(labelText);
            textArea.setBackground(new Color(204, 255, 255));

            JButton likeButton;

            if (!liked) {
                likeButton = new JButton("Like");
                likeButton.setBackground(new Color(102, 0, 204));
            } else {
                likeButton = new JButton("Unlike");
                likeButton.setBackground(Color.RED);
            }

            likeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle the like button click event here
                    // You can implement the logic to handle liking or unliking a post
                    // For example, update the database, increment or decrement like count, etc.
                    System.out.println(liked);
                    if (!liked) {
                        pc.incrementLikes(post.getId());
                        likeButton.setText("Unlike");
                        likeButton.setBackground(Color.RED);
                        liked = true;
                        System.out.println("Liked post by: " + post.getUsername());
                    } else {
                        pc.decrementLikes(post.getId());
                        likeButton.setText("Like");
                        likeButton.setBackground(new Color(102, 0, 204));
                        liked = false;
                        System.out.println("Unliked post by: " + post.getUsername());
                    }
                }
            });

            panel.add(textArea);
            panel.add(likeButton);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
        }

        postArea.setViewportView(panel);
    }

    public void createFriendLabels(List<String> usernames) {
        for (String username : usernames) {
            JLabel friendLabel = new JLabel("Friend: " + username);
            friendArea.append(username + "\n");
        }
    }

    private void createFriendList(List<String> friends) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(102, 0, 204));

        postController pc = new postController();

        for (String friend : friends) {
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            String labelText = friend;
            textArea.setText(labelText);
            textArea.setBackground(new Color(204, 255, 255));

            JButton unfollow;
            unfollow = new JButton("Unfollow");
            unfollow.setBackground(Color.RED);

            unfollow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle the like button click event here
                    // You can implement the logic to handle liking or unliking a post
                    // For example, update the database, increment or decrement like count, etc.

                    SocialGraph sg = new SocialGraph();
                    sg.detachConnection(name, friend);
                    refresh();

                }
            });

            panel.add(textArea);
            panel.add(unfollow);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
        }

        friendScrollPane.setViewportView(panel);
    }

    private void createRecomendationList(List<String> friends) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(204, 255, 255));

        postController pc = new postController();

        for (String friend : friends) {
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            String labelText = friend;
            textArea.setText(labelText);
            textArea.setBackground(new Color(102, 0, 204));

            JButton unfollow;
            unfollow = new JButton("Follow");
            unfollow.setBackground(Color.GREEN);

            unfollow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle the like button click event here
                    // You can implement the logic to handle liking or unliking a post
                    // For example, update the database, increment or decrement like count, etc.

                    SocialGraph sg = new SocialGraph();
                    sg.establishConnection(name, friend);
                    
                    refresh();

                }
            });

            panel.add(textArea);
            panel.add(unfollow);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
        }

        recomendScrollPane.setViewportView(panel);
    }

    /**
     * Creates new form Dashbaoard
     */
    public Dashboard(String user) {
        initComponents();

        String currentUser = user;
        userName.setText(currentUser);
        SocialGraph sg = new SocialGraph();
        List<Posts> posts = sg.getPostsFromFollowingUsers();
        createTextAreas(posts);

//        System.out.println(sg.getPostsFromFollowingUsers());
        List<JLabel> labels = createPostLabels(sg.getAllPosts());

        TreeNode tn = new TreeNode(user);
        System.out.println("followers = " + tn.followers);
        System.out.println("following = " + tn.following);
        System.out.println("mutual = " + tn.mutual);
        System.out.println("recomendation = " + tn.getRecommendation(currentUser));

        createFriendList(tn.mutual);

        createRecomendationList(tn.getRecommendation(currentUser));

        for (Posts pst : sg.getAllPosts()) {
            System.out.println(pst.username);
            System.out.println(pst.discription);

            String disc = pst.username + "\n" + pst.discription;

            System.out.println("");
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

        jLabel4 = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        recomendScrollPane = new javax.swing.JScrollPane();
        recomendArea = new javax.swing.JTextArea();
        friendScrollPane = new javax.swing.JScrollPane();
        friendArea = new javax.swing.JTextArea();
        searchLabel = new javax.swing.JTextField();
        postArea = new javax.swing.JScrollPane();
        newPost = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText(" Post Something");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 110, -1));
        getContentPane().add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 80, 20));

        jButton1.setBackground(new java.awt.Color(190, 240, 240));
        jButton1.setText("What's on your mind??");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 440, -1));

        recomendArea.setBackground(new java.awt.Color(204, 255, 255));
        recomendArea.setColumns(20);
        recomendArea.setRows(5);
        recomendScrollPane.setViewportView(recomendArea);

        getContentPane().add(recomendScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 170, 390));

        friendArea.setBackground(new java.awt.Color(204, 255, 255));
        friendArea.setColumns(20);
        friendArea.setRows(5);
        friendArea.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                friendAreaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        friendScrollPane.setViewportView(friendArea);

        getContentPane().add(friendScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 160, 390));

        searchLabel.setBackground(new java.awt.Color(204, 255, 255));
        searchLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchLabelActionPerformed(evt);
            }
        });
        getContentPane().add(searchLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 430, 30));

        postArea.setOpaque(false);

        newPost.setColumns(20);
        newPost.setLineWrap(true);
        newPost.setWrapStyleWord(true);
        newPost.setRows(5);
        postArea.setViewportView(newPost);

        getContentPane().add(postArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 470, 440));

        jLabel10.setText("Friend List!");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        jLabel9.setText("People you may know!");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 140, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ImageSearchButton.png"))); // NOI18N
        jLabel7.setToolTipText("");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 40, 30));

        jLabel6.setText("Search User");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 80, 20));

        jLabel5.setText("Hello!");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, -1));

        jLabel1.setBackground(new java.awt.Color(153, 255, 204));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 216, 562));

        jLabel2.setBackground(new java.awt.Color(153, 255, 204));
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 169, 562));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 0, 720, 562));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Post p = new Post();
        p.setVisible(true);
        p.setLocationRelativeTo(null);

//        this.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchLabelActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        DashController dc = new DashController();
        boolean found = dc.findFriend(searchLabel.getText());
        System.out.println(found);

        if (found) {
            // Show a confirmation dialog
            int option = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to follow " + searchLabel.getText() + "?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // User clicked "Yes", add friend logic here
                System.out.println("following!");
                SocialGraph sg = new SocialGraph();
                sg.establishConnection(name, searchLabel.getText());
            } else {
                // User clicked "No" or closed the dialog
                System.out.println("not following");
            }
        } else {
            // Show a warning dialog for user not found
            JOptionPane.showMessageDialog(this, "User does not exist!", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:

        Dashboard d = new Dashboard(name);
        d.setVisible(true);
        d.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void friendAreaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_friendAreaAncestorAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_friendAreaAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dashboard db = new Dashboard(name);
                db.setVisible(true);
                db.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea friendArea;
    private javax.swing.JScrollPane friendScrollPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextArea newPost;
    private javax.swing.JScrollPane postArea;
    private javax.swing.JTextArea recomendArea;
    private javax.swing.JScrollPane recomendScrollPane;
    private javax.swing.JTextField searchLabel;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
}

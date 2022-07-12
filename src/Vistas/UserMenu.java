package Vistas;

import Modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class UserMenu extends javax.swing.JFrame {

  Conexion conexion = new Conexion();
  Connection connection;
  Statement st; //ejecucion de los query
  ResultSet rs;//trae los resultados de conexion cuando lance los query
  DefaultTableModel contenidoTablaEmpleados;

  public UserMenu() {
    initComponents();
    this.setLocationRelativeTo(this);
    listarEmpleados();

  }

  //metodo de visibilidad privada
  public void listarEmpleados() {
    String filtroBusqueda = txtSearch.getText();
    //si no hay nada en el campo de busqueda se cargaran todos los empleados
    if (filtroBusqueda.isEmpty()) {
      String query = "SELECT * FROM empleados";//query de consulta
      //establecer conexion con la tabla y retorna respuesta
      try {
        //establecer conexion
        connection = conexion.getConnection();
        //creamos la consulta query para la base de datos
        st = connection.createStatement();
        //la ejecutamos
        rs = st.executeQuery(query);//esperamos que nos devuelva 4 empleados
        //asignar en un objeto los datos que devuelve de cada registro
        Object[] empleado = new Object[6];
        contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
        // el resultado de la consulta del query  nos determinara la cantidad de empleados que existen
        //vamos a iterar sobre la cantidad de empleados que hay en la consulta mediante while
        while (rs.next()) { //cuando ya no hay next es que ya no hay empleados
          empleado[0] = rs.getInt("idEmp");
          empleado[1] = rs.getString("nombreEmp");
          empleado[2] = rs.getString("apellidos");
          empleado[3] = rs.getString("tipoDocumento");
          empleado[4] = rs.getString("documento");
          empleado[5] = rs.getString("correo");
          //actualizr el contenido de la tabla 
          //en la tabla creamos una nueva fila con los 5 atributos del objeto empleado
          contenidoTablaEmpleados.addRow(empleado);
          tblEmpleados.setModel(contenidoTablaEmpleados);
          System.out.println("idEmp: " + empleado[0] + ", nombre: " + empleado[1] + " "
            + ", documento: " + empleado[2] + " " + empleado[3] + "correo " + empleado[4]);

        }

      } catch (SQLException e) { //excepcion de MySQL con la letra e
        System.out.println("No se pudo cargar la informacion de los empleados");

      }

    } else {
      String query = "SELECT * FROM empleados WHERE nombreEmp like '%" + filtroBusqueda + "%' OR apellidos like '%" + filtroBusqueda + "%';";
      try {
        //establecer conexion
        connection = conexion.getConnection();
        //creamos la consulta query para la base de datos
        st = connection.createStatement();
        //la ejecutamos
        rs = st.executeQuery(query);//esperamos que nos devuelva 4 empleados
        //asignar en un objeto los datos que devuelve de cada registro
        Object[] empleado = new Object[6];
        contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
        // el resultado de la consulta del query  nos determinara la cantidad de empleados que existen
        //vamos a iterar sobre la cantidad de empleados que hay en la consulta mediante while
        while (rs.next()) { //cuando ya no hay next es que ya no hay empleados
          empleado[0] = rs.getInt("idEmp");
          empleado[1] = rs.getString("nombreEmp");
          empleado[2] = rs.getString("apellidos");
          empleado[3] = rs.getString("tipoDocumento");
          empleado[4] = rs.getString("documento");
          empleado[5] = rs.getString("correo");
          //actualizr el contenido de la tabla 
          //en la tabla creamos una nueva fila con los 5 atributos del objeto empleado
          contenidoTablaEmpleados.addRow(empleado);
          tblEmpleados.setModel(contenidoTablaEmpleados);
          System.out.println("idEmp: " + empleado[0] + ", nombre: " + empleado[1] + " "
            + ", documento: " + empleado[2] + " " + empleado[3] + "correo " + empleado[4]);

        }

      } catch (SQLException e) { //excepcion de MySQL con la letra e
        System.out.println("No se pudo cargar la informacion de los empleados");

      }

    }

  }

  public void borrarRegistrosTabla() {
    //getRowCount() devuelve la cantidad de filas que tiene la tabla 
    for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
      contenidoTablaEmpleados.removeRow(i);
      // cada vez que se elimina una fila debe quedar menos filas por eliminar
      i = i - 1;

    }

  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel1 = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jPanel3 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tblEmpleados = new javax.swing.JTable();
    btnAddUser = new javax.swing.JButton();
    btnShow = new javax.swing.JButton();
    btnEdit = new javax.swing.JButton();
    btnRemove = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    txtSearch = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    btnSearch = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanel2.setBackground(new java.awt.Color(204, 204, 204));

    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

    tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "IdEmp", "Nombre", "Apellidos", "Tipo documento", "Documento", "Correo"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        tblEmpleadosMouseClicked(evt);
      }
    });
    jScrollPane1.setViewportView(tblEmpleados);

    btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/avatar.png"))); // NOI18N
    btnAddUser.setText("Añadir");
    btnAddUser.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnAddUserActionPerformed(evt);
      }
    });

    btnShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/showUser.png"))); // NOI18N
    btnShow.setText("Consultar");
    btnShow.setPreferredSize(new java.awt.Dimension(38, 38));
    btnShow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnShowActionPerformed(evt);
      }
    });

    btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/editUser.png"))); // NOI18N
    btnEdit.setText("Editar");
    btnEdit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnEditActionPerformed(evt);
      }
    });

    btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/deleteUser.png"))); // NOI18N
    btnRemove.setText("Eliminar");
    btnRemove.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnRemoveActionPerformed(evt);
      }
    });

    jLabel2.setText("EMPLEADOS");

    jLabel3.setText("MisionTIC 2022");

    txtSearch.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        txtSearchActionPerformed(evt);
      }
    });

    jLabel4.setText("Nombre");

    btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/showUser.png"))); // NOI18N
    btnSearch.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnSearchActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(43, 43, 43)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))))
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel3)
              .addComponent(jLabel2))
            .addGap(108, 108, 108)
            .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(152, 152, 152)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnSearch)))
        .addContainerGap(47, Short.MAX_VALUE))
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1))
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(btnAddUser)
              .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4)
          .addComponent(btnSearch))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnEdit)
          .addComponent(btnRemove))
        .addGap(25, 25, 25))
    );

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Empleados", jPanel1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jTabbedPane1)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jTabbedPane1)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
    // TODO add your handling code here:
    //llamar la instancia de AddUserForm
    AddUserForm addUserForm = new AddUserForm(this, true);
    //hacerlo visible
    addUserForm.setVisible(true);
    //actualizar la informacion de la tabla
    borrarRegistrosTabla();
    listarEmpleados();


  }//GEN-LAST:event_btnAddUserActionPerformed

  private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_btnRemoveActionPerformed

  private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_txtSearchActionPerformed

  private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
    // TODO add your handling code here:
    borrarRegistrosTabla();
    listarEmpleados();
  }//GEN-LAST:event_btnSearchActionPerformed

  private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
    
     
    // TODO add your handling code here:
    //capturar la informacion sobre la cual de click 
    int filaSeleccionada = tblEmpleados.getSelectedRow(); //captura la informacion de esa fila
    System.out.println("Fila seleccionada " + filaSeleccionada);
    //Se convierte el texto que se captura de la tabla en entero
    int idEmp = Integer.parseInt(tblEmpleados.getValueAt(filaSeleccionada, 0).toString());//capturar desde nuestra tabla
    String nombreEmp = tblEmpleados.getValueAt(filaSeleccionada, 1).toString();
    String apellidos = tblEmpleados.getValueAt(filaSeleccionada, 2).toString();
    String tipoDocumento = tblEmpleados.getValueAt(filaSeleccionada, 3).toString();
    String documento = tblEmpleados.getValueAt(filaSeleccionada, 4).toString();
    String correo = tblEmpleados.getValueAt(filaSeleccionada, 5).toString();
    
    //validamos que si esten capturando los valores de forma correcta
    String empleadoSeleccionado = "{\n  idEmp: " + idEmp + ",\n  nombre: " + nombreEmp + " "
      + apellidos + ",\n  documento: " + tipoDocumento + " " + documento + ",\n  correo: "
      + correo + "\n}";

    System.out.println(empleadoSeleccionado);
    ShowUserForm showUserForm = new ShowUserForm(this, true);
    showUserForm.recibirDatosDeUserMenu(idEmp, nombreEmp, apellidos, tipoDocumento, documento, correo);
    showUserForm.setVisible(true);
    this.borrarRegistrosTabla();
    this.listarEmpleados();  

  }//GEN-LAST:event_tblEmpleadosMouseClicked

  private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
  
  }//GEN-LAST:event_btnShowActionPerformed

  private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_btnEditActionPerformed

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
      java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new UserMenu().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnAddUser;
  private javax.swing.JButton btnEdit;
  private javax.swing.JButton btnRemove;
  private javax.swing.JButton btnSearch;
  private javax.swing.JButton btnShow;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JTable tblEmpleados;
  private javax.swing.JTextField txtSearch;
  // End of variables declaration//GEN-END:variables
}

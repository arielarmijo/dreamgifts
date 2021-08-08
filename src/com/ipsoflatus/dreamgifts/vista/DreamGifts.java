package com.ipsoflatus.dreamgifts.vista;

import com.ipsoflatus.dreamgifts.vista.compras.ArmadoPackView;
import com.ipsoflatus.dreamgifts.vista.admin.ArticuloView;
import com.ipsoflatus.dreamgifts.vista.admin.BancoView;
import com.ipsoflatus.dreamgifts.vista.admin.CategoriaArticuloView;
import com.ipsoflatus.dreamgifts.vista.admin.ClienteView;
import com.ipsoflatus.dreamgifts.vista.admin.ComunaView;
import com.ipsoflatus.dreamgifts.vista.admin.EstadoVentaView;
import com.ipsoflatus.dreamgifts.vista.admin.PackView;
import com.ipsoflatus.dreamgifts.vista.admin.ProveedorView;
import com.ipsoflatus.dreamgifts.vista.admin.RRSSView;
import com.ipsoflatus.dreamgifts.vista.admin.UsuarioView;
import com.ipsoflatus.dreamgifts.vista.compras.RegistroCompraView;
import com.ipsoflatus.dreamgifts.vista.compras.RevisionFacturaView;
import com.ipsoflatus.dreamgifts.vista.compras.SolicitudPedidoView;
import com.ipsoflatus.dreamgifts.vista.informes.InformeClientesView;
import com.ipsoflatus.dreamgifts.vista.informes.InformeCyDView;
import com.ipsoflatus.dreamgifts.vista.informes.InformeInventarioView;
import com.ipsoflatus.dreamgifts.vista.informes.InformeVentasView;
import com.ipsoflatus.dreamgifts.vista.ventas.ActualizacionDespachoView;
import com.ipsoflatus.dreamgifts.vista.ventas.ConfirmacionPagoView;
import com.ipsoflatus.dreamgifts.vista.ventas.ListaDestinosView;
import com.ipsoflatus.dreamgifts.vista.ventas.VentaView;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public class DreamGifts extends javax.swing.JFrame {
    
    public JPanel venta;
    public JPanel confirmacionPago;
    public JPanel destinos;
    public JPanel despachos;
    
    public JPanel armadoPacks;
    public JPanel solicitudPedido;
    public JPanel registroCompras;
    public RevisionFacturaView revisionFactura;
    
    public JPanel informeVentas;
    public JPanel informeInventario;
    public JPanel informeClientes;
    public JPanel informeDyC;
    
    public ClienteView cliente;
    public JPanel proveedor;
    public JPanel pack;
    public JPanel articulo;
    public JPanel categoriaArticulo;
    public JPanel comuna;
    public JPanel banco;
    public JPanel rrss;
    public JPanel estadoVenta;
    public JPanel usuario;
    
    public DreamGifts() {
        initComponents();
        crearPanelVentas();
        crearPanelCompras();
        crearPanelInformes();
        crearPanelAdmin();
        //showTabbedPaneAndTab(jTabbedPaneInformes, "informes", 1);
    }
    
    private void crearPanelVentas() {
        venta = new VentaView();
        confirmacionPago = new ConfirmacionPagoView();
        destinos = new ListaDestinosView();
        despachos = new ActualizacionDespachoView();
        jTabbedPaneVentas.addTab("Venta", venta);
        jTabbedPaneVentas.addTab("Confirmación Pago", confirmacionPago);
        jTabbedPaneVentas.addTab("Lista Destinos", destinos);
        jTabbedPaneVentas.addTab("Actualización Despachos", despachos);
    }
    
    private void crearPanelCompras() {
        armadoPacks = new ArmadoPackView();
        solicitudPedido = new SolicitudPedidoView();
        registroCompras = new RegistroCompraView();
        revisionFactura = new RevisionFacturaView();
        jTabbedPaneCompras.addTab("Armado Packs",armadoPacks);
        jTabbedPaneCompras.addTab("Solicitud Pedido", solicitudPedido);
        jTabbedPaneCompras.addTab("Registro Compra", registroCompras);
        jTabbedPaneCompras.addTab("Revisión Facturas", revisionFactura);
        revisionFactura.setRoot(this);
    }
    
    private void crearPanelInformes() {
        informeVentas = new InformeVentasView();
        informeInventario = new InformeInventarioView();
        informeClientes = new InformeClientesView();
        informeDyC = new InformeCyDView();
        jTabbedPaneInformes.addTab("Informe Ventas", informeVentas);
        jTabbedPaneInformes.addTab("Informe Inventario", informeInventario);
        jTabbedPaneInformes.addTab("Informe Clientes", informeClientes);
        jTabbedPaneInformes.addTab("Informe Devoluciones y Cambios", informeDyC);
    }
        
    private void crearPanelAdmin() {
        cliente = new ClienteView();
        proveedor = new ProveedorView();
        pack = new PackView();
        articulo = new ArticuloView();
        categoriaArticulo = new CategoriaArticuloView();
        comuna = new ComunaView();
        banco = new BancoView();
        rrss = new RRSSView();
        estadoVenta = new EstadoVentaView();
        usuario = new UsuarioView();
        jTabbedPaneAdmin.addTab("Clientes", cliente);
        jTabbedPaneAdmin.addTab("Proveedores", proveedor);
        jTabbedPaneAdmin.addTab("Packs", pack);
        jTabbedPaneAdmin.addTab("Articulos", articulo);
        jTabbedPaneAdmin.addTab("Categoría Artículos", categoriaArticulo);
        jTabbedPaneAdmin.addTab("Comunas", comuna);
        jTabbedPaneAdmin.addTab("Bancos", banco);
        jTabbedPaneAdmin.addTab("RRSS", rrss);
        jTabbedPaneAdmin.addTab("Estados Venta", estadoVenta);
        jTabbedPaneAdmin.addTab("Usuarios", usuario);
        cliente.setRoot(this);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTienda = new javax.swing.JLabel();
        mainContainer = new javax.swing.JPanel();
        jTabbedPaneVentas = new javax.swing.JTabbedPane();
        jTabbedPaneCompras = new javax.swing.JTabbedPane();
        jTabbedPaneInformes = new javax.swing.JTabbedPane();
        jTabbedPaneAdmin = new javax.swing.JTabbedPane();
        jPanelEstado = new javax.swing.JPanel();
        jLabelEstado = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuVentas = new javax.swing.JMenu();
        jMenuItemVenta = new javax.swing.JMenuItem();
        jMenuItemConfirmacionPago = new javax.swing.JMenuItem();
        jMenuItemDespachos = new javax.swing.JMenuItem();
        jMenuItemEstadoDespachos = new javax.swing.JMenuItem();
        jMenuCompras = new javax.swing.JMenu();
        jMenuItemArmadoPacks = new javax.swing.JMenuItem();
        jMenuItemSolicitudPedido = new javax.swing.JMenuItem();
        jMenuItemRegistroCompra = new javax.swing.JMenuItem();
        jMenuItemFactura = new javax.swing.JMenuItem();
        jMenuInformes = new javax.swing.JMenu();
        jMenuItemInformeVentas = new javax.swing.JMenuItem();
        jMenuItemInformeInventario = new javax.swing.JMenuItem();
        jMenuItemInformeClientes = new javax.swing.JMenuItem();
        jMenuItemInformeDyC = new javax.swing.JMenuItem();
        jMenuadmin = new javax.swing.JMenu();
        jMenuItemClientes = new javax.swing.JMenuItem();
        jMenuItemProveedores = new javax.swing.JMenuItem();
        jMenuItemPacks = new javax.swing.JMenuItem();
        jMenuItemArticulos = new javax.swing.JMenuItem();
        jMenuItemCategoriaArticulos = new javax.swing.JMenuItem();
        jMenuItemComunas = new javax.swing.JMenuItem();
        jMenuItemBancos = new javax.swing.JMenuItem();
        jMenuItemRRSS = new javax.swing.JMenuItem();
        jMenuItemCategoriaVentas = new javax.swing.JMenuItem();
        jMenuItemUsuarios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dream Gifts");
        setMinimumSize(new java.awt.Dimension(890, 680));
        setName("DreamGifts"); // NOI18N
        setPreferredSize(new java.awt.Dimension(890, 680));
        setSize(new java.awt.Dimension(890, 680));

        jLabelTienda.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabelTienda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTienda.setText("Dream Gifts");

        mainContainer.setMinimumSize(new java.awt.Dimension(850, 500));
        mainContainer.setPreferredSize(new java.awt.Dimension(850, 500));
        mainContainer.setLayout(new java.awt.CardLayout());

        jTabbedPaneVentas.setMinimumSize(new java.awt.Dimension(850, 500));
        jTabbedPaneVentas.setPreferredSize(new java.awt.Dimension(850, 500));
        jTabbedPaneVentas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneVentasStateChanged(evt);
            }
        });
        mainContainer.add(jTabbedPaneVentas, "ventas");

        jTabbedPaneCompras.setMinimumSize(new java.awt.Dimension(850, 500));
        jTabbedPaneCompras.setPreferredSize(new java.awt.Dimension(850, 500));
        jTabbedPaneCompras.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneComprasStateChanged(evt);
            }
        });
        mainContainer.add(jTabbedPaneCompras, "compras");

        jTabbedPaneInformes.setMinimumSize(new java.awt.Dimension(850, 500));
        jTabbedPaneInformes.setPreferredSize(new java.awt.Dimension(850, 500));
        jTabbedPaneInformes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneInformesStateChanged(evt);
            }
        });
        mainContainer.add(jTabbedPaneInformes, "informes");

        jTabbedPaneAdmin.setMinimumSize(new java.awt.Dimension(850, 500));
        jTabbedPaneAdmin.setPreferredSize(new java.awt.Dimension(850, 500));
        jTabbedPaneAdmin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneAdminStateChanged(evt);
            }
        });
        mainContainer.add(jTabbedPaneAdmin, "admin");

        jPanelEstado.setMinimumSize(new java.awt.Dimension(57, 25));

        jLabelEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEstado.setText("Estado");
        jLabelEstado.setName(""); // NOI18N

        javax.swing.GroupLayout jPanelEstadoLayout = new javax.swing.GroupLayout(jPanelEstado);
        jPanelEstado.setLayout(jPanelEstadoLayout);
        jPanelEstadoLayout.setHorizontalGroup(
            jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEstadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelEstadoLayout.setVerticalGroup(
            jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEstadoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabelEstado)
                .addGap(5, 5, 5))
        );

        jMenuVentas.setText("Ventas");

        jMenuItemVenta.setText("Venta");
        jMenuItemVenta.setInheritsPopupMenu(true);
        jMenuItemVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVentaActionPerformed(evt);
            }
        });
        jMenuVentas.add(jMenuItemVenta);

        jMenuItemConfirmacionPago.setText("Confirmación Pago");
        jMenuItemConfirmacionPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConfirmacionPagoActionPerformed(evt);
            }
        });
        jMenuVentas.add(jMenuItemConfirmacionPago);

        jMenuItemDespachos.setText("Lista Despachos");
        jMenuItemDespachos.setToolTipText("");
        jMenuItemDespachos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDespachosActionPerformed(evt);
            }
        });
        jMenuVentas.add(jMenuItemDespachos);

        jMenuItemEstadoDespachos.setText("Estado Despachos");
        jMenuItemEstadoDespachos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEstadoDespachosActionPerformed(evt);
            }
        });
        jMenuVentas.add(jMenuItemEstadoDespachos);

        jMenuBar.add(jMenuVentas);

        jMenuCompras.setText("Compras");

        jMenuItemArmadoPacks.setText("Armado Packs");
        jMenuItemArmadoPacks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemArmadoPacksActionPerformed(evt);
            }
        });
        jMenuCompras.add(jMenuItemArmadoPacks);

        jMenuItemSolicitudPedido.setText("Solicitud Pedido");
        jMenuItemSolicitudPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSolicitudPedidoActionPerformed(evt);
            }
        });
        jMenuCompras.add(jMenuItemSolicitudPedido);

        jMenuItemRegistroCompra.setText("Registro Compra");
        jMenuItemRegistroCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegistroCompraActionPerformed(evt);
            }
        });
        jMenuCompras.add(jMenuItemRegistroCompra);

        jMenuItemFactura.setText("Revisión Factura");
        jMenuItemFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFacturaActionPerformed(evt);
            }
        });
        jMenuCompras.add(jMenuItemFactura);

        jMenuBar.add(jMenuCompras);

        jMenuInformes.setText("Informes");

        jMenuItemInformeVentas.setText("Informe Ventas");
        jMenuItemInformeVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInformeVentasActionPerformed(evt);
            }
        });
        jMenuInformes.add(jMenuItemInformeVentas);

        jMenuItemInformeInventario.setText("Informe Inventario");
        jMenuItemInformeInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInformeInventarioActionPerformed(evt);
            }
        });
        jMenuInformes.add(jMenuItemInformeInventario);

        jMenuItemInformeClientes.setText("Informe Clientes");
        jMenuItemInformeClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInformeClientesActionPerformed(evt);
            }
        });
        jMenuInformes.add(jMenuItemInformeClientes);

        jMenuItemInformeDyC.setText("Informe Cambio y Devoluciones");
        jMenuItemInformeDyC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInformeDyCActionPerformed(evt);
            }
        });
        jMenuInformes.add(jMenuItemInformeDyC);

        jMenuBar.add(jMenuInformes);

        jMenuadmin.setText("Administración");

        jMenuItemClientes.setText("Clientes");
        jMenuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemClientes);

        jMenuItemProveedores.setText("Proveedores");
        jMenuItemProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProveedoresActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemProveedores);

        jMenuItemPacks.setText("Packs");
        jMenuItemPacks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPacksActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemPacks);

        jMenuItemArticulos.setText("Artículos");
        jMenuItemArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemArticulosActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemArticulos);

        jMenuItemCategoriaArticulos.setText("Categoría Artículos");
        jMenuItemCategoriaArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCategoriaArticulosActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemCategoriaArticulos);

        jMenuItemComunas.setText("Comunas");
        jMenuItemComunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemComunasActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemComunas);

        jMenuItemBancos.setText("Bancos");
        jMenuItemBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBancosActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemBancos);

        jMenuItemRRSS.setText("RRSS");
        jMenuItemRRSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRRSSActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemRRSS);

        jMenuItemCategoriaVentas.setText("Estados Venta");
        jMenuItemCategoriaVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCategoriaVentasActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemCategoriaVentas);

        jMenuItemUsuarios.setText("Usuarios");
        jMenuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUsuariosActionPerformed(evt);
            }
        });
        jMenuadmin.add(jMenuItemUsuarios);

        jMenuBar.add(jMenuadmin);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTienda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemClientesActionPerformed

    private void jMenuItemProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProveedoresActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemProveedoresActionPerformed

    private void jMenuItemArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemArticulosActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemArticulosActionPerformed

    private void jMenuItemPacksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPacksActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemPacksActionPerformed

    private void jMenuItemRRSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRRSSActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemRRSSActionPerformed

    private void jMenuItemCategoriaArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCategoriaArticulosActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemCategoriaArticulosActionPerformed

    private void jMenuItemComunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemComunasActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemComunasActionPerformed

    private void jMenuItemBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBancosActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemBancosActionPerformed

    private void jMenuItemCategoriaVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCategoriaVentasActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemCategoriaVentasActionPerformed

    private void jMenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuariosActionPerformed
        showAdminTab(evt);
    }//GEN-LAST:event_jMenuItemUsuariosActionPerformed

    private void jMenuItemVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVentaActionPerformed
        showVentasTab(evt);
    }//GEN-LAST:event_jMenuItemVentaActionPerformed

    private void jMenuItemConfirmacionPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConfirmacionPagoActionPerformed
        showVentasTab(evt);
    }//GEN-LAST:event_jMenuItemConfirmacionPagoActionPerformed

    private void jMenuItemDespachosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDespachosActionPerformed
        showVentasTab(evt);
    }//GEN-LAST:event_jMenuItemDespachosActionPerformed

    private void jMenuItemEstadoDespachosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEstadoDespachosActionPerformed
        showVentasTab(evt);
    }//GEN-LAST:event_jMenuItemEstadoDespachosActionPerformed

    private void jMenuItemSolicitudPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSolicitudPedidoActionPerformed
        showComprasTab(evt);
    }//GEN-LAST:event_jMenuItemSolicitudPedidoActionPerformed

    private void jMenuItemRegistroCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRegistroCompraActionPerformed
        showComprasTab(evt);
    }//GEN-LAST:event_jMenuItemRegistroCompraActionPerformed

    private void jMenuItemFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFacturaActionPerformed
        showComprasTab(evt);
    }//GEN-LAST:event_jMenuItemFacturaActionPerformed

    private void jMenuItemInformeVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInformeVentasActionPerformed
        showInformesTab(evt);
    }//GEN-LAST:event_jMenuItemInformeVentasActionPerformed

    private void jMenuItemInformeInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInformeInventarioActionPerformed
        showInformesTab(evt);
    }//GEN-LAST:event_jMenuItemInformeInventarioActionPerformed

    private void jMenuItemInformeClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInformeClientesActionPerformed
        showInformesTab(evt);
    }//GEN-LAST:event_jMenuItemInformeClientesActionPerformed

    private void jMenuItemInformeDyCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInformeDyCActionPerformed
        showInformesTab(evt);
    }//GEN-LAST:event_jMenuItemInformeDyCActionPerformed

    private void jTabbedPaneAdminStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneAdminStateChanged
        int selectedTab = jTabbedPaneAdmin.getSelectedIndex();
        jLabelEstado.setText("Administración: " + jTabbedPaneAdmin.getTitleAt(selectedTab));
    }//GEN-LAST:event_jTabbedPaneAdminStateChanged

    private void jTabbedPaneVentasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneVentasStateChanged
        int selectedTab = jTabbedPaneVentas.getSelectedIndex();
        jLabelEstado.setText("Ventas: " + jTabbedPaneVentas.getTitleAt(selectedTab));
    }//GEN-LAST:event_jTabbedPaneVentasStateChanged

    private void jTabbedPaneComprasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneComprasStateChanged
        int selectedTab = jTabbedPaneCompras.getSelectedIndex();
        jLabelEstado.setText("Compras: " + jTabbedPaneCompras.getTitleAt(selectedTab));
    }//GEN-LAST:event_jTabbedPaneComprasStateChanged

    private void jTabbedPaneInformesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneInformesStateChanged
        int selectedTab = jTabbedPaneInformes.getSelectedIndex();
        jLabelEstado.setText("Informes: " + jTabbedPaneInformes.getTitleAt(selectedTab));
    }//GEN-LAST:event_jTabbedPaneInformesStateChanged

    private void jMenuItemArmadoPacksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemArmadoPacksActionPerformed
       showComprasTab(evt);
    }//GEN-LAST:event_jMenuItemArmadoPacksActionPerformed

    public void showVentasTab(int tab) {
        showTabbedPaneAndTab(jTabbedPaneVentas, "ventas", tab);
    }
    
    public void showComprasTab(int tab) {
        showTabbedPaneAndTab(jTabbedPaneCompras, "compras", tab);
    }
    
    public void showInformesTab(int tab) {
        showTabbedPaneAndTab(jTabbedPaneInformes, "informes", tab);
    }
    
    private void showAdminTab(int tab) {
        showTabbedPaneAndTab(jTabbedPaneAdmin, "admin", tab);
    }
    
    private void showAdminTab(ActionEvent evt) {
        showTabbedPaneAndTab(jTabbedPaneAdmin, "admin", evt);
    }
    
    private void showVentasTab(ActionEvent evt) {
        showTabbedPaneAndTab(jTabbedPaneVentas, "ventas", evt);
    }
    
    private void showComprasTab(ActionEvent evt) {
        showTabbedPaneAndTab(jTabbedPaneCompras, "compras", evt);
    }
    
    private void showInformesTab(ActionEvent evt) {
        showTabbedPaneAndTab(jTabbedPaneInformes, "informes", evt);
    }
    
    private void showTabbedPaneAndTab(JTabbedPane pane, String card, ActionEvent evt) {
        JMenuItem menuItem = (JMenuItem) evt.getSource(); 
        JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent(); 
        int tabIndex = popupMenu.getComponentZOrder(menuItem);
        CardLayout c = (CardLayout) mainContainer.getLayout();
        c.show(mainContainer, card);
        pane.setSelectedIndex(tabIndex);
    }
    
    private void showTabbedPaneAndTab(JTabbedPane pane, String card, int tab) {
        CardLayout c = (CardLayout) mainContainer.getLayout();
        c.show(mainContainer, card);
        pane.setSelectedIndex(tab);
    }
    
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
            java.util.logging.Logger.getLogger(DreamGifts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DreamGifts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DreamGifts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DreamGifts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DreamGifts().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelTienda;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuCompras;
    private javax.swing.JMenu jMenuInformes;
    private javax.swing.JMenuItem jMenuItemArmadoPacks;
    private javax.swing.JMenuItem jMenuItemArticulos;
    private javax.swing.JMenuItem jMenuItemBancos;
    private javax.swing.JMenuItem jMenuItemCategoriaArticulos;
    private javax.swing.JMenuItem jMenuItemCategoriaVentas;
    private javax.swing.JMenuItem jMenuItemClientes;
    private javax.swing.JMenuItem jMenuItemComunas;
    private javax.swing.JMenuItem jMenuItemConfirmacionPago;
    private javax.swing.JMenuItem jMenuItemDespachos;
    private javax.swing.JMenuItem jMenuItemEstadoDespachos;
    private javax.swing.JMenuItem jMenuItemFactura;
    private javax.swing.JMenuItem jMenuItemInformeClientes;
    private javax.swing.JMenuItem jMenuItemInformeDyC;
    private javax.swing.JMenuItem jMenuItemInformeInventario;
    private javax.swing.JMenuItem jMenuItemInformeVentas;
    private javax.swing.JMenuItem jMenuItemPacks;
    private javax.swing.JMenuItem jMenuItemProveedores;
    private javax.swing.JMenuItem jMenuItemRRSS;
    private javax.swing.JMenuItem jMenuItemRegistroCompra;
    private javax.swing.JMenuItem jMenuItemSolicitudPedido;
    private javax.swing.JMenuItem jMenuItemUsuarios;
    private javax.swing.JMenuItem jMenuItemVenta;
    private javax.swing.JMenu jMenuVentas;
    private javax.swing.JMenu jMenuadmin;
    private javax.swing.JPanel jPanelEstado;
    private javax.swing.JTabbedPane jTabbedPaneAdmin;
    private javax.swing.JTabbedPane jTabbedPaneCompras;
    private javax.swing.JTabbedPane jTabbedPaneInformes;
    public javax.swing.JTabbedPane jTabbedPaneVentas;
    private javax.swing.JPanel mainContainer;
    // End of variables declaration//GEN-END:variables
}

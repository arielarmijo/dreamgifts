use dreamgifts;

select p.nombre as Pack, a.nombre as Articulo, pha.cantidad, p.costo
	from packs p
    inner join
    pack_has_articulo pha on p.id = pha.pack_id
    inner join articulos a on a.id = pha.articulo_id;
    
select oc.id as OC, oc.fecha_orden as Fecha, p.razon_social as Proveedor, a.nombre as Articulo, ocd.cantidad
		from ordenes_compra oc
        inner join orden_compra_detalle ocd on oc.id = ocd.orden_compra_id
        inner join articulos a on a.id = ocd.articulo_id
        inner join proveedores p on p.id = oc.proveedor_id;
package com.example.retrofitMovil.utilities

object Constantes {
    //llamadas api
    const val BASE_URL = "http://informatica.iesquevedo.es:2326/reyortiz_servidorRest-1.0-SNAPSHOT/api/"
    const val ALLMESAS = "mesas/"
    const val ALLPEDIDOS = "pedidos/"
    const val MESA_ID = "mesas/{id}"
    const val PEDIDO_ID = "pedidos/{id}"
    const val ID = "id"
    const val PEDIDOS_POR_MESA = "pedidos/tableNumber/{tableNumber}"
    const val NO_DATA = "No data"
    const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

    //ui
    const val TABLE_NUMBER = "tableNumber"
    const val MESA = "Mesa: "
    const val ID_UI = "ID: "
    const val COMA = ", "
    const val CLIENTE = "Cliente nº"
    const val SELECTED = " selected"
    const val NO_PEDIDOS ="No hay pedidos asociados a esta mesa"

}
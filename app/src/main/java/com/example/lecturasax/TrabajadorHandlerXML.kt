package com.example.lecturasax

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class TrabajadorHandlerXML : DefaultHandler() {
    private val cadena = StringBuilder()
    private var trabajador: Trabajador? = null
    private var becario: Becario? = null
    private var proyecto: Proyecto?= null
    var trabajadoresTr: MutableList<Trabajador> = mutableListOf()
    var trabajadoresBec: MutableList<Becario> = mutableListOf()
    var proyectos: MutableList<Proyecto> = mutableListOf()


    @Throws(SAXException::class)
    override fun startDocument() {
        cadena.clear()
        trabajadoresTr = mutableListOf()
        trabajadoresBec = mutableListOf()
       // proyectos = mutableListOf()
        Log.d("SAX", "abriendo el documento")
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String, nombreLocal: String, nombre: String, attributes: Attributes) {
        cadena.setLength(0)
        if (nombre == "trabajador") {
            trabajador = Trabajador()
            trabajador?.empresa = attributes.getValue("empresa")
            trabajador?.lugar = attributes.getValue("lugar")
        }
        Log.d("SAX", "abriendo etiqueta trabajador")

        if (nombre=="proyectos"){
            proyectos= mutableListOf()
        }
        Log.d("SAX", "abriendo etiqueta proyectos")

        if (nombre=="proyecto"){
            proyecto= Proyecto()
        }
        Log.d("SAX", "abriendo etiqueta proyecto")

        if (nombre=="becario"){
            becario= Becario()
            becario?.funcion = attributes.getValue("funcion")
        }
        Log.d("SAX", "abriendo etiqueta becario")

        if(nombre=="ies"){
            becario?.lugar=attributes.getValue("lugar")
        }
        Log.d("SAX", "abriendo etiqueta lugar")

    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        cadena.append(ch, start, length)
        Log.d("SAX", "guardando en una cadena $cadena")
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, nombreLocal: String, nombre: String) {
        when (nombre) {
            "nombre" -> trabajador?.nombre = cadena.toString()
            "edad" -> trabajador?.edad = cadena.toString().toInt()
            "nomPro" -> proyecto?.nomPro = cadena.toString()
            "fecha" -> proyecto?.fecha = cadena.toString().toInt()
            "proyecto" -> proyectos.add(proyecto!!)
            "proyectos" -> trabajador?.proyectos=Proyectos(proyectos)
            "trabajador" -> trabajador?.let { trabajadoresTr.add(it) }
            "ies" -> becario?.ies=cadena.toString()
            "alias" -> becario?.alias=cadena.toString()
            "apellido" -> becario?.apellido=cadena.toString()
            "becario" -> becario?.let { trabajadoresBec.add(it) }
        }

        Log.d("SAX", "cerrando elemento $nombre $nombreLocal")
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        Log.d("SAX", "Documento Terminado")
    }
}

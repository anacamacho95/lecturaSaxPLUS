package com.example.lecturasax

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "trabajadores")
data class Trabajadores(
    @field:ElementList(inline = true, entry = "trabajador")
    var trabajador: List<Trabajador> = mutableListOf(),
    @field:ElementList(inline = true, entry = "becario")
    var becario: List<Becario> = mutableListOf()

)

@Root(name = "trabajador")
data class Trabajador(
    @field:Element(name = "nombre")
    var nombre: String = "",

    @field:Element(name = "edad")
    var edad: Int = 0,
    @field:Element(name = "proyectos", required = false)
    var proyectos: Proyectos? = null,

    @field:Attribute(name = "empresa", required = false)
    var empresa: String? = null,

    @field:Attribute(name = "lugar1", required = false)
    var lugar1: String? = null,

)
{
    override fun toString():String{ //no se usa, estamos recurriendo al Log.d
        return "Nombre:$nombre Edad:$edad Empresa:$empresa NºProyectos:${proyectos.toString()}"
    }
}
@Root(name = "proyectos")
data class Proyectos(
    @field:ElementList(inline = true, entry = "proyecto")
    var proyecto: List<Proyecto> = mutableListOf()
)
{
    override fun toString(): String {
        return proyecto.toString()
    }
}

@Root(name = "proyecto")
data class Proyecto(
    @field:Element(name = "nomPro")
    var nomPro: String = "",

    @field:Element(name = "fecha")
    var fecha: Int = 0,
){
    override fun toString(): String {
        return "NomPro:$nomPro Fecha:$fecha"
    }
}

@Root(name = "becario")
data class Becario(
    @field:Element(name = "ies")
    var ies: String = "",
    @field:Element(name = "alias")
    var alias: String = "",
    @field:Element(name = "apellido", required = false)
    var apellido: String? = "",


    @field:Attribute(name = "funcion", required = false)
    var funcion: String? = null,
    @field:Attribute(name = "lugar2", required = false)
    var lugar2: String? = null,

    )
{
    override fun toString():String{
        return "IES:$ies Nombre:$alias Funcion:$funcion Lugar:$lugar2"
    }
}

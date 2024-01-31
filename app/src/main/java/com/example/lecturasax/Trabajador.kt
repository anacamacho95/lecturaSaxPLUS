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

    @field:Attribute(name = "empresa", required = false)
    var empresa: String? = null,

    @field:Attribute(name = "lugar", required = false)
    var lugar: String? = null,

)
{
    override fun toString():String{
        return "Nombre:$nombre Edad:$edad Empresa:$empresa"
    }
}
@Root(name = "becario")
data class Becario(
    @field:Element(name = "ies")
    var ies: String = "",
    @field:Element(name = "alias")
    var alias: String = "",
    @field:Element(name = "apellido")
    var apellido: String? = "",


    @field:Attribute(name = "funcion", required = false)
    var funcion: String? = null,
    @field:Attribute(name = "lugar", required = false)
    var lugar: String? = null,

    )
{
    override fun toString():String{
        return "IES:$ies Nombre:$alias Funcion:$funcion Lugar:$lugar"
    }
}

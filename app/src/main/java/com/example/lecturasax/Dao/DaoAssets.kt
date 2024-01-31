package com.example.lecturasax.Dao

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lecturasax.Becario
import com.example.lecturasax.Trabajador
import com.example.lecturasax.Trabajadores
import org.simpleframework.xml.core.Persister
import java.io.*

class DaoAssets (private val context: Context) {
    var trabajadoresTr = mutableListOf<Trabajador>() //importante INICIALIZAR VARIABLE
    var trabajadoresBec = mutableListOf<Becario>()

    fun procesarArchivoAssetsXML() {
        val serializer = Persister()
        var inputStream: InputStream? = null
        var reader: InputStreamReader? = null

        try {
            inputStream = context.assets.open("trabajadores.xml")//importante pasar el context
            reader = InputStreamReader(inputStream)
            val trabajadoresListType = serializer.read(Trabajadores::class.java, reader, false)
            trabajadoresTr.addAll(trabajadoresListType.trabajador)
            Log.d("assetsXMLtra", trabajadoresTr.toString())

            trabajadoresBec.addAll(trabajadoresListType.becario)
            Log.d("assetsXMLbec", trabajadoresBec.toString())


        } catch (e: Exception) {
            // Manejo de errores
            e.printStackTrace()
        } finally {
            // Cerrar inputStream y reader
            try {
                reader?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    fun copiarArchivoDesdeAssets() {
        val nombreArchivo = "trabajadores.xml"
        val archivoEnAssets = context.assets.open(nombreArchivo)
        val archivoInterno = context.openFileOutput(
            nombreArchivo,
            AppCompatActivity.MODE_PRIVATE
        )//tener en cuenta el context y AppCompatActivity

        archivoEnAssets.copyTo(archivoInterno)
        archivoEnAssets.close()
        archivoInterno.close()

    }

    fun addTrabajador(trabajador: Trabajador) {
        try {
            val serializer = Persister()
            trabajadoresTr.add(trabajador)
            val trabajadoresList = Trabajadores(trabajadoresTr)
            val outputStream = context.openFileOutput(
                "trabajadores.xml",
                AppCompatActivity.MODE_PRIVATE
            )//***Añadir context
            serializer.write(trabajadoresList, outputStream)
            outputStream.close() // Asegúrate de cerrar el outputStream después de escribir
        } catch (e: Exception) {
            e.printStackTrace() // Manejo de errores adecuado
        }
    }
    fun addBecario(becario: Becario) {
        try {
            val serializer = Persister()
            trabajadoresBec.add(becario)
            val becariosList = Trabajadores(trabajadoresTr,trabajadoresBec)
            val outputStream = context.openFileOutput(
                "trabajadores.xml",
                AppCompatActivity.MODE_PRIVATE
            )//***Añadir context
            serializer.write(becariosList, outputStream)
            outputStream.close() // Asegúrate de cerrar el outputStream después de escribir
        } catch (e: Exception) {
            e.printStackTrace() // Manejo de errores adecuado
        }
    }

    fun ProcesarArchivoXMLInterno() {
        val nombreArchivo = "trabajadores.xml"
        val serializer = Persister()

        try {
            // Abrir el archivo para lectura
            val file = File(context.filesDir, nombreArchivo)//***IMP context
            val inputStream = FileInputStream(file)
            val trabajadoresList = serializer.read(Trabajadores::class.java, inputStream)
            trabajadoresTr.clear() //evitar duplicar datos, limpiamos lista de trabajadores, no el archivo interno
            trabajadoresBec.clear()
            trabajadoresTr.addAll(trabajadoresList.trabajador)
            trabajadoresTr.forEach(){
                Log.d("assetsXML-interno", it.toString())
            }
            trabajadoresBec.addAll(trabajadoresList.becario)
            trabajadoresBec.forEach(){
                Log.d("assetsXML-interno", it.toString())
            }
            inputStream.close()

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    fun vaciarArchivoInterno() { //vaciamos el archivo interno
        val nombreArchivo = "trabajadores.xml"

        // Abre el archivo interno para escritura (esto eliminará el contenido existente)
        val archivoInternoEscritura =
            context.openFileOutput(nombreArchivo, AppCompatActivity.MODE_PRIVATE)
        archivoInternoEscritura.write("".toByteArray())

        // Cierra el archivo
        archivoInternoEscritura.close()
    }
}
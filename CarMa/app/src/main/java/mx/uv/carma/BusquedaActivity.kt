package mx.uv.carma

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.uv.carma.databinding.ActivityBusquedaBinding
import mx.uv.carma.modelo.BusquedaBD
import mx.uv.carma.poko.Estado
import mx.uv.carma.poko.Municipio

class BusquedaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusquedaBinding
    private lateinit var db: BusquedaBD
    private var uid = ""
    private var nombre = ""
    private val marcasSeleccionadas = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        nombre = intent.getStringExtra("nombre")!!
        uid = intent.getStringExtra("uid")!!

        db = BusquedaBD(this)

        binding.btnBuscar.setOnClickListener {
            buscarCoches()
        }

        binding.llMarcaVw.setOnClickListener {
            val marcaCoche = "Volkswagen"


            val drawableRes = mx.uv.carma.R.drawable.rounded_corners_l_primary
            val colorRes = mx.uv.carma.R.color.l_accent


            val drawableResAccent = mx.uv.carma.R.drawable.rounded_corners_busqueda_marca
            val colorResDark = mx.uv.carma.R.color.l_primary_dark

            if (marcasSeleccionadas.contains(marcaCoche)) {

                marcasSeleccionadas.remove(marcaCoche)
                binding.llMarcaVw.setBackgroundResource(drawableResAccent)
                binding.tvMarcaVw.setTextColor(ContextCompat.getColor(this@BusquedaActivity, colorResDark))
            } else {

                marcasSeleccionadas.add(marcaCoche)
                binding.llMarcaVw.setBackgroundResource(drawableRes)
                binding.tvMarcaVw.setTextColor(ContextCompat.getColor(this@BusquedaActivity, colorRes))
            }



        }

        binding.llMarcaChevrolet.setOnClickListener {
            val marcaCoche = "Chevrolet"


            val drawableRes = mx.uv.carma.R.drawable.rounded_corners_l_primary
            val colorRes = mx.uv.carma.R.color.l_accent


            val drawableResAccent = mx.uv.carma.R.drawable.rounded_corners_busqueda_marca
            val colorResDark = mx.uv.carma.R.color.l_primary_dark

            if (marcasSeleccionadas.contains(marcaCoche)) {

                marcasSeleccionadas.remove(marcaCoche)
                binding.llMarcaChevrolet.setBackgroundResource(drawableResAccent)
                binding.tvMarcaChevrolet.setTextColor(ContextCompat.getColor(this@BusquedaActivity, colorResDark))
            } else {

                marcasSeleccionadas.add(marcaCoche)
                binding.llMarcaChevrolet.setBackgroundResource(drawableRes)
                binding.tvMarcaChevrolet.setTextColor(ContextCompat.getColor(this@BusquedaActivity, colorRes))
            }



        }

        binding.llMarcaFord.setOnClickListener {
            val marcaCoche = "Ford"


            val drawableRes = mx.uv.carma.R.drawable.rounded_corners_l_primary
            val colorRes = mx.uv.carma.R.color.l_accent


            val drawableResAccent = mx.uv.carma.R.drawable.rounded_corners_busqueda_marca
            val colorResDark = mx.uv.carma.R.color.l_primary_dark

            if (marcasSeleccionadas.contains(marcaCoche)) {

                marcasSeleccionadas.remove(marcaCoche)
                binding.llMarcaFord.setBackgroundResource(drawableResAccent)
                binding.tvMarcaFord.setTextColor(ContextCompat.getColor(this@BusquedaActivity, colorResDark))
            } else {

                marcasSeleccionadas.add(marcaCoche)
                binding.llMarcaFord.setBackgroundResource(drawableRes)
                binding.tvMarcaFord.setTextColor(ContextCompat.getColor(this@BusquedaActivity, colorRes))
            }


        }

        binding.llMarcaNissan.setOnClickListener {
            val marcaCoche = "Nissan"


            val drawableRes = mx.uv.carma.R.drawable.rounded_corners_l_primary
            val colorRes = mx.uv.carma.R.color.l_accent


            val drawableResAccent = mx.uv.carma.R.drawable.rounded_corners_busqueda_marca
            val colorResDark = mx.uv.carma.R.color.l_primary_dark

            if (marcasSeleccionadas.contains(marcaCoche)) {

                marcasSeleccionadas.remove(marcaCoche)
                binding.llMarcaNissan.setBackgroundResource(drawableResAccent)
                binding.tvMarcaNissan.setTextColor(ContextCompat.getColor(this@BusquedaActivity, colorResDark))
            } else {

                marcasSeleccionadas.add(marcaCoche)
                binding.llMarcaNissan.setBackgroundResource(drawableRes)
                binding.tvMarcaNissan.setTextColor(ContextCompat.getColor(this@BusquedaActivity, colorRes))
            }


        }


        val spinnerPais = binding.spinnerPais
        val spinnerEstado = binding.spinnerEstado
        val spinnerMunicipio = binding.spinnerMunicipio


        val paises = db.obtenerPaises()
        val listaPaises = mutableListOf("Cualquiera") + paises.map { it.nombre }
        val adapterPaises = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPaises)
        spinnerPais.adapter = adapterPaises

        var estados = listOf<Estado>()
        var municipios = listOf<Municipio>()

        spinnerPais.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {

                    val listaEstados = listOf("Cualquiera")
                    val adapterEstados = ArrayAdapter(this@BusquedaActivity, android.R.layout.simple_spinner_item, listaEstados)
                    spinnerEstado.adapter = adapterEstados
                } else {
                    val paisSeleccionado = paises[position - 1]
                    estados = db.obtenerEstadosPorPais(paisSeleccionado.id)
                    val listaEstados = mutableListOf("Cualquiera") + estados.map { it.nombre }
                    val adapterEstados = ArrayAdapter(this@BusquedaActivity, android.R.layout.simple_spinner_item, listaEstados)
                    spinnerEstado.adapter = adapterEstados
                }

                spinnerEstado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if (position == 0) {

                            val listaMunicipios = listOf("Cualquiera")
                            val adapterMunicipios = ArrayAdapter(this@BusquedaActivity, android.R.layout.simple_spinner_item, listaMunicipios)
                            spinnerMunicipio.adapter = adapterMunicipios
                        } else {
                            val estadoSeleccionado = estados[position - 1]
                            municipios = db.obtenerMunicipiosPorEstado(estadoSeleccionado.id)
                            val listaMunicipios = mutableListOf("Cualquiera") + municipios.map { it.nombre }
                            val adapterMunicipios = ArrayAdapter(this@BusquedaActivity, android.R.layout.simple_spinner_item, listaMunicipios)
                            spinnerMunicipio.adapter = adapterMunicipios
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }



        binding.ivNavInicio.setOnClickListener {
            irPantallaInicio()
        }

        binding.ivNavPerfil.setOnClickListener {
            irPantallaPerfil()
        }

        binding.ivNavVenta.setOnClickListener {
            irPantallaVenta()
        }

        binding.ivNavChats.setOnClickListener {
            irPantallaMisChats()
        }


        configurarRangeSliderFormatoPrecio()
        configurarRangeSliderFormatoKm()
        configurarSpinnerAnios()
        configurarSpinnerArregloTransmision()
        configurarSpinnerArregloCombustible()

    }


    private fun buscarCoches() {

        val marcasSeleccionadasList = ArrayList(marcasSeleccionadas)


        val anioSeleccionado = binding.spinnerAnio.selectedItem.toString()


        val transmisionSeleccionada = binding.spinnerTransmision.selectedItem.toString()


        val combustibleSeleccionado = binding.spinnerCombustible.selectedItem.toString()


        val minPrecio = binding.rangeSliderPrecio.values[0].toInt()
        val maxPrecio = binding.rangeSliderPrecio.values[1].toInt()


        val minKm = binding.rangeSliderKm.values[0].toInt()
        val maxKm = binding.rangeSliderKm.values[1].toInt()


        val paisSeleccionado = binding.spinnerPais.selectedItem.toString()
        val estadoSeleccionado = binding.spinnerEstado.selectedItem.toString()
        val municipioSeleccionado = binding.spinnerMunicipio.selectedItem.toString()


        val intent = Intent(this@BusquedaActivity, ResultadosBusquedaActivity::class.java)
        intent.putStringArrayListExtra("marcasSeleccionadas", marcasSeleccionadasList)
        intent.putExtra("anio", anioSeleccionado)
        intent.putExtra("transmision", transmisionSeleccionada)
        intent.putExtra("combustible", combustibleSeleccionado)
        intent.putExtra("precioMin", minPrecio)
        intent.putExtra("precioMax", maxPrecio)
        intent.putExtra("kmMin", minKm)
        intent.putExtra("kmMax", maxKm)
        intent.putExtra("pais", paisSeleccionado)
        intent.putExtra("estado", estadoSeleccionado)
        intent.putExtra("municipio", municipioSeleccionado)
        intent.putExtra("nombre", nombre)
        intent.putExtra("uid",uid)


        startActivity(intent)
    }


    fun configurarRangeSliderFormatoPrecio(){
        val rangeSliderPrecio = binding.rangeSliderPrecio
        val minTextViewPrecio = binding.tvMinPrecio
        val maxTextViewPrecio = binding.tvMaxPrecio

        rangeSliderPrecio.addOnChangeListener { slider, _, _ ->
            val min = slider.values[0].toInt()
            val max = slider.values[1].toInt()
            minTextViewPrecio.text = String.format("$%,d", min)
            maxTextViewPrecio.text = String.format("$%,d", max)
        }
    }

    fun configurarRangeSliderFormatoKm(){
        val rangeSliderKm = binding.rangeSliderKm
        val minTextViewKm = binding.tvMinKm
        val maxTextViewKm = binding.tvMaxKm

        rangeSliderKm.addOnChangeListener { slider, _, _ ->
            val min = slider.values[0].toInt()
            val max = slider.values[1].toInt()
            minTextViewKm.text = String.format("%,d", min)
            maxTextViewKm.text = String.format("%,d", max)
        }
    }



    fun configurarSpinnerAnios() {

        val opcionesIntent = mutableListOf<String>("Cualquiera")
        for (i in 2024 downTo 1980) {
            opcionesIntent.add(i.toString())
        }

        val adapterArray = ArrayAdapter(this@BusquedaActivity,
            android.R.layout.simple_spinner_item, opcionesIntent)

        adapterArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAnio.adapter = adapterArray
    }

    fun configurarSpinnerArregloTransmision(){

        val opcionesIntent = arrayOf("Cualquiera", "Manual", "Automática")
        val adapterArray = ArrayAdapter(this@BusquedaActivity,
            android.R.layout.simple_spinner_item, opcionesIntent)
        adapterArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTransmision.adapter = adapterArray
    }

    fun configurarSpinnerArregloCombustible(){

        val opcionesIntent = arrayOf("Cualquiera", "Gasolina", "Diésel", "Electricidad")
        val adapterArray = ArrayAdapter(this@BusquedaActivity,
            android.R.layout.simple_spinner_item, opcionesIntent)
        adapterArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCombustible.adapter = adapterArray
    }


    fun irPantallaInicio(){
        val intent = Intent(this@BusquedaActivity,InicioActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("uid", uid)
        startActivity(intent)
    }

    fun irPantallaPerfil(){
        val intent = Intent(this@BusquedaActivity,PerfilActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("uid", uid)
        startActivity(intent)
    }

    fun irPantallaVenta(){
        val intent = Intent(this@BusquedaActivity,VentaActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("uid", uid)
        startActivity(intent)
    }

    fun irPantallaMisChats(){
        val intent = Intent(this@BusquedaActivity,MisChatsActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("uid", uid)
        startActivity(intent)
    }

}